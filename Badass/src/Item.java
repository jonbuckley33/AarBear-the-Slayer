import java.awt.Color;
import java.awt.Image;

public class Item extends Element
{
	boolean used;
	Window window;
	Image icon;
	
	public Item(int x, int y, int w, int h, Color c, String nam, boolean grav, Window wi) 
	{
		super(x, y, w, h, c, nam, grav);
		used = false;
		window = wi;
	}
	
	public boolean isUsed()
	{
		return used;
	}
	
	public void setUsed(boolean b)
	{
		used = b;
	}
	
	public void act()
	{
		setUsed(true);
	}
	
	public Window getWindow()
	{
		return window;
	}
}
