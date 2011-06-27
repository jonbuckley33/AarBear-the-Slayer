import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class HUD
{
	int health;
	int fuel;
	ArrayList<Weapon> weapons;
	BufferedImage weaponsSelectorImage = null;
	BufferedImage fuelGauge;
	BufferedImage regularImage;
	BufferedImage shotgunImage;
	BufferedImage fuelBar;
	BufferedImage rocketImage;
	BufferedImage healthGauge;
	BufferedImage healthBar;
	BufferedImage guitarImage;
	BufferedImage teleporterImage;
	BufferedImage placeHolder;
	BufferedImage transparentHealthGauge;
	BufferedImage healthDraw;
	BufferedImage weaponIndex;
	int index = 0;
	Badass badass;
	
	
	public HUD(Badass ba)
	{
		badass = ba;
		
		try {fuelGauge = ImageIO.read(new File("FuelBar.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {fuelBar = ImageIO.read(new File("fuelCounter.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {weaponsSelectorImage = ImageIO.read(new File("weaponSelector.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}	
		
		try {weaponIndex = ImageIO.read(new File("weaponIndex.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}	
		
		try {regularImage = ImageIO.read(new File("singleShot.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {shotgunImage = ImageIO.read(new File("shotgun.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {rocketImage = ImageIO.read(new File("rocketselect.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {healthGauge = ImageIO.read(new File("healthGauge.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {healthBar = ImageIO.read(new File("healthCounter.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {guitarImage = ImageIO.read(new File("guitarWeapon.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {teleporterImage = ImageIO.read(new File("TeleporterWeapon.png"));}
		catch (IOException e){System.out.println("*****ERROR**** teleport");}
		
		try {transparentHealthGauge = ImageIO.read(new File("healthGaugeTransparent.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}

		try {placeHolder = ImageIO.read(new File("PlaceHolderWeapon.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
	}
	
	public void draw(Graphics g)
	{
		health = badass.getHealth();
		fuel = badass.getFuel();
		weapons = Window.getWindow().getWeapons();
		index = Window.getWindow().getWeaponIndex();
		
		g.drawImage(weaponsSelectorImage, 10, 10, null);
		g.drawImage(weaponIndex, 10 + 45 * index, 10, null);
		
		g.setColor(Color.black);
		g.drawString(Window.getWindow().getScore() + "", 500, 20);
		
		for (int i = 0; i < weapons.size(); i++)
		{
			String s = weapons.get(i).getName();
			
			if (s.compareTo("regular")==0)
				g.drawImage(regularImage, 17 + (i*45), 17, null);
			
			else if (s.compareTo("shotgun")==0)
				g.drawImage(shotgunImage, 17 + (i*45), 17, null);
			
			else if (s.compareTo("rocket")==0)
				g.drawImage(rocketImage, 17 + (i*45), 17, null);
			
			else if (s.compareTo("guitar")==0)
				g.drawImage(guitarImage, 17 + (i*45), 17, null);
			
			else if (s.compareTo("teleport")==0)
				g.drawImage(teleporterImage, 17 + (i*45), 17, null);
			
			else
				g.drawImage(placeHolder, 17 + (i*45), 17, null);
			
			g.drawString(weapons.get(i).getAmmo() + " ", 32 + (i*45), 55);
			
		}
		
		g.drawImage(fuelGauge, 25, Window.getWindow().getHeight()-41, null);
		for (int i = 0; i < fuel; i++)
			g.drawImage(fuelBar, (i*15) + 80 , Window.getWindow().getHeight()-33, null);
		
		if (Window.getWindow().getController().spaceInvalid(new Element(Window.getWindow().getWidth()-210, Window.getWindow().getHeight()-41, healthGauge.getWidth(), healthGauge.getHeight(), Color.green, "nothing", false)))
			healthDraw = transparentHealthGauge;
		
		else
			healthDraw = healthGauge;
		
		g.drawImage(healthDraw, Window.getWindow().getWidth()-210, Window.getWindow().getHeight()-41, null);
		for (int i = 0; i < health; i++)
			g.drawImage(healthBar, (i*18) + Window.getWindow().getWidth()-145 , Window.getWindow().getHeight()-34, null);
	}
	
	public void decreaseFuel()
	{
		fuel--;
	}
	
	public void increaseFuel()
	{
		fuel++;
	}
	
	public void decreaseHealth()
	{
		health--;
	}
	
	public void increaseHealth()
	{
		health++;
	}
	
	public void setFuel(int x)
	{
		fuel = x;
	}
	
	public void setHealth(int x)
	{
		health = x;
	}
	
	public void setWeapons(ArrayList<Weapon> toSet)
	{
		weapons = toSet;
	}
}
