
public class Mass 
{
	int time;
	
	public Mass()
	{
		time = 0;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public void increaseTime()
	{
		time++;
	}
	
	public void resetTime()
	{
		time = 0;
	}
	
	public int getSpeed()
	{
		int speed;
		
		if (time < 8)
			speed = 1;
		
		else if (time < 15)
			speed = 2;
		
		else
			speed = 3;
		
		return speed;
			
	}
}
