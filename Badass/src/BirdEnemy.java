import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;


public class BirdEnemy extends Enemy
{
	int range;
	Element projectile;
	Dimension orig;
	int steps = 0;
	
	public BirdEnemy(int x, int y, int w, int h, Color c, String nam, boolean grav, ArrayList<Element> elements, Window win, int ran) 
	{
		super(x, y, w, h, c, nam, grav, elements, win);
		range = ran;
		setHasAttack(true);
		setVerticalRange(50);
		range = ran;
		orig = new Dimension(x/5, y/5);
		int steps = 0;
	}
	
	@Override
	public void attack(boolean b)
	{
		setBusy(true);
		if (projectile == null)		
		{
			projectile = new Element((int)getDimension().getWidth()*5 , (int)getDimension().getHeight()*5 + getHeight()*5 + 20, 20, 20, Color.black, "attack", false);
			window.getGrid().addElement(projectile);			
		}
			
		else
		{
			projectile.setDimension((int)projectile.getDimension().getWidth(), (int)projectile.getDimension().getHeight() + 2);
		}
		
		if (window.getController().checkCollisions(window.getBadass(), projectile))
		{
			Badass badass = window.getBadass();
			
			badass.setHealth(badass.getHealth()-1);
			
			if (badass.getDimension().getWidth() < projectile.getDimension().getWidth()) /// whats this do? make it delete projectile after damaging
				badass.save(true, projectile);
			
			else
				badass.save(false, projectile);
			
			Window.getWindow().getGrid().removeElement(projectile);
			projectile = null;
			window.setFlash(true);
		}
		
		else if (projectile.getDimension().getHeight()*5 > Window.getWindow().getHeight())
		{
			Window.getWindow().getGrid().removeElement(projectile);
			projectile = null;
		}
			
	}
	
	public void move(ArrayList<Element> elements)
	{	
		Window.getWindow().getGrid().removeElement(projectile);
		projectile = null;
		
		if (right)
		{
			//destroy();
				
			if (steps >= range)
			{
				right = false;
			}
				
			else				
			{
				steps++;
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
			//destroy();
				
			if (steps == 0)
			{
				right = true;
			}
				
			else
			{
				steps--;
				setDimension((int)getDimension().getWidth() - 1, (int) getDimension().getHeight());
					
				if (window.getController().hitWall(this, window.getGrid().getElements()))
				{
					setDimension((int)getDimension().getWidth() + 1, (int)getDimension().getHeight());
					right = true;
				}
			}
		}
	}
	
	@Override 
	public void destroy()
	{
		super.destroy();
		Window.getWindow().getGrid().removeElement(projectile);
		projectile = null;
	}
	
	

}
