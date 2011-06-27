

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Badass extends Element
{
	int gravity;
	int time = 0;
	int endJump = 3;
	Dimension d;
	int jumpH;
	boolean shooting = false;
	boolean right;
	Bullet bullet;
	private int fuel = 1;
	static int startingFuel = 15;
	private int originalFuel;
	private int originalHealth;
	private int health = 6;
	private int startingHealth = 6;
	private int adjustment;
	private Image bulletReg;
	private Image bulletStopped;
	private boolean locked = false;
	private int hurt = 0;
	Image img;
	Image forward;
	Image forwardBackup;
	Image back;
	Image backBackup;
	BufferedImage transForw;
	BufferedImage transBack;
	

	public Badass(int x, int y, int w, int h, Color c, String nam, boolean grav) 
	{
		super(x, y, w, h, c, nam, grav);
		d = this.getDimension();
		
		try {forward = ImageIO.read(new File("Badass.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		forwardBackup = forward;
		
		try {back = ImageIO.read(new File("BadassFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		backBackup = back;
		
		try {transBack = ImageIO.read(new File("BadassTransparentFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {transForw = ImageIO.read(new File("BadassTransparent.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		img = forward;
		
		setImage(img);
		setWidth(img.getWidth(null)/5);
		setHeight(img.getHeight(null)/5);
		
		startingHealth = 6;
		startingFuel = 15;
		
		originalFuel = startingFuel;
		originalHealth = startingHealth;
	}
	
	public Badass(Dimension k, int x, int y)
	{
		super(k, x, y);
		d = this.getDimension();
		super.setDimension(k);
	}
	
	public void jump(int grav)
	{
		if (fuel > 0 && !locked)
		{	
			moveUp();
			fuel--;
		}
	}
	
	public void jump(int grav, Element e)
	{
		if (fuel > 0 && !locked)
		{	
			moveUp(e);
			fuel--;
		}
	}
	
	public void moveUp()
	{
		if (onGround())
			jumpH = 12;
		
		else
			jumpH = 5;
		
		d = new Dimension((int)getDimension().getWidth(), (int)getDimension().getHeight());
		setDimension((int) d.getWidth(),(int) d.getHeight() - jumpH);
		if (Window.getWindow().getController().spaceInvalid(this, Window.getWindow().getGrid().getElements()))
			setDimension(d);
	}
	
	public void moveLeft()
	{	
		d = this.getDimension();
		super.setDimension(new Dimension((int)d.getWidth() - 1,(int) d.getHeight()));
		if (Window.getWindow().getController().spaceInvalid(this, Window.getWindow().getGrid().getElements()))
			setDimension(d);
	}
	
	public void moveRight()
	{
		d = this.getDimension();
		super.setDimension(new Dimension((int)d.getWidth() + 1 ,(int) d.getHeight()));
		if (Window.getWindow().getController().spaceInvalid(this, Window.getWindow().getGrid().getElements()))
			setDimension(d);
	}
	
	public void moveUp(Element e)
	{
		if (onGround())
			jumpH = 12;
		
		else
			jumpH = 5;
		
		d = new Dimension((int)getDimension().getWidth(), (int)getDimension().getHeight());
		setDimension((int) d.getWidth(),(int) d.getHeight() - jumpH);
		if (Window.getWindow().getController().checkCollisions(this, Window.getWindow().getGrid().getElements(), e))
			setDimension(d);
	}
	
	public void moveLeft(Element e)
	{
		d = this.getDimension();
		super.setDimension(new Dimension((int)d.getWidth() - 1,(int) d.getHeight()));
		if (Window.getWindow().getController().checkCollisions(this, Window.getWindow().getGrid().getElements(), e))
			setDimension(d);
	}
	
	public void moveRight(Element e)
	{
		d = this.getDimension();
		super.setDimension(new Dimension((int)d.getWidth() + 1 ,(int) d.getHeight()));
		if (Window.getWindow().getController().checkCollisions(this, Window.getWindow().getGrid().getElements(), e))
			setDimension(d);
	}
	
	public void save(boolean right, Element e)
	{
		if (right)
		{
			moveLeft(e);
			moveLeft(e);
			moveLeft(e);
			
			jump(Window.getWindow().getController().getGravity(), e);
			jump(Window.getWindow().getController().getGravity(), e);
		}
		
		else
		{
			moveRight(e);
			moveRight(e);
			moveRight(e);
			
			jump(Window.getWindow().getController().getGravity(), e);
			jump(Window.getWindow().getController().getGravity(), e);
		}
	}
	
	public void shoot(boolean right, String kind)
	{
		d = getDimension();
		if (shooting)
			bullet.destroy();

		generateBullet(right, kind);
		shooting = true;
	}
	
	public void moveBullet()
	{
		if (bullet.isRight())
			bullet.moveRight(this);
			
		else
			bullet.moveLeft(this);
	}
	
	public int getFuel()
	{
		return fuel;
	}
	
	public void setFuel(int x)
	{
		if (x <= startingFuel)
			fuel = x;
		
		else
			fuel = startingFuel;
	}
	
	public Bullet getBullet()
	{
		return bullet;
	}
	
	public boolean isShooting()
	{
		return shooting;
	}
	
	public void generateBullet(boolean right, String kind)
	{
		if (kind.compareTo("regular")==0)
		{
			if (right)
			{
				try {bulletReg = ImageIO.read(new File("bulletMoving.png"));}
				catch (IOException e){System.out.println("*****ERROR****");}
				adjustment = 60;
			}
			
			else
			{
				try {bulletReg = ImageIO.read(new File("bulletMovingFlipped.png"));}
				catch (IOException e){System.out.println("*****ERROR****");}
				adjustment = -40;
			}
			
			bullet = new Bullet((int)(d.getWidth()*5 + adjustment),(int) (d.getHeight()*5) + 20, 30, 5, Color.red, "bullet", false);
			bullet.setImage(bulletReg);
			bullet.setWidth(bulletReg.getWidth(null)/5);
		}
		
		else if (kind.compareTo("rocket")==0)
		{
			if (right)
				adjustment = 60;
			
			else
				adjustment = -40;
			
			bullet = new Rocket((int)(d.getWidth()*5 + adjustment),(int) (d.getHeight()*5) + 20, 30, 5, Color.red, "bullet", false);
		}
		
		
		else if (kind.compareTo("shotgun")==0)
		{
			if (right)
			{
				try {bulletReg = ImageIO.read(new File("bulletMoving.png"));}
				catch (IOException e){System.out.println("*****ERROR****");}
				adjustment = 60;
			}
			
			else
			{
				try {bulletReg = ImageIO.read(new File("bulletMovingFlipped.png"));}
				catch (IOException e){System.out.println("*****ERROR****");}
				adjustment = -40;
			}
			
			bullet = new ShotgunBullet((int)(d.getWidth()*5 + adjustment),(int) (d.getHeight()*5) + 20, 30, 5, Color.red, "bullet", false);
			bullet.setImage(bulletReg);
			bullet.setWidth(bulletReg.getWidth(null)/5);
		}
		
		else if (kind.compareTo("guitar")==0)
		{
			bullet = new GuitarBullet((int)(d.getWidth()*5 + adjustment),(int) (d.getHeight()*5) + 20, 30, 5, Color.red, "bullet", false, this);
			
			if (right)
			{
				try {bulletReg = ImageIO.read(new File("guitarBullet.png"));}
				catch (IOException e){System.out.println("*****ERROR****");}
				adjustment = 60;
			}
			
			else
			{
				try {bulletReg = ImageIO.read(new File("guitarBulletFlipped.png"));}
				catch (IOException e){System.out.println("*****ERROR****");}
				adjustment = -40;
			}
			
			bullet.setImage(bulletReg);
			bullet.setWidth(bulletReg.getWidth(null)/5);
			bullet.setHeight(bulletReg.getHeight(null)/5);
		}
		
		else if (kind.compareTo("teleport")==0)
		{
			if (right)
			{
				try {bulletReg = ImageIO.read(new File("bulletMoving.png"));}
				catch (IOException e){System.out.println("*****ERROR****");}
				adjustment = 60;
			}
			
			else
			{
				try {bulletReg = ImageIO.read(new File("bulletMovingFlipped.png"));}
				catch (IOException e){System.out.println("*****ERROR****");}
				adjustment = -40;
			}
			
			bullet = new Teleport((int)(d.getWidth()*5 + adjustment),(int) (d.getHeight()*5) + 20, 30, 5, Color.red, "bullet", false);
			bullet.setImage(bulletReg);
			bullet.setWidth(bulletReg.getWidth(null)/5);
		}
		
		
		if (right)
			bullet.setRight(true);
		
		else
			bullet.setRight(false);
		
		
	}
	
	public void setShooting(boolean b)
	{
		shooting = b;
	}
	
	public boolean setHealth(int x)
	{
		if (hurt > 0)
		{
			if (forward == transForw)
				revertImages();
			
			else
				setImages(transForw, transBack);
			
			hurt--;
			return false;
		}
		
		else if (x < health)
		{
			revertImages();
			hurt = 30;
			health = x;
			return true;
		}
		
		return true;
			
	}	
	
	public int getHealth()
	{
		return health;
	}
	
	public void resetHealth()
	{
		health = startingHealth;
	}
	
	public void setMaxFuel(int x)
	{
		startingFuel = x;
	}
	
	public void setMaxHealth(int x)
	{
		startingHealth = x;
	}
	
	public int getMaxFuel()
	{
		return startingFuel;
	}
	
	public Image getImage()
	{
		return img;
	}
	
	public void setImages(Image forw, Image bac)
	{
		forward = forw;
		back = bac;
	}
	
	public void revertStats()
	{
		setMaxFuel(originalFuel);
		setMaxHealth(originalHealth);
	}
	
	public void setRight(boolean r)
	{
		right = r;
		
		if (r)
			img = forward;
		
		else
			img = back;
	}
	
	public void revertImages()
	{
		forward = forwardBackup;
		back = backBackup;
	}
	
	public boolean isRight()
	{
		return right;
	}
	
	public void lockMovement(boolean b)
	{
		locked = b;
	}
	
	public int getHurt()
	{
		return hurt;
	}

}
