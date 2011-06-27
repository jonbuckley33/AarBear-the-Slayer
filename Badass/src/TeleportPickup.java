import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TeleportPickup extends Item
{
	BufferedImage teleport;

	public TeleportPickup(int x, int y, int w, int h, Color c, String nam, boolean grav, Window wi)
	{
		super(x, y, w, h, c, nam, grav, wi);
		
		try {teleport = ImageIO.read(new File("TeleportPickup.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setImage(teleport);
		setWidth(teleport.getWidth()/5);
		setHeight(teleport.getHeight()/5);
	}
	
	@Override
	public void act()
	{
		super.act();
		Window.getWindow().getWeapons().add(new Weapon(GuitarBullet.class, "teleport"));
	}

}
