import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Teleport extends Bullet
{
	int time = 0;
	boolean teleporting = false;
	BufferedImage teleportImage;
	Image orig;
	
	public Teleport(int x, int y, int w, int h, Color c, String nam, boolean grav) 
	{
		super(x, y, w, h, c, nam, grav);
		
		try {teleportImage = ImageIO.read(new File("TeleportImage.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
	}
	
	@Override
	public boolean act(Window w)
	{	
		setImage(teleportImage);
		Badass badass = Window.getWindow().getBadass();
		if (!teleporting)
		{
			setDimension((int)getDimension().getWidth(), (int)getDimension().getHeight() - (int)(badass.getHeight()*.4));
			setWidth(teleportImage.getWidth()/5);
			setHeight(teleportImage.getHeight()/5);
			
			if (Window.getWindow().getController().hitWall(this))
			{
				Window.getWindow().getGrid().removeElement(this);
				return true;
			}
			
			setBusy(true);
			teleporting = true;

			badass.setImages(teleportImage, teleportImage);
			badass.lockMovement(true);

			setImage(teleportImage);
			
			return false;
		}
			
		else 
		{
			time++;
			if (time < 15)
			{
				setDimension((int) getDimension().getWidth(), (int) getDimension().getHeight()+1);
				if (Window.getWindow().getController().hitWall(this))
					setDimension((int) getDimension().getWidth(), (int) getDimension().getHeight()-1);
				
				return false;
			}
			
			else
			{
				badass.setDimension((int) getDimension().getWidth(), (int) getDimension().getHeight());
				destroy();
				return true;
			}
		}

	}
	
	@Override
	public void destroy()
	{
		teleporting = false;
		setBusy(false);
		Window.getWindow().getGrid().removeElement(this);
		Window.getWindow().getBadass().lockMovement(false);
		Window.getWindow().getBadass().revertImages();

	}
}
