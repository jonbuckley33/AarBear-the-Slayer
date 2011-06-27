import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;


public abstract class Level 
{
	ArrayList<Element> walls = new ArrayList<Element>();
	ArrayList<NotTangible> background = new ArrayList<NotTangible>();
	Level original;
	boolean shift;
	Element e;
	Color color;
	Image img;
	String name;
	Dimension spawn;
	Element finish;
	
	public ArrayList<Element> getWalls()
	{
		return walls;
	}
	
	public void setWalls(ArrayList<Element> w)
	{
		walls = w;
	}
	
	public ArrayList<NotTangible> getScenery()
	{
		return background;
	}
	
	public void setScenery(ArrayList<NotTangible> w)
	{
		background = w;
	}
	
	public void shift(int x)
	{
		int shift = 0;
		
		if (x == 5)
			shift = 1;
		
		else if (x <= 20)
			shift = 2;
		
		else
			shift = 3;
		
		for (int i = 0; i < walls.size(); i++)
		{
			e = walls.get(i);
			e.setDimension((int)e.getDimension().getWidth() - shift, (int) e.getDimension().getHeight());
			
			if (e.getDimension().getWidth() < 0 && !(e instanceof MovingPlatform))
			{
				e.setDimension(0, (int) e.getDimension().getHeight());
				e.setWidth(e.getWidth()-shift);
				
				if (e.getWidth() <= 0)
					walls.remove(e);
			}
		}
		
		for (int i = 0; i < background.size(); i++)
		{
			e = background.get(i);
			e.setDimension((int)e.getDimension().getWidth() - shift, (int) e.getDimension().getHeight());
			
			if (e.getDimension().getWidth() < 0)
			{
				e.setDimension(0, (int) e.getDimension().getHeight());
				e.setWidth(e.getWidth()-shift);
				
				if (e.getWidth() <= 0)
					background.remove(e);
			}
		}
		
	}
	
	public Image getBackground()
	{
		return img;
	}
	
	public void setBackground(Image i)
	{
		img = i;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color c)
	{
		color = c;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public void setSpawn(Dimension d)
	{
		spawn = d;
	}
	
	public Dimension getSpawn()
	{
		return spawn;
	}
	
	public void setFinish(Element e)
	{
		finish = e;
	}
	
	public Element getFinish()
	{
		return finish;
	}
	
	public void setSave(Level l)
	{
		original = l;
	}
	
	public Level getSave()
	{
		return original;
	}
	
	public void reset()
	{
		
	}
	
	
}
