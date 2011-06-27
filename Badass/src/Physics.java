import java.util.ArrayList;


public class Physics 
{
	static int gravity;
	static int elastic;
	Grid grid;
	Window window;
	ArrayList<Element> active = new ArrayList<Element>();
	int currentIndex = 0;
	
	public Physics(int weight, int elasticity, Window win)
	{
		gravity = weight;
		elastic = elasticity;
		window = win;
		grid = window.getGrid();
	}
	
	public void dropElements(ArrayList<Element> elements)
	{
		ArrayList<Element> copy = elements;
		ArrayList<Element> all = grid.getElements();

		for (int i = 0; i < copy.size(); i ++)
		{	
			Element e = copy.get(i);
			e.setDimension((int) e.getDimension().getWidth(), (int) e.getDimension().getHeight()+1);
			
			if (!spaceInvalid(e, all))
			{
				e.getMass().increaseTime();
				e.setOnGround(false);
			}
				
			else
			{
				e.setDimension((int) e.getDimension().getWidth(), (int) e.getDimension().getHeight()-1);
				e.setOnGround(true);
				e.getMass().resetTime();
			}

		}	
	}
	
	public boolean checkCollisions(Element e, ArrayList<Element> other)
	{
		while (other.contains(e))
				other.remove(e);
		
		int bottomY = (int) ((e.getDimension().getHeight()*5) + (e.getHeight()*5));
		int topY = (int) (e.getDimension().getHeight()*5);
		int kBottom;
		int kTop;
		
		ArrayList<Element> usable = other;
		
		for (Element k : usable)
		{			
			kBottom = (int) (k.getDimension().getHeight()*5 + k.getHeight()*5);
			kTop = (int) (k.getDimension().getHeight()*5);
			
			if ((e.getDimension().getWidth() + e.getWidth()) > k.getDimension().getWidth() && (e.getDimension().getWidth() < k.getDimension().getWidth() + k.getWidth()))
			{
				if (bottomY >= kTop && topY <= kBottom)
					return true;
				
				else if (topY <= kBottom && topY >= kTop)
					return true;			
			}	
		}
		return false;
	}
	
	public boolean checkCollisions(Element e, ArrayList<Element> other, Element forget)
	{
		while (other.contains(e))
				other.remove(e);
		
		while (other.contains(forget))
				other.remove(forget);
		
		
		int bottomY = (int) ((e.getDimension().getHeight()*5) + (e.getHeight()*5));
		int topY = (int) (e.getDimension().getHeight()*5);
		int kBottom;
		int kTop;
		
		ArrayList<Element> usable = other;
		
		for (Element k : usable)
		{			
			kBottom = (int) (k.getDimension().getHeight()*5 + k.getHeight()*5);
			kTop = (int) (k.getDimension().getHeight()*5);
			
			if ((e.getDimension().getWidth() + e.getWidth()) > k.getDimension().getWidth() && (e.getDimension().getWidth() < k.getDimension().getWidth() + k.getWidth()) && !(k instanceof Enemy && e instanceof Enemy))
			{
				if (bottomY >= kTop && topY <= kBottom)
				{
					other.add(forget);
					other.add(e);
					return true;
				}
				
				else if (topY <= kBottom && topY >= kTop)
				{
					other.add(forget);
					other.add(e);
					return true;			
				}
			}	
		}
		
		other.add(forget);
		other.add(e);
		return false;
	}
	
	public boolean checkCollisions(Element e, Element other)
	{	
		int bottomY = (int) ((e.getDimension().getHeight()) + (e.getHeight()));
		int topY = (int) (e.getDimension().getHeight());
		int kBottom;
		int kTop;
				
		kBottom = (int) (other.getDimension().getHeight() + other.getHeight());
		kTop = (int) (other.getDimension().getHeight());
			
		if ((e.getDimension().getWidth() + e.getWidth()) > other.getDimension().getWidth() && (e.getDimension().getWidth() < other.getDimension().getWidth() + other.getWidth()))
		{
			if (bottomY + 1 >= kTop && topY <= kBottom + 1)
				return true;
				
			else if (topY <= kBottom + 1 && topY >= kTop - 1)
				return true;			
		}
		
		int kY = (int) other.getDimension().getHeight();
		int yValue = (int) e.getDimension().getHeight();
		
		if (yValue + e.getHeight() > kY && yValue  < kY + other.getHeight())
		{
			if ((e.getDimension().getWidth() + e.getWidth()) >= (other.getDimension().getWidth()) && (e.getDimension().getWidth()) <= (other.getDimension().getWidth() + other.getWidth() + 3))
			{
				return true;
			}
		}
		return false;
	}
		
