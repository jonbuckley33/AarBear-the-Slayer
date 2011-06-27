import java.util.ArrayList;


public class SaveFile 
{
	String name;
	int credits;
	ArrayList<Weapon> weapons;
	
	public SaveFile(String name)
	{
		weapons = new ArrayList<Weapon>();
		this.name = name;
		weapons.add(new Weapon(Bullet.class, "regular"));
		weapons.add(new Weapon(ShotgunBullet.class, "shotgun"));
		weapons.add(new Weapon(Rocket.class, "rocket"));
	}
	
	public void setCredits(int x)
	{
		credits = x;
	}
	
	public void addCredits(int x)
	{
		credits += x;
	}
	
	public void setWeapons(ArrayList<Weapon> w)
	{
		weapons = w;
	}
	
	public void addWeapons(Weapon x)
	{
		weapons.add(x);
	}
	
	public ArrayList<Weapon> getWeapons()
	{
		return weapons;
	}
	
	public int getCredits()
	{
		return credits;
	}
}
