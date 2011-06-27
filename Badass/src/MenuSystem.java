import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MenuSystem 
{
	ArrayList<StartButton> menuItems = new ArrayList<StartButton>();


	
	public MenuSystem()
	{
	}
	
	public StartButton checkClicks(MouseEvent m)
	{
		for (StartButton s : menuItems)
		{
			if (s.hit(m) && s.isVisible())
				return s;
		}
		
		return null;
	}
	
	public StartButton checkMouseOvers(MouseEvent m)
	{
		for (StartButton s : menuItems)
		{
			if (s.hit(m) && s.isVisible())
			{
				s.setDepressed(true);
				return s;	
			}
			
			else
				s.setDepressed(false);
		}
		return null;
	}
	
	public void addItem(StartButton s)
	{
		menuItems.add(s);
	}
	
	public void draw(Graphics g)
	{
		for (StartButton s : menuItems)
		{
			if (s.isVisible())
				s.draw(g);
		}
	}
	
	public ArrayList<StartButton> getItems()
	{
		return menuItems;
	}
	
	public void setItems(ArrayList<StartButton> s)
	{
		menuItems = s;
	}
	
}
