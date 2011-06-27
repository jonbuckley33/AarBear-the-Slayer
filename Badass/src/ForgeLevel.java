import java.util.ArrayList;


public class ForgeLevel extends Level
{
	ArrayList<Element> walls;
	
	public ForgeLevel()
	{
		walls = new ArrayList<Element>();
	}
	
	public void addWall(Element e)
	{
		walls.add(e);
		setWalls(walls);
	}
	
	public void finalize()
	{
		for (int i = 0; i < walls.size(); i++)
		{
			Element e = walls.get(i);
			
			e.setDimension((int)e.getDimension().getWidth()*5,(int) e.getDimension().getHeight());
			
			if (e instanceof EarthFloor)
				e.setWidth(e.getWidth()*5);
			
		}
		setWalls(walls);
	}
}
