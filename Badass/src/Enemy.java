import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;


public class Enemy extends Element
{
	int health = 1;
	int damage = 1;
	Element element = null;
	boolean right;
	Image reversed;
	Image original;
	Window window;
	boolean projectile = false;
	boolean attack;
	int xRange = 25;
	int yRange = 0;
	
	public Enemy(int x, int y, int w, int h, Color c, String nam, boolean grav, ArrayList<Element> elements, Window win)
	{
		super(x, y, w, h, c, nam, grav);
		right = Math.random() > .5;
		findElement(elements);
		window = win;
	}
	
	public Enemy(Dimension d, int w, int h) 
	{
		super(d, w, h);
		right = Math.random() > .5;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int x)
	{
		health = x;
	}
	
	public void setReverseImage(Image img)
	{
		reversed = img;
	}
	
	public void move()
	{
		if (!hasElement())
			findElement(Window.getWindow().getGrid().getElements());
		
		if (attackRange(xRange))
			attack(window.getController().isLeft(this, window.getBadass()));   /// if enemy is left of badass
		
		else
			setBusy(false);

		if (!isBusy())
			move(Window.getWindow().getGrid().getElements());
	}
	
	public void move(ArrayList<Element> elements)
	{		
		if (right)
		{
			if (getDimension().getWidth()+getWidth() > element.getDimension().getWidth() + element.getWidth())
			{
				right = false;
			}
			
			else
			{
				setDimension((int)getDimension().getWidth() + 1, (int)getDimension().getHeight());
				
				if (window.getController().hitWall(this, window.getGrid().getElements()))
				{
					setDimension((int)getDimension().getWidth() - 1, (int)getDimension().getHeight());
					right = false;
				}

			}
		}
		
		else if (!right)
		{
			if (getDimension().getWidth() - 1 < element.getDimension().getWidth())
			{
				right = true;
			}
			
			else
			{
				setDimension((int)getDimension().getWidth() - 1, (int) getDimension().getHeight());
				
				if (window.getController().hitWall(this, window.getGrid().getElements()))
				{
					setDimension((int)getDimension().getWidth() + 1, (int)getDimension().getHeight());
					right = true;
				}
			}
		}

	}
	
	public void setElement(Element e)
	{
		element = e;
	}
	
	public Element getElement()
	{
		return element;
	}
	
	public boolean hasElement()
	{
		return (element != null);
	}
	
	public void findElement(ArrayList<Element> elements)
	{
		boolean contained = false;
		
		if (elements.contains(this))
		{
			elements.remove(this);
			contained = true;
		}
		
		for (int i = 0; i < elements.size(); i++)
		{
			Element e = elements.get(i);
			if (e.getDimension().getWidth() < this.getDimension().getWidth() + this.getWidth() && e.getDimension().getWidth() + e.getWidth() > this.getDimension().getWidth())
			{
				if (e.getDimension().getHeight() > getDimension().getHeight() + getHeight())
				this.setElement(elements.get(i));
				return;
			}
		}
		
		if (contained)
			elements.add(this);
	}

	@Override
	public Image getImage()
	{
		if (!right)
			return original;
		
		else
			return reversed;
	}
	
	@Override
	public void setImage(Image img)
	{
		setHasImage(true);
		original = img;
	}
	
	public Window getWindow()
	{
		return window;
	}
	
	public boolean projectileCapable()
	{
		return projectile;
	}

	public void setProjectileCapable(boolean b)
	{
		projectile = b;
	}
	
	public void attack(boolean b)
	{
		if (b)
			setRight(true);
		
		else 
			setRight(false);
	}

	public boolean isFacingRight()
	{
		return right;
	}

	public void setRight(boolean b)
	{
		if (right != b)
			right = b;
	}

	public void destroy()
	{
		Window.getWindow().getGrid().removeElement(this);
	}

	public boolean hasAttack()
	{
		return attack;
	}
	
	public void setHasAttack(boolean b)
	{
		attack = b;
	}

	public boolean attackRange(int x)
	{
		if (yRange == 0)
		{
			if (window.getController().isWithin(this, window.getBadass(), x))
				return true;
		
			else
				return false;
		}
		
		else
		{
			if (window.getController().isWithin(this, window.getBadass(), x, yRange))
				return true;
		
			else
				return false;
		}
			
	}
	
	public void setHorizontalRange(int x)
	{
		xRange = x;
	}
	
	public void setVerticalRange(int y)
	{
		yRange = y;
	}
	
	public int getRange()
	{
		return xRange;
	}
}

