import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Bullet extends Element
{
	Dimension d;
	boolean move = true;
	Image bulletStopped;
	BufferedImage bulletExplosion;
	boolean right = false;
	boolean done = false;
	int damage = 1;

	public Bullet(int x, int y, int w, int h, Color c, String nam, boolean grav) 
	{
		super(x, y, w, h, c, nam, grav);
		d = new Dimension(x/5, y/5);
		move = true;
		
		try {bulletStopped = ImageIO.read(new File("bulletStopped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {bulletExplosion = ImageIO.read(new File("bulletExplosion.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
	}
	
	public void moveRight(Badass b)
	{
		if (isBusy())
			act(Window.getWindow());
		
		else if (move)
		{
			d = getDimension();
			super.setDimension((int)d.getWidth() + 4,(int) d.getHeight());
		}
	}
	
	public void moveLeft(Badass b)
	{
		if (isBusy())
			act(Window.getWindow());
		
		else if (move)
		{
			d = getDimension();
			super.setDimension((int)d.getWidth() - 4,(int) d.getHeight());
		}
	}
	
	public boolean stop(Window w)
	{
		move = false;
		
		return act(w);
	}
	
	public boolean act(Window w)
	{
		setImage(bulletStopped);
		int toSet = bulletStopped.getWidth(null);
		this.setWidth(toSet/5);
		done = true;
		
		return done;
	}
	
	public void destroy()
	{
		setDimension(-100, -100);
		Window.getWindow().getGrid().removeElement(this);
		setBusy(false);
	}
	
	public boolean isRight()
	{
		return right;
	}

	public void setRight(boolean b)
	{
		right = b;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void damage()
	{
		damage--;
		
		if (damage <= 0)
		{
			Window.getWindow().getGrid().removeElement(this);
			destroy();
		}	
	}
	
	public void setDamage(int x)
	{
		damage = x;
	}
}
