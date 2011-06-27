import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class SkunkEnemy extends Enemy
{
	Image skunky;
	Image skunkyFlipped;
	Element projectile;

	public SkunkEnemy(int x, int y, int w, int h, Color c, String nam, boolean grav, ArrayList<Element> elements, Window win) 
	{
		super(x, y, w, h, c, nam, grav, elements, win);
		
		Image skunk = null;
		Image skunkFlipped = null;
		
		try {skunk = ImageIO.read(new File("skunk.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}

		try {skunkFlipped = ImageIO.read(new File("skunkFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {skunky = ImageIO.read(new File("skunkAttack.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}

		try {skunkyFlipped = ImageIO.read(new File("skunkAttackFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setProjectileCapable(true);
		
		setImage(skunkFlipped);
		setReverseImage(skunk);
		setHealth(5);
		setWidth(skunk.getWidth(null)/5);
		setHeight(skunk.getHeight(null)/5);
		
		setHealth(2);
		setHasAttack(true);
	}
	
	@Override
	public void move()
	{
		if (!hasElement())
			findElement(Window.getWindow().getGrid().getElements());
		
		if (attackRange(xRange))
			attack(window.getController().isLeft(this, window.getBadass()));   /// if enemy is left of badass
		
		else
			setBusy(false);

		if (!isBusy())
		{
			Window.getWindow().getGrid().removeElement(projectile);
			projectile = null;
			
			move(Window.getWindow().getGrid().getElements());
		}
	}
	
	@Override
	public void attack(boolean b)
	{
		setBusy(true);
		
		if (projectile == null)
		{
			if (b)
			{
				if (isFacingRight())
					setRight(false);
				
				projectile = new Element((int)getDimension().getWidth()*5 + 30, (int)getDimension().getHeight()*5, 20, 20, Color.black, "attack", false);
				projectile.setImage(skunky);
				projectile.setWidth(skunky.getWidth(null)/5);
				projectile.setHeight(skunky.getHeight(null)/5);
			}
			
			else
			{
				if (!isFacingRight())
					setRight(true);
				
				projectile = new Element((int)getDimension().getWidth()*5 - (getWidth()*5) - 40, (int)getDimension().getHeight()*5, 20, 20, Color.black, "attack", false);
				projectile.setImage(skunkyFlipped);
				projectile.setWidth(skunkyFlipped.getWidth(null)/5);
				projectile.setHeight(skunkyFlipped.getHeight(null)/5);
			}
			
			window.getGrid().addElement(projectile);			
			
		}
		
		if (window.getController().checkCollisions(window.getBadass(), projectile))
		{
			Badass badass = window.getBadass();
			
			badass.setHealth(badass.getHealth()-1);
			
			if (badass.getDimension().getWidth() < getDimension().getWidth())
				badass.save(true, projectile);
			
			else
				badass.save(false, projectile);
			
			window.setFlash(true);
		}
			
	}
	
	
	public void destroy()
	{
		window.getGrid().removeElement(projectile);
		projectile = null;
	}

}
