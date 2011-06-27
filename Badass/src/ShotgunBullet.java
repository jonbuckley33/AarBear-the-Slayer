import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ShotgunBullet extends Bullet
{
	Image bulletStopped;
	Window window;
	ArrayList<Element> shots = new ArrayList<Element>();
	
	public ShotgunBullet(int x, int y, int w, int h, Color c, String nam, boolean grav) 
	{
		super(x, y, w, h, c, nam, grav);
		
		try {bulletStopped = ImageIO.read(new File("bulletStopped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
	}
	
	@Override
	public boolean act(Window w)
	{
		window = w;
		
		setImage(bulletStopped);
		int toSet = bulletStopped.getWidth(null);
		this.setWidth(toSet/5);
		
		Element toAdd;
		
		int adjustment = 50;
		
		if (!isRight())
			adjustment = -50;

		for (int i = 1; i < 4; i ++)
		{
			toAdd = new Element((int)getDimension().getWidth()*5 + (adjustment*i), (int)getDimension().getHeight()*5 - (50*i), getWidth(), getHeight(), Color.red, "BulletCopy", false);
			toAdd.setImage(bulletStopped);
			toAdd.setWidth(bulletStopped.getWidth(null)/5);
			toAdd.setHeight(bulletStopped.getWidth(null)/5 + 1);
			w.getGrid().addElement(toAdd);
			shots.add(toAdd);
		}		
		
		return true;
	}
	
	@Override
	public void destroy()
	{
		for (Element e : shots)
			window.getGrid().removeElement(e);		
	}

}
