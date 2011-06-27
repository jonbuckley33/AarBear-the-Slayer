import java.awt.Color;
import java.awt.Dimension;


public class NotTangible extends Element
{
	Dimension anchor;
	Color color;
	String name;
	
	public NotTangible(int x, int y, int w, int h, Color c, String nam, boolean grav) 
	{
		super(x, y, w, h, c, nam, grav);
	}
}
