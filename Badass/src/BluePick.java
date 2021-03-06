import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BluePick extends Item
{
	BufferedImage pick;

	public BluePick(int x, int y, int w, int h, Color c, String nam, boolean grav, Window wi) 
	{
		super(x, y, w, h, c, nam, grav, wi);
		
		try {pick = ImageIO.read(new File("1pick.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setImage(pick);
		setWidth(pick.getWidth()/5);
		setHeight(pick.getHeight()/5);
	}
	
	@Override
	public void act()
	{
		super.act();
		
		Window.getWindow().addScore(1);
	}

}