	public boolean hitWall(Element e, ArrayList<Element> other)
	{
		ArrayList<Element> temp = other;			
		int yValue = (int) e.getDimension().getHeight();
		int kY;
		
		for (Element o : temp)
		{
			if (o != e && !(o instanceof Item || o instanceof Badass || o  instanceof Enemy))
			{
				kY = (int) o.getDimension().getHeight();
				
				if (yValue + e.getHeight() > kY && yValue  < kY + o.getHeight())
				{
					if ((e.getDimension().getWidth() + e.getWidth()) >= (o.getDimension().getWidth()) && (e.getDimension().getWidth()) <= (o.getDimension().getWidth() + o.getWidth() + 1))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean hitWall(Element e)
	{
		ArrayList<Element> temp = Window.getWindow().getGrid().getElements();
		
		int yValue = (int) e.getDimension().getHeight();
		int kY;
		
		for (Element o : temp)
		{
			if (o != e && !(o instanceof Item || o instanceof Badass || o  instanceof Enemy))
			{
				kY = (int) o.getDimension().getHeight();
				
				if (yValue + e.getHeight() > kY && yValue  < kY + o.getHeight())
				{
					if ((e.getDimension().getWidth() + e.getWidth()) >= (o.getDimension().getWidth()) && (e.getDimension().getWidth()) <= (o.getDimension().getWidth() + o.getWidth() + 1))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean spaceInvalid(Element e, ArrayList<Element> other)
	{
		ArrayList<Element> temp = other;
		if (temp.contains(e))
			temp.remove(e);
		
		int yValue = (int) e.getDimension().getHeight();
		int kY;
		
		for (Element o : temp)
		{
			if (o != e)
			{
				kY = (int) o.getDimension().getHeight();
				
				if (yValue + e.getHeight() > kY && yValue  < kY + o.getHeight())
				{
					if ((e.getDimension().getWidth() + e.getWidth()) > (o.getDimension().getWidth()) && (e.getDimension().getWidth()) < (o.getDimension().getWidth() + o.getWidth()))
					{
						temp.add(e);
						return true;
					}				
				}
			}
		}
		
		temp.add(e);
		return false;
	}
	
	public boolean spaceInvalid(Element e)
	{	
		ArrayList<Element> temp = Window.getWindow().getGrid().getElements();
		boolean removed = false;
		
		if (temp.contains(e))
		{
			removed = true;
			temp.remove(e);
		}
		
		int yValue = (int) e.getDimension().getHeight();
		int kY;
		
		for (Element o : temp)
		{
			if (o != e)
			{
				kY = (int) o.getDimension().getHeight();
				
				if (yValue + e.getHeight() > kY && yValue  < kY + o.getHeight())
				{
					if ((e.getDimension().getWidth() + e.getWidth()) > (o.getDimension().getWidth()) && (e.getDimension().getWidth()) < (o.getDimension().getWidth() + o.getWidth()))
					{
						if (removed)
							temp.add(e);
						
						return true;
					}				
				}
			}
		}
		
		if (removed)
			temp.add(e);
		
		return false;
	}
	
	public Element riding(Element e, ArrayList<Element> other)
	{
		boolean removed = false;
		ArrayList<Element> temp = other;
		if (temp.contains(e))
		{
			temp.remove(e);
			removed = true;
		}
		
		int yValue = (int) e.getDimension().getHeight();
		int kY;
		
		for (Element o : temp)
		{
			if (o != e)
			{
				kY = (int) o.getDimension().getHeight();
				
				if (yValue + e.getHeight() > kY - 2 && yValue  < kY + o.getHeight() + 2)
				{
					if ((e.getDimension().getWidth() + e.getWidth()) > (o.getDimension().getWidth()) - 2 && (e.getDimension().getWidth()) < (o.getDimension().getWidth() + o.getWidth()) + 2)
					{
						if (removed)
							temp.add(e);
						
						return o;
					}				
				}
			}
		}
		
		if (removed)
			temp.add(e);
		
		return null;
	}
	
	public void maintainTraction(ArrayList<Element> elements)
	{
		int xChange = 0;
		int yChange = 0;
		Element x;
		
		ArrayList<Element> all = elements;
		for (int i = 0; i < all.size(); i++)
		{
			Element e = all.get(i);
			
			if (!(e.getName().compareTo("Floor") == 0))
			{
				x = riding(e, all);
			
				if (x instanceof MovingPlatform && x.getMoving())
				{
					System.out.println(x.getDimension().getWidth()*5);
					System.out.println("Maintainging****");
					xChange = (int) (x.getDimension().getWidth() - x.getLast().getWidth());
					yChange = (int) (x.getDimension().getHeight() - x.getLast().getHeight());
					System.out.println(e.getClass() + "    " + xChange);

					
					e.setDimension((int)e.getDimension().getWidth() + xChange, (int)e.getDimension().getHeight() + yChange);
				}
			}
		}
	}
	
	public int getGravity()
	{
		return gravity;
	}
	
	public boolean isWithin(Element e, Element other, int radius)
	{
		int bottomY = (int) ((e.getDimension().getHeight()) + (e.getHeight()));
		int topY = (int) (e.getDimension().getHeight());
		int kBottom;
		int kTop;
				
		kBottom = (int) (other.getDimension().getHeight() + other.getHeight());
		kTop = (int) (other.getDimension().getHeight());
			
		if ((e.getDimension().getWidth() + e.getWidth()) > other.getDimension().getWidth() - radius && (e.getDimension().getWidth() < other.getDimension().getWidth()  + other.getWidth() + radius))
		{
			if (bottomY >= kTop && topY <= kBottom)
				return true;
				
			else if (topY <= kBottom && topY >= kTop)
				return true;			
		}
		
		int kY = (int) other.getDimension().getHeight();
		int yValue = (int) e.getDimension().getHeight();
		
		if (yValue + e.getHeight() > kY && yValue  < kY + other.getHeight())
		{
			if ((e.getDimension().getWidth() + e.getWidth()) >= (other.getDimension().getWidth()) && (e.getDimension().getWidth()) <= (other.getDimension().getWidth() + other.getWidth() + 3))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isWithin(Element e, Element other, int radius, int height)
	{
		int bottomY = (int) ((e.getDimension().getHeight()) + (e.getHeight()));
		int topY = (int) (e.getDimension().getHeight());
		int kBottom;
		int kTop;
				
		kBottom = (int) (other.getDimension().getHeight() + other.getHeight());
		kTop = (int) (other.getDimension().getHeight());
			
		if ((e.getDimension().getWidth() + e.getWidth()) > other.getDimension().getWidth() - radius && (e.getDimension().getWidth() < other.getDimension().getWidth()  + other.getWidth() + radius))
		{
			if (bottomY >= kTop - height && topY <= kBottom + height)
				return true;
				
			else if (topY <= kBottom + height && topY >= kTop - height)
				return true;			
		}
		
		int kY = (int) other.getDimension().getHeight();
		int yValue = (int) e.getDimension().getHeight();
		
		if (yValue + e.getHeight() > kY && yValue  < kY + other.getHeight())
		{
			if ((e.getDimension().getWidth() + e.getWidth()) >= (other.getDimension().getWidth()) && (e.getDimension().getWidth()) <= (other.getDimension().getWidth() + other.getWidth() + 3))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isLeft(Element e, Element other)
	{
		if (e.getDimension().getWidth() < other.getDimension().getWidth())
			return true;
		
		return false;
	}
	
	
}
