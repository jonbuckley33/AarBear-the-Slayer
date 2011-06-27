import java.awt.Dimension;
import java.util.ArrayList;


public class Grid 
{
	ArrayList<Element> elements = new ArrayList<Element>();
	ArrayList<Element> gravities = new ArrayList<Element>();
	ArrayList<Dimension> occupied = new ArrayList<Dimension>();
	ArrayList<Dimension> open = new ArrayList<Dimension>();
	
	private int height;
	private int width;
	private int wspaces;
	private int hspaces;
	
	public Grid(int h, int w)
	{
		height = h;
		width = w;
		
		wspaces = width / 10;
		hspaces = height / 10;
		
		for (int i = 0; i < height; i++)
		{
			for (int x = 0; x < width; x++)
				open.add(new Dimension(i,x));
		}
	}
	
	public void addElement(Element e)
	{
		elements.add(e);
		
		if (e.isGravity())
			gravities.add(e);
		
		/**for (int i = 0; i < e.getHeight(); i++)
		{
			for (int x = 0; x < e.getWidth(); x++)
			{
				occupied.add(new Dimension((int) (e.getDimension().getWidth() + x),(int) (e.getDimension().getHeight() + i)));
			}
		}
		
		open.removeAll(occupied);**/
	}	
	
	public void setElements(ArrayList<Element> all)
	{
		elements = all;
		
		gravities.clear();
		
		for (Element e : elements)
		{	
			if (e.isGravity())
				gravities.add(e);
		}
		
	}
	
	public void removeElement(Element e)
	{
		gravities.remove(e);
		elements.remove(e);
		occupied.remove(e);
	}
	
	public void removeGravity(Element e)
	{
		gravities.remove(e);
	}
	
	public ArrayList<Element> getElements()
	{
		return elements;
	}
	
	public ArrayList<Dimension> getOccupied()
	{
		return occupied;
	}
	
	public ArrayList<Element> getGravities()
	{
		return gravities;
	}
}
