import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;


public class LinearMenu extends MenuSystem
{
	boolean horizontal;
	int current = 0;
	int xBuffer = 0;
	int yBuffer = 0;
	Point p;
	BufferedImage bg = null;
	
	public LinearMenu(boolean horizontal, int width, int height)
	{
		this.horizontal = horizontal;
		p = new Point(width, height);
		
		if (horizontal)
			current = width;
		
		else
			current = height;
	}
	
	@Override
	public void addItem(StartButton s)
	{
		if (s != null)
		{
			if (horizontal)
			{
				StartButton toAdd = new StartButton(current + xBuffer, (int) p.getY() + yBuffer, s.getImage().getWidth(), s.getImageDown().getHeight(), s.getImage(), s.getImageDown());
				current += s.getImage().getWidth() + 5;
				super.addItem(toAdd);
			}
			
			else
			{
				StartButton toAdd = new StartButton((int) p.getX() + xBuffer, current + yBuffer, s.getImage().getWidth(), s.getImageDown().getHeight(), s.getImage(), s.getImageDown());
				current += s.getImage().getHeight() + 5;
				super.addItem(toAdd);
			}
			
		}
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(bg, (int) p.getX(), (int) p.getY(), null);
		super.draw(g);
	}
	
	public void setBackgroundImage(BufferedImage i, int x, int y)
	{
		bg = i;
		xBuffer = x;
		yBuffer = y;
	}
}
