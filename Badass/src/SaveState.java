import java.util.ArrayList;


public class SaveState 
{
	String name;
	ArrayList<Level> completed = new ArrayList<Level>();
	int credits;
	
	public SaveState(String name, Window w)
	{
		this.name = name;
		completed.add(w.getLevel());
	}
	
	public void save(Window w)
	{
		if (!completed.contains(w.getLevel()))
			completed.add(w.getLevel());
	}
	
	public void save(int p)
	{
		credits = p;
	}
	
	public int getCredits()
	{
		return credits;
	}	
	
	public String getName()
	{
		return name;
	}
}
