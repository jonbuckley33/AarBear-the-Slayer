import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class LevelTwo extends Level
{
	int HEIGHT;
	int WIDTH;
	BufferedImage bg;
	BufferedImage flagImage;
	BufferedImage flagFlippedImage;
	ArrayList<NotTangible> scene = new ArrayList<NotTangible>();
	
	public LevelTwo(Window w)
	{
		HEIGHT = w.getHeight();
		WIDTH = w.getWidth();
		
		color = Color.CYAN;
		setColor(color);
		
		try {bg = ImageIO.read(new File("background.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {flagImage = ImageIO.read(new File("flag.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {flagFlippedImage = ImageIO.read(new File("flagFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setBackground(bg);
		
		ArrayList<Element> walls = new ArrayList<Element>();
		walls.add(new EarthFloor(0, 150, 100, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(100, 520, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(350, 280, 300, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(650, 320, 100, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(800, 80, 200, 20, Color.blue, "Floor", false));
		
		EarthFloor badguyFloor = new EarthFloor(1000, 580, 200, 20, Color.blue, "Floor", false);
		walls.add(badguyFloor);
		walls.add(new EarthFloor(1200, 420, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(1400, 260, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(1600, 100, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(2600, 100, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(2900, 540, 40, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(3300, 100, 250, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(3700, 200, 100, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(4200, 400, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(4600, 250, 40, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(5000, 100, 200, 20, Color.blue, "Floor", false));

		
		EarthFloor finish = new EarthFloor(5300, 250, 300, 20, Color.blue, "Floor", false);
		NotTangible flag = new NotTangible(5330, 250 - flagFlippedImage.getHeight(), 10, 100, Color.blue, "Flag", false);		
		NotTangible flag2 = new NotTangible(5570, 250 - flagFlippedImage.getHeight(), 10, 100, Color.blue, "Flag", false);
		flag.setImage(flagFlippedImage);
		flag2.setImage(flagFlippedImage);

		scene.add(flag2);
		scene.add(flag);
		
		setScenery(scene);
		walls.add(finish);
		setFinish(finish);

		MovingPlatform test = new MovingPlatform(1800, 300, 400, 20, Color.blue, "Floor", false, 10, false);
		
		
		BearEnemy bear = new BearEnemy(1000, 500, 20, 20, Color.green, "Enemy", true, walls, w);	
		bear.setElement(badguyFloor);
		
		walls.add(test);
		
		Image earthFloor = null;
		
		SkunkEnemy skunk = new SkunkEnemy(1400, 100, 20, 20, Color.green, "Enemy", true, walls, w);	
		walls.add(skunk);
		
		SkunkEnemy skunk2 = new SkunkEnemy(3300, 50, 20, 20, Color.green, "Enemy", true, walls, w);	
		walls.add(skunk2);
		
		SkunkEnemy skunk4 = new SkunkEnemy(3380, 50, 20, 20, Color.green, "Enemy", true, walls, w);	
		walls.add(skunk4);
		
		SkunkEnemy skunk3 = new SkunkEnemy(3700, 100, 20, 20, Color.green, "Enemy", true, walls, w);	
		walls.add(skunk3);
		
		BearEnemy bear2 = new BearEnemy(3360, 20, 20, 20, Color.green, "Enemy", true, walls, w);
		walls.add(bear2);

		
		walls.add(bear);
		
		FuelPack fuel = new FuelPack(100, 500, 30, 30, Color.red, "Item", true, w);
		walls.add(fuel);
		
		GuitarPickup guitar = new GuitarPickup(1250, 360, 10, 10, Color.green, "Item", true, w);
		walls.add(guitar);
		
		TeleportPickup teleport = new TeleportPickup(3300, 20, 10, 10, Color.green, "Item", true, w);
		walls.add(teleport);
		
		BluePick blue = new BluePick(1000, 550, 30, 30, Color.red, "Item", true, w);
		walls.add(blue);
		
		HealthPack health = new HealthPack(1300, 200, 30, 30, Color.red, "Item", true, w);
		walls.add(health);
		
		BirdEnemy bird = new BirdEnemy(600, 100, 50, 20, Color.green, "Enemy", false, walls, w, 20);
		walls.add(bird);
		
		this.setWalls(walls);
		setSave(this);
	}
	
	@Override
	public void reset()
	{
		Window w = Window.getWindow();
		HEIGHT = w.getHeight();
		WIDTH = w.getWidth();
		
		color = Color.CYAN;
		setColor(color);
		
		getScenery().clear();
		getWalls().clear();
		
		try {bg = ImageIO.read(new File("background.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {flagImage = ImageIO.read(new File("flag.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {flagFlippedImage = ImageIO.read(new File("flagFlipped.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		setBackground(bg);
		
		ArrayList<Element> walls = new ArrayList<Element>();
		walls.add(new EarthFloor(0, 150, 100, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(100, 520, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(350, 280, 300, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(650, 320, 100, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(800, 80, 200, 20, Color.blue, "Floor", false));
		
		EarthFloor badguyFloor = new EarthFloor(1000, 580, 200, 20, Color.blue, "Floor", false);
		walls.add(badguyFloor);
		walls.add(new EarthFloor(1200, 420, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(1400, 260, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(1600, 100, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(2600, 100, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(2900, 540, 40, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(3300, 100, 250, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(3700, 200, 100, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(4200, 400, 200, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(4600, 250, 40, 20, Color.blue, "Floor", false));
		walls.add(new EarthFloor(5000, 100, 200, 20, Color.blue, "Floor", false));

		
		EarthFloor finish = new EarthFloor(5300, 250, 300, 20, Color.blue, "Floor", false);
		NotTangible flag = new NotTangible(5330, 250 - flagFlippedImage.getHeight(), 10, 100, Color.blue, "Flag", false);		
		NotTangible flag2 = new NotTangible(5570, 250 - flagFlippedImage.getHeight(), 10, 100, Color.blue, "Flag", false);
		flag.setImage(flagFlippedImage);
		flag2.setImage(flagFlippedImage);

		scene.add(flag2);
		scene.add(flag);
		
		setScenery(scene);
		walls.add(finish);
		setFinish(finish);

		MovingPlatform test = new MovingPlatform(1800, 300, 400, 20, Color.blue, "Floor", false, 10, false);
		
		
		BearEnemy bear = new BearEnemy(1000, 500, 20, 20, Color.green, "Enemy", true, walls, w);	
		bear.setElement(badguyFloor);
		
		walls.add(test);
		
		Image earthFloor = null;
		
		SkunkEnemy skunk = new SkunkEnemy(1400, 100, 20, 20, Color.green, "Enemy", true, walls, w);	
		walls.add(skunk);
		
		SkunkEnemy skunk2 = new SkunkEnemy(3300, 50, 20, 20, Color.green, "Enemy", true, walls, w);	
		walls.add(skunk2);
		
		SkunkEnemy skunk4 = new SkunkEnemy(3380, 50, 20, 20, Color.green, "Enemy", true, walls, w);	
		walls.add(skunk4);
		
		SkunkEnemy skunk3 = new SkunkEnemy(3700, 100, 20, 20, Color.green, "Enemy", true, walls, w);	
		walls.add(skunk3);
		
		BearEnemy bear2 = new BearEnemy(3360, 20, 20, 20, Color.green, "Enemy", true, walls, w);
		walls.add(bear2);

		
		walls.add(bear);
		
		FuelPack fuel = new FuelPack(100, 500, 30, 30, Color.red, "Item", true, w);
		walls.add(fuel);
		
		GuitarPickup guitar = new GuitarPickup(1250, 360, 10, 10, Color.green, "Item", true, w);
		walls.add(guitar);
		
		TeleportPickup teleport = new TeleportPickup(3300, 20, 10, 10, Color.green, "Item", true, w);
		walls.add(teleport);
		
		BluePick blue = new BluePick(1000, 550, 30, 30, Color.red, "Item", true, w);
		walls.add(blue);
		
		HealthPack health = new HealthPack(1300, 200, 30, 30, Color.red, "Item", true, w);
		walls.add(health);
		
		BirdEnemy bird = new BirdEnemy(600, 100, 50, 20, Color.green, "Enemy", false, walls, w, 20);
		walls.add(bird);
		
		this.setWalls(walls);
		setSave(this);
	}
}
