import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class EarthFloor extends Element
{
	Image earthFloor;

	public EarthFloor(Dimension d, int w, int h) 
	{
		super(d, w, h);
		
		try {earthFloor = ImageIO.read(new File("earthFloor.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setImage(earthFloor);
		setDimension(new Dimension((int)d.getWidth()/5, (int)d.getHeight()/5));
		setGravity(false);
		setName("Floor");
	}
	
	public EarthFloor(int x, int y, int w, int h, Color c, String s, boolean b)
	{
		super(x, y, w, h, c, s, b);
		
		try {earthFloor = ImageIO.read(new File("earthFloor.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setImage(earthFloor);
	}
	
}
