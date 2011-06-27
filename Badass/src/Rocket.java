import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Rocket extends Bullet
{
	int counter = 1;
	Image rocket;
	Image rocketFlipped;
	BufferedImage explosion;
	BufferedImage explosion1;
	BufferedImage toSet;
	boolean set = false;
	int time = 0;
	int duration = 20;

	public Rocket(int x, int y, int w, int h, Color c, String nam, boolean grav) 
	{
		super(x, y, w, h, c, nam, grav);
		
		try {rocket = ImageIO.read(new File("rocket.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {rocketFlipped = ImageIO.read(new File("rocketFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {explosion = ImageIO.read(new File("explosion.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {explosion1 = ImageIO.read(new File("explosion1.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		toSet = explosion1;
		setDamage(5);
	}
	
	public void moveRight(Badass b)
	{		
		if (isBusy())
		{
			act(Window.getWindow());
		}
		
		else
		{
			if (!set)
				setImage(rocket);
			
			if (move)
			{
				counter++;
				d = getDimension();
				super.setDimension((int)d.getWidth() + counter + 5,(int) d.getHeight());
			}
		}
		
		
	}
	
	public void moveLeft(Badass b)
	{
		if (isBusy())
			act(Window.getWindow());
		
		else 
		{
			if (!set)
				setImage(rocketFlipped);
			
			if (move)
			{
				counter++;
				d = getDimension();
				super.setDimension((int)d.getWidth() - counter - 3,(int) d.getHeight());
			}
		}	
	}
	
	@Override
	public boolean act(Window w)
	{		
		if (time == 0)
		{
			setDimension((int) getDimension().getWidth(), (int) getDimension().getHeight()-3);
			this.setWidth(toSet.getWidth()/5 + 8);
			this.setHeight(toSet.getHeight()/5 + 7);
			setBusy(true);
		}
		
		setMoving(true);
		setImage(toSet);
		
		if (toSet == explosion1)
			toSet = explosion;
		
		else
			toSet = explosion1;
		
		time++;
		
		if (time < duration)
			return false;
		
		setBusy(false);
		
		destroy();
		setMoving(false);
		return true;
	}

}
