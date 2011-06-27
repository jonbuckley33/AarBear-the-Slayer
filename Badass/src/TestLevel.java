import java.awt.Color;
import java.util.ArrayList;


public class TestLevel extends Level
{
	static int HEIGHT;
	static int WIDTH;
	Color color;
	
	public TestLevel(Window w)
	{
		HEIGHT = w.getHeight();
		WIDTH = w.getWidth();
		
		setColor(color);
		
		ArrayList<Element> walls = new ArrayList<Element>();
		walls.add(new Element(10, HEIGHT-60, 500, 20, Color.blue, "Floor", false));
		walls.add(new Element(300, HEIGHT-200, 200, 20, Color.green, "Floor", false));
		
		this.setWalls(walls);
	}
}
