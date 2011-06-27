import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class StartButton 
{
	BufferedImage img;
	BufferedImage imgDown;
	BufferedImage toDraw;
	Point point;
	boolean depressed = false;
	boolean clicked = false;
	boolean visible = true;
	int width;
	int height;
	
	public StartButton(Point p, int w, int h, BufferedImage i)
	{
		point = p;
		width = w;
		height = h;
		img = i;
	}
	
	public StartButton(Point p, int w, int h, BufferedImage i, BufferedImage imageDown)
	{
		point = p;
		width = w;
		height = h;
		img = i;
		imgDown = imageDown;
	}
	
	public StartButton(int x, int y, int w, int h, BufferedImage i)
	{
		point = new Point(x, y);
		width = w;
		height = h;
		img = i;
		imgDown = i;
	}
	
	public StartButton(int x, int y, int w, int h, BufferedImage i, BufferedImage j)
	{
		point = new Point(x, y);
		width = w;
		height = h;
		img = i;
		imgDown = j;
	}
	
	public StartButton(BufferedImage i, BufferedImage j)
	{
		img = i;
		imgDown = j;
	}
	
	
	public boolean hit(MouseEvent e)
	{		
		if (e.getX() > point.getX() && e.getX() < point.getX() + width)
		{
			if (e.getY() > point.getY() && e.getY() < point.getY() + height)
				return true;
		}
		
		return false;
	}
	
	public void draw(Graphics g)
	{
		if (depressed)
			toDraw = imgDown;
		
		else
			toDraw = img;
		
		g.drawImage(toDraw, (int) point.getX(), (int) point.getY(), null);
	}
	
	public void setImages(BufferedImage i, BufferedImage j)
	{
		img = i;
		imgDown = j;
	}
	
	public void setDepressed(boolean b)
	{
		depressed = b;
	}
	
	public void setClicked(boolean b)
	{
		visible = !b;
		clicked = b;
	}
	
	public boolean isClicked()
	{
		return clicked;
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void setVisible(boolean b)
	{
		visible = b;
	}
	
	public BufferedImage getImage()
	{
		return img;
	}
	
	public BufferedImage getImageDown()
	{
		return imgDown;
	}
	
	public void setWidth(int w)
	{
		width = w;
	}
	
	public void setHeight(int h)
	{
		height = h;
	}
	
	public Point getPoint()
	{
		return point;
	}
	
	public boolean isDepressed()
	{
		return depressed;
	}
}
