import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class Element 
{
	private Dimension d;
	private int width;
	private int height;
	private Color color;
	private String name;
	private boolean gravity;
	private int time = 0;
	private int start;
	private int angle;
	private boolean onGround = false;
	private Mass mass;
	Image image;
	private boolean imaged;
	private boolean busy = false;
	private boolean moving = false;
	private Dimension last;
	
	
	public Element(String nam)
	{
		name = nam;
	}
	
	public Element(int x, int y, int w, int h, Color c, String nam, boolean grav)
	{
		d = new Dimension(x/5,y/5);
		width = w/5;
		height = h/5;
		color = c;
		name = nam;
		gravity = grav;
		start = (int)System.currentTimeMillis()/100;
		
		if (grav)
			angle = 270;
		
		else
			angle = -1;
		
		mass = new Mass();
	}
	
	public Element(Dimension d, int w, int h)
	{
		d = new Dimension((int)d.getWidth()/5,(int) d.getHeight()/5);
		width = w/5;
		height = h/5;
	}
	
	public Dimension getDimension()
	{
		return d;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isGravity()
	{
		return gravity;
	}
	
	public void setDimension(Dimension x)
	{
		d = x;
	}
	
	public void setDimension(int x, int y)
	{
		d.setSize(x, y);
	}
	
	public void setColor(Color c)
	{
		color = c;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public void setWidth(int x)
	{
		width = x;
	}
	
	public void setHeight(int y)
	{
		height = y;
	}
	
	public void setName(String nam)
	{
		name = nam;
	}
	
	public void setGravity(boolean b)
	{
		gravity = b;
	}
	
	public void fall()
	{
		if (gravity)
			time = (int)System.currentTimeMillis()/100 - start; /// tenth seconds
	}
	
	public int getAngle()
	{
		return angle;
	}
	
	public void setOnGround(Boolean b)
	{
		onGround = b;
	}
	
	public boolean onGround()
	{
		return onGround;
	}
	
	public boolean outOfBoard(int l, int w)
	{
		if (d.getHeight() + height > (l/5) + height)
			return true;
		
		if (d.getWidth() + width > (w/5) + height || d.getWidth() < 0)
			return true;
		
		return false;
	}
	
	public void setRandomColor()
	{
		color = new Color((int) (Math.random()*255),
				(int) (Math.random()*255),
				(int) (Math.random()*255));
	}
	
	public Mass getMass()
	{
		return mass;
	}
	
	public void setImage(Image img)
	{
		image = img;
		imaged = true;
	}

	public Image getImage()
	{
		return image;
	}
	
	public boolean hasImage()
	{
		if (imaged)
			return true;
		
		return false;
	}
	
	public void setHasImage(boolean b)
	{
		imaged = b;
	}
	
	public void setMoving(boolean b)
	{
		moving = b;
	}
	
	public boolean getMoving()
	{
		return moving;
	}
	
	public Dimension getLast()
	{
		return last;
	}
	
	public void setLast(Dimension l)
	{
		last = l;
	}
	
	public void draw(Graphics g)
	{
		if (hasImage())
			g.drawImage(getImage(), (int) getDimension().getWidth()*5, (int) getDimension().getHeight()*5, null);
		
		else
		{
			g.setColor(getColor());
			g.fillRect((int) getDimension().getWidth()*5, (int) getDimension().getHeight()*5, getWidth()*5, getHeight()*5);
		}
	}
	
	public boolean isBusy()
	{
		return busy;
	}
	
	public void setBusy(boolean b)
	{
		busy = b;
	}
}
