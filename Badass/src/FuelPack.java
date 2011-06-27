import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class FuelPack extends Item
{
	Image img;
	
	public FuelPack(int x, int y, int w, int h, Color c, String nam, boolean grav, Window wi) 
	{
		super(x, y, w, h, c, nam, grav, wi);
		
		try {img = ImageIO.read(new File("fuelTank.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setImage(img);
		setWidth(img.getWidth(null)/5);
		setHeight(img.getHeight(null)/5);
	}
	
	@Override 
	public void act()
	{
		this.getWindow().getBadass().setMaxFuel(getWindow().getBadass().getMaxFuel() + 3);
		setUsed(true);
	}

}
