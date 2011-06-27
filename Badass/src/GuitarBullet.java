import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class GuitarBullet extends Bullet
{
	int radius = 10;
	Dimension original;
	boolean offTurn;
	Image orig;
	Image flipped;
	Image left;
	Image right;
	Image toSet;
	Image toSetFlipped;
	Badass badass;

	public GuitarBullet(int x, int y, int w, int h, Color c, String nam, boolean grav, Badass ba)
	{
		super(x, y, w, h, c, nam, grav);
		original = getDimension();	
		orig = getImage();
		badass = ba;
		
		try {toSet = ImageIO.read(new File("badassGuitar.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {toSetFlipped = ImageIO.read(new File("badassGuitarFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		badass.setImages(toSet, toSetFlipped);
		
		try {left = ImageIO.read(new File("guitarBulletFlippedTwo.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {right = ImageIO.read(new File("guitarBulletTwo.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
	}
	
	@Override
	public void moveRight(Badass b)
	{	
		move(b.isRight(), b);
	}
	
	@Override
	public void moveLeft(Badass b)
	{
			move(b.isRight(), b);
	}
	
	public void move(boolean r, Badass b)
	{
		if (r)
		{
			flipped = right;
			setDimension((int)b.getDimension().getWidth() + 10,(int) b.getDimension().getHeight() - 5);
		}
		
		else 
		{
			flipped = left;
			setDimension((int)b.getDimension().getWidth() - 13,(int) b.getDimension().getHeight() - 5);
		}
		
	}
	
	@Override
	public boolean act(Window w)
	{
		destroy();
		return true;
	}
	
	@Override
	public Image getImage()
	{
		if (offTurn)
		{
			offTurn = false;
			return orig;
		}
		
		else
		{
			offTurn  = true;
			return flipped;
		}
	}
	
	public void setOriginal(Image img)
	{
		orig = img;
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		badass.revertImages();
	}

}
