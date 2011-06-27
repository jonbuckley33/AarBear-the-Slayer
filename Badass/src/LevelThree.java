import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class LevelThree extends Level
{
	int HEIGHT;
	int WIDTH;
	BufferedImage bg;
	BufferedImage flagImage;
	ArrayList<NotTangible> scene = new ArrayList<NotTangible>();
	
	public LevelThree(Window w)
	{
		HEIGHT = w.getHeight();
		WIDTH = w.getWidth();
		
		try {bg = ImageIO.read(new File("background.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {flagImage = ImageIO.read(new File("flagFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		getScenery().clear();
		getWalls().clear();
		
		
		setBackground(bg);
		
		ArrayList<Element> things = new ArrayList<Element>();
		things.add(new EarthFloor(0, 500, 200, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(600, 100, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(1000, 100, 250, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(1400, 500, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(2000, 200, 400, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(2800, 500, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(3100, 500, 400, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(3400, 100, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(3600, 200, 200, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(4000, 100, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(4200, 300, 400, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(4800, 100, 250, 20, Color.blue, "Floor", false));
		
		EarthFloor finish = new EarthFloor(6500, 500, 300, 20, Color.blue, "Floor", false);
		things.add(new Element(3800, 0, 10, Window.getWindow().getHeight(), Color.BLACK, "Wall", false));
		
		
		NotTangible flag = new NotTangible(6550, 500 - flagImage.getHeight(), 10, 100, Color.blue, "Flag", false);		
		NotTangible flag2 = new NotTangible(6770, 500 - flagImage.getHeight(), 10, 100, Color.blue, "Flag", false);
		flag.setImage(flagImage);
		flag2.setImage(flagImage);
		
		scene.add(flag2);
		scene.add(flag);
		
		setScenery(scene);
		things.add(finish);
		setFinish(finish);
		
		
		BearEnemy bear = new BearEnemy(2100, 100, 20, 20, Color.green, "Enemy", true, things, w);
		things.add(bear);
		
		BearEnemy bear2 = new BearEnemy(2300, 100, 20, 20, Color.green, "Enemy", true, things, w);
		things.add(bear2);
		
		SkunkEnemy skunk = new SkunkEnemy(2900, 400, 20, 20, Color.green, "Enemy", true, things, w);	
		things.add(skunk);
		
		SkunkEnemy skunk2 = new SkunkEnemy(4850, 50, 20, 20, Color.green, "Enemy", true, things, w);	
		things.add(skunk2);
		
		SkunkEnemy skunk3 = new SkunkEnemy(5000, 50, 20, 20, Color.green, "Enemy", true, things, w);	
		things.add(skunk3);
		
		BearEnemy bear3 = new BearEnemy(4925, 20, 20, 20, Color.green, "Enemy", true, things, w);
		things.add(bear3);
		
		TeleportPickup teleport = new TeleportPickup(700, 75, 10, 10, Color.green, "Item", true, w);
		things.add(teleport);
		
		GuitarPickup guitar = new GuitarPickup(2300, 150, 10, 10, Color.green, "Item", true, w);
		things.add(guitar);
		
		HealthPack health = new HealthPack(2350, 175, 30, 30, Color.red, "Item", true, w);
		things.add(health);
		
		FuelPack fuel = new FuelPack(5010, 50, 30, 30, Color.red, "Item", true, w);
		things.add(fuel);
		
		BirdEnemy bird = new BirdEnemy(4200, 200, 50, 20, Color.green, "Enemy", false, things, w, 300/5);
		things.add(bird);
		
		this.setWalls(things);
	}
	
	@Override
	public void reset()
	{
		Window w = Window.getWindow();
		
		try {bg = ImageIO.read(new File("background.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {flagImage = ImageIO.read(new File("flagFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setBackground(bg);
		
		ArrayList<Element> things = new ArrayList<Element>();
		things.add(new EarthFloor(0, 500, 200, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(600, 100, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(1000, 100, 250, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(1400, 500, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(2000, 200, 400, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(2800, 500, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(3100, 500, 400, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(3400, 100, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(3600, 200, 200, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(4000, 100, 150, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(4200, 300, 400, 20, Color.blue, "Floor", false));
		things.add(new EarthFloor(4800, 100, 250, 20, Color.blue, "Floor", false));
		
		EarthFloor finish = new EarthFloor(6500, 500, 300, 20, Color.blue, "Floor", false);
		things.add(new Element(3800, 0, 10, Window.getWindow().getHeight(), Color.BLACK, "Wall", false));
		
		
		NotTangible flag = new NotTangible(6550, 500 - flagImage.getHeight(), 10, 100, Color.blue, "Flag", false);		
		NotTangible flag2 = new NotTangible(6770, 500 - flagImage.getHeight(), 10, 100, Color.blue, "Flag", false);
		flag.setImage(flagImage);
		flag2.setImage(flagImage);
		
		scene.add(flag2);
		scene.add(flag);
		
		setScenery(scene);
		things.add(finish);
		setFinish(finish);
		
		
		BearEnemy bear = new BearEnemy(2100, 100, 20, 20, Color.green, "Enemy", true, things, w);
		things.add(bear);
		
		BearEnemy bear2 = new BearEnemy(2300, 100, 20, 20, Color.green, "Enemy", true, things, w);
		things.add(bear2);
		
		SkunkEnemy skunk = new SkunkEnemy(2900, 400, 20, 20, Color.green, "Enemy", true, things, w);	
		things.add(skunk);
		
		SkunkEnemy skunk2 = new SkunkEnemy(4850, 50, 20, 20, Color.green, "Enemy", true, things, w);	
		things.add(skunk2);
		
		SkunkEnemy skunk3 = new SkunkEnemy(5000, 50, 20, 20, Color.green, "Enemy", true, things, w);	
		things.add(skunk3);
		
		BearEnemy bear3 = new BearEnemy(4925, 20, 20, 20, Color.green, "Enemy", true, things, w);
		things.add(bear3);
		
		TeleportPickup teleport = new TeleportPickup(700, 75, 10, 10, Color.green, "Item", true, w);
		things.add(teleport);
		
		GuitarPickup guitar = new GuitarPickup(2300, 150, 10, 10, Color.green, "Item", true, w);
		things.add(guitar);
		
		HealthPack health = new HealthPack(2350, 175, 30, 30, Color.red, "Item", true, w);
		things.add(health);
		
		FuelPack fuel = new FuelPack(5010, 50, 30, 30, Color.red, "Item", true, w);
		things.add(fuel);
		
		BirdEnemy bird = new BirdEnemy(4200, 200, 50, 20, Color.green, "Enemy", false, things, w, 300/5);
		things.add(bird);
		
		this.setWalls(things);
	}
}
