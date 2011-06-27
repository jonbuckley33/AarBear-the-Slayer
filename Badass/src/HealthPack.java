import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class HealthPack extends Item
{
	Window window;
	BufferedImage pack;
	
	public HealthPack(int x, int y, int w, int h, Color c, String nam, boolean grav, Window wi)
	{
		super(x, y, w, h, c, nam, grav, wi);
		
		try {pack = ImageIO.read(new File("healthPack.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		setImage(pack);
		setWidth(pack.getWidth()/5);
		setHeight(pack.getHeight()/5);
	}
	
	public void act()
	{
		setUsed(true);
		window = getWindow();
		
		window.getBadass().setHealth(7);
	}

}
