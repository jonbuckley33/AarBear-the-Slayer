import java.awt.Color;
import java.awt.Dimension;


public class MovingPlatform extends Element
{
	int movement;
	Dimension originalDimension = new Dimension();
	boolean up;
	boolean forward;
	int steps = 0;
	Dimension last;
	
	public MovingPlatform(int x, int y, int w, int h, Color c, String nam, boolean grav, int m, boolean u) 
	{
		super(x, y, w, h, c, nam, grav);
		int tempX = (int) getDimension().getWidth();
		int tempY = (int) getDimension().getHeight();
		
		originalDimension.setSize(new Dimension(tempX, tempY));
		movement = m;
		up = u;
		forward = true;
		setMoving(true);
	}
	
	public void move()
	{	
		last = new Dimension((int)getDimension().getWidth(),(int) getDimension().getHeight());
		setLast(last);
		
		if (!up)
		{
			int x = (int)getDimension().getWidth();
			int y = (int)getDimension().getHeight();

			originalDimension.setSize(new Dimension(x + (steps), y));
			
			if (getDimension().getWidth() >= originalDimension.getWidth() + movement)
			{
				setDimension((int) getDimension().getWidth() - 1, (int) getDimension().getHeight());
				flip();
			}
			
			else if (getDimension().getWidth() < originalDimension.getWidth() - movement)
			{
				System.out.println("too left");
				setDimension((int) getDimension().getWidth() + 1, (int) getDimension().getHeight());
				flip();
			}
			
			if (forward)
			{
				setDimension((int) getDimension().getWidth() + 1, (int) getDimension().getHeight());
				steps++;
			}
			
			else
			{
				setDimension((int) getDimension().getWidth() - 1, (int) getDimension().getHeight());
				steps--;
			}
		}
		
		else
		{
			int x = (int)getDimension().getWidth();
			int y = (int)getDimension().getHeight();

			originalDimension.setSize(new Dimension(x, y + (steps)));
			
			if (getDimension().getHeight() > originalDimension.getHeight() + movement)
			{
				setDimension((int) getDimension().getHeight() - 1, (int) getDimension().getHeight());
				flip();
			}
			
			if (getDimension().getHeight() < originalDimension.getHeight() - movement)
			{
				setDimension((int) getDimension().getWidth() + 1, (int) getDimension().getHeight());
				flip();
			}
			
			else if (forward)
			{
				setDimension((int) getDimension().getHeight() + 1, (int) getDimension().getHeight());
				steps++;
			}
			
			else
			{
				setDimension((int) getDimension().getHeight() - 1, (int) getDimension().getHeight());
				steps--;
			}
		}
				
	}
	
	public void flip()
	{
		forward = !forward;
	}
}

