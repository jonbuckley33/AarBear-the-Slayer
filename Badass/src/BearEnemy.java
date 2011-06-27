import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BearEnemy extends Enemy
{
	Image img;
	Image reversed;
	
	public BearEnemy(int x, int y, int w, int h, Color c, String nam, boolean grav, ArrayList<Element> elements, Window win) 
	{
		super(x, y, w, h, c, nam, grav, elements, win);
		
		try {img = ImageIO.read(new File("bear.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {reversed = ImageIO.read(new File("bearReversed.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setDimension(new Dimension(x/5, y/5));
		setImage(img);
		setHealth(2);
		setReverseImage(reversed);
		setWidth(img.getWidth(null)/5);
		setHeight(img.getHeight(null)/5);
		setName("Enemy");
		
		setHasAttack(false);
	}

}
