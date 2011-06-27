import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GuitarPickup extends Item
{
	public GuitarPickup(int x, int y, int w, int h, Color c, String nam, boolean grav, Window wi) 
	{
		super(x, y, w, h, c, nam, grav, wi);
		
		try {icon = ImageIO.read(new File("guitarPickup.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setImage(icon);
		setWidth(icon.getWidth(null)/5);
		setHeight(icon.getHeight(null)/5);
		
	}
	
	@Override
	public void act()
	{
		used = true;
		
		getWindow().addWeapon(new Weapon(GuitarBullet.class, "guitar"));
	}

}
