
public class Weapon
{
	Class bullet;
	int ammo = 20;
	boolean infinite = false;
	String name;
	
	public Weapon(Class bullet, String name)
	{
		this.name = name;
		this.bullet = bullet;
		
		if (bullet == Bullet.class)
		{
			infinite = true;
			ammo = 99;
		}
		
		else if (bullet == GuitarBullet.class)
			ammo = 50;
		
		else if (bullet == ShotgunBullet.class)
			ammo = 30;
		
		else if (bullet == Teleport.class)
			ammo = 10;
		
		else if (bullet == Rocket.class)
			ammo = 5;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAmmo()
	{
		return ammo;
	}
	
	public void setAmmo(int x)
	{
		ammo = x;
	}
	
	public void useAmmo()
	{
		if (!infinite)
			ammo --;
	}
}
