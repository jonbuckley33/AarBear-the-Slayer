import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JApplet;



public class Window extends JApplet implements Runnable, KeyListener, MouseListener
{
	private static int HEIGHT = 600;
	private static int WIDTH = 1000;
	private Grid grid;
	private Physics controller;
	private ArrayList<Element> elements;
	private ArrayList<NotTangible> scenery;
	private ArrayList<Element> gravities;
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> items;
	private Badass badass;
	private boolean rightDown;
	private boolean leftDown;
	private boolean spaceDown;
	private boolean upDown;
	private boolean pause = false;
	private boolean bulletActive = false;
	private int start;
	private int health;
	private int time;
	private int jumpCount = 0;
	private boolean gameOver = false;
	private boolean complete = false;
	private boolean loading = false;
	private Badass temp;
	private Color bg;
	private boolean flash = false; 
	private boolean rightLast = true;
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	private ArrayList<MovingPlatform> movers = new ArrayList<MovingPlatform>();
	private boolean offTurn = true;
	private HUD hud;
	private int score = 0;
	private int weaponIndex = 0;
	static Window windowInstance;
	//SaveManager saver;
	SaveFile save;
	Level level = null;
	Image badassFlipped = null;
	Image badassImg = null;
	Image fuelText = null;
	Image weaponsSelectorImage = null;
	Image regularImage = null;
	Image shotgunImage = null;
	Image guitarImage = null;
	BufferedImage levelComplete;
	LevelSelector leveler;
	NotTangible weaponsSelector;
	Forge forge;

	public void keyPressed(KeyEvent arg0)
	{
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightDown = true;
			rightLast = true;
			leftDown = false;
		}
			
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftDown = true;
			rightLast = false;
			rightDown = false;
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE)
		{
			spaceDown = true;
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_UP)
		{
			upDown = true;
		}
	}

	public void keyReleased(KeyEvent arg0)
	{
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			rightDown = false;
			
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT)
			leftDown = false;
		
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE)
			spaceDown = false;
		
		if (arg0.getKeyCode() == KeyEvent.VK_UP)
			upDown = false;
		
	}

	public void keyTyped(KeyEvent arg0) 
	{
		if (arg0.getKeyChar() == 'a')
		{
			System.out.println("pressed");
			shiftWeapons();
		}
		
		if (arg0.getKeyChar() == 'r')
		{
			reset();
		}
		
		if (arg0.getKeyChar() == '1')
		{
			weaponIndex = 0;
		}
		
		if (arg0.getKeyChar() == '2')
		{
			weaponIndex = 1;
		}
		
		if (arg0.getKeyChar() == '3')
		{
			weaponIndex = 2;
		}
		
		if (arg0.getKeyChar() == '4')
		{
			weaponIndex = 3;
		}
		
		if (arg0.getKeyChar() == '5')
		{
			weaponIndex = 4;
		}
		
		if (arg0.getKeyChar() == 'w')
		{
			complete = true;
		}
		
		
	}

	public void start()
	{
		Thread th = new Thread(this);
		th.start();
		
		start = (int) System.currentTimeMillis()/100;
	}

	public void init()
	{
		windowInstance = this;
		
		StartScreen start = new StartScreen();
		
		while (level == null)
		{System.out.print("");}
				
		grid = new Grid(WIDTH, HEIGHT);
		bg = level.getColor();
		scenery = level.getScenery();
		
		elements = level.getWalls();
		grid.setElements(elements);
		
		enemies = new ArrayList<Enemy>();
		for (Element e : elements)
		{
			if (e instanceof Enemy)
				enemies.add((Enemy) e);
		}
				
		items = new ArrayList<Item>();
		for (Element e : elements)
		{
			if (e instanceof Item)
				items.add((Item) e);
		}
		
		for (Element e : elements)
		{
			if (e instanceof MovingPlatform)
				 movers.add((MovingPlatform) e);
		}	
		
		badass = new Badass(0, 0, 20, 20, Color.red, "Ball", true);
		
		try {fuelText = ImageIO.read(new File("FuelText.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {weaponsSelectorImage = ImageIO.read(new File("weaponsSelector.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {regularImage = ImageIO.read(new File("singleShot.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {shotgunImage = ImageIO.read(new File("shotgun.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {guitarImage = ImageIO.read(new File("guitarWeapon.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {levelComplete = ImageIO.read(new File("levelComplete.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		health = badass.getHealth();

		grid.addElement(badass);
		hud = new HUD(badass);
		
		controller = new Physics(1, 1, this);
		
		elements = grid.getElements();
		gravities = grid.getGravities();
		
		save = new SaveFile("001");
		weapons = save.getWeapons();
		score = save.getCredits();
		
		//weapons.add("teleport");
		
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setVisible(true);
		requestFocus(true);
		addKeyListener(this);
		addMouseListener(this);
	}
	
	public void run() 
	{	
		while (true)
		{	
			while (pause)
			{
				try {Thread.sleep(100);} 
				catch (InterruptedException e) {e.printStackTrace();}
			}
						
			if (badass.getDimension().getWidth()*5  > WIDTH/2 + WIDTH*.1)
			{
				level.shift((int) (badass.getDimension().getWidth()*5 - (WIDTH/2 + WIDTH*.1)));
				elements = level.getWalls();
				grid.setElements(elements);
			}
			
			if (badass.getHurt() > 0)
				badass.setHealth(badass.getHealth()-1);
			
			elements = grid.getElements();
			gravities = grid.getGravities();
			
			controller.dropElements(gravities);
			controller.dropElements(gravities);
			
			for (int i = 0; i < movers.size(); i++)
			{
				MovingPlatform e = movers.get(i);
				Dimension revert = new Dimension((int) e.getDimension().getWidth(),(int) e.getDimension().getHeight());
					
				e.move();				
				if (controller.spaceInvalid(e, elements))			
				{
					e.flip();
					e.setDimension(revert);
				}
			}
			
			if (badass.onGround())
			{
				jumpCount = (int)System.currentTimeMillis()/100;			
				badass.setFuel(badass.getFuel()+1);
			}
			
			if (rightDown)
			{	
				badass.moveRight();
			}

			if (leftDown)		
				badass.moveLeft();

			
			if (upDown)
				badass.jump(controller.getGravity());
			
			if (spaceDown)
			{
				if (!badass.isShooting() && !bulletActive && weapons.get(weaponIndex).getAmmo() > 0)   // no bullet active and badass isnt shooting 
				{
					weapons.get(weaponIndex).useAmmo();
					badass.shoot(rightLast, weapons.get(weaponIndex).getName());
					grid.addElement(badass.getBullet());	
				}
				
				else if (bulletActive && weapons.get(weaponIndex).getAmmo() > 0)						// bullet currently on field and space is pressed --- wants to shoot new
				{
					badass.getBullet().destroy();
					grid.removeElement(badass.getBullet());
					
					weapons.get(weaponIndex).useAmmo();
					
					badass.setShooting(false);
					badass.shoot(rightLast, weapons.get(weaponIndex).getName());
					
					grid.addElement(badass.getBullet());
					
					bulletActive = false;
				}
				
				else if (badass.isShooting() && !badass.getBullet().isBusy())   // space is down and bullet is en route--- continue path
				{
					if (!badass.getBullet().outOfBoard(HEIGHT, WIDTH))
						badass.moveBullet();
					
					else  
					{
						grid.removeElement(badass.getBullet());
						badass.setShooting(false);
					}
				}
			}
						
			if (!spaceDown && badass.isShooting() && !bulletActive)	//bullet is en route, but space is released
			{
				if (badass.getBullet().stop(this))
					bulletActive = true;
			}
			
			else if (badass.isShooting() && !bulletActive && badass.getBullet().isBusy())
			{
				if (badass.getBullet().stop(this))
					bulletActive = true;
			}

			if (badass.getDimension().getWidth() <= 0)
				badass.setDimension(0, (int) badass.getDimension().getHeight());
			
			else if (badass.outOfBoard(HEIGHT, WIDTH) || badass.getHealth() <= 0)
				gameOver = true;
			
			
			for (Enemy e : enemies)
			{
				if (badass.isShooting())
				{						
					if (controller.checkCollisions(badass.getBullet(), e))
					{
						Bullet bullet = badass.getBullet();
						bullet.damage();
						e.setHealth(e.getHealth()-1);
					}
				}
								
				if (e.getHealth() > 0  && offTurn)
					e.move();
				
				if (controller.checkCollisions(badass, e))
				{
					if (badass.setHealth(badass.getHealth()-1))
						flash = true;
						
					if (badass.getDimension().getWidth() < e.getDimension().getWidth())
						badass.save(true, e);
					
					else
						badass.save(false, e);
					
				}
			}
			
			if (badass.getDimension().getWidth() > level.getFinish().getDimension().getWidth() + level.getFinish().getWidth()/2)
				complete = true;
			
			
			//controller.maintainTraction(elements);
			
			offTurn = !offTurn;
			
			for (Item i : items)
			{
				if (controller.checkCollisions(badass, i))
				{
					i.act();
					i.setUsed(true);
				}	
			}
				
			for (int i = 0; i < enemies.size(); i++)
			{					
				if (enemies.get(i).getHealth()==0)					
				{
					enemies.get(i).destroy();
					grid.removeElement(enemies.get(i));
					enemies.remove(i);
				}
			}
			
			for (int i = 0; i < items.size(); i++)
			{
				if (items.get(i).isUsed())
				{
					grid.removeElement(items.get(i));
					items.remove(i);
				}
			}
			
			repaint();
			
			try {Thread.sleep(50);} 
			
			catch (InterruptedException e) {}
		}	
	}

	public void paint(Graphics g)
	{
		if (loading)
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		
		else if (!gameOver)
		{	
			if (complete)
			{
				setVisible(false);
				Store store = new Store(save);
				//g.drawImage(levelComplete, 0, 0, this);
			}
			
			else 
			{
				if (level.getBackground() == null)
				{
					g.setColor(level.getColor());
					g.fillRect(0, 0, WIDTH, HEIGHT);
				}
				
				else
					g.drawImage(level.getBackground(),0,0, this);
				
				g.setColor(Color.black);
				g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
				
				if (grid != null)
				{
					badass.setRight(rightLast);
					
					for (int i = 0; i < scenery.size(); i++)
					{
						NotTangible e = scenery.get(i);	
						e.draw(g);
					}
					
					
					for (int i = 0; i < elements.size(); i++)
					{
						Element e = elements.get(i);
						
						if (e.getName().compareTo("Floor") == 0)
						{
							if (e instanceof MovingPlatform)
							{
								g.setColor(Color.blue);
								g.fillRect((int)e.getDimension().getWidth()*5, (int)e.getDimension().getHeight()*5, e.getWidth()*5, e.getHeight()*5);
							}
							
							for (int x = 0; x < (e.getWidth()*5)/5; x++)
								g.drawImage(e.getImage(), (int) e.getDimension().getWidth()*5 + (x*5), (int) e.getDimension().getHeight()*5, this);
						}
						
						else if (!(e instanceof Badass))
							e.draw(g);
						
					}
					
					hud.draw(g);
					
					badass.draw(g);
				}
				
				if (flash)
				{
					g.setColor(Color.white);
					g.fillRect(0, 0, WIDTH, HEIGHT);
					
					flash = false;
				}
			}
		}
		
		else
		{
			setVisible(false);
			Gameover gover = new Gameover();
		}
		
	}
		
	public Level getLevel() 
	{
		return level;
	}
	
	public Grid getGrid()
	{
		return grid;
	}
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	public Badass getBadass()
	{
		return badass;
	}
	 
	public void shiftWeapons()
	{
		Weapon temp = weapons.get(0);
		weapons.add(temp);
		
		weapons.remove(0);
	}
	
	public void addWeapon(Weapon gun)
	{
		weapons.add(gun);
	}
	
	public Physics getController()
	{
		return controller;
	}
	
	public void setFlash(boolean t)
	{
		flash = t;
	}
	
	public static Window getWindow()
	{
		return windowInstance;
	}
	
	public ArrayList<Weapon> getWeapons()
	{
		return weapons;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void addScore(int x)
	{
		score += x;
	}
	
	public void setLevel(Level l)
	{
		level = l;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e)
	{
		pause = false;
		badass.lockMovement(false);		
	}


	@Override
	public void mouseExited(MouseEvent e) 
	{
		pause = true;
		badass.lockMovement(true);
		
	}


	@Override
	public void mousePressed(MouseEvent e) 
	{
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getWeaponIndex()
	{
		return weaponIndex;
	}
	
	public void reset()
	{
		setVisible(true);
		pause = true;
		loading = true;
		
		repaint();
		
		resetVariables();
		
		weapons.clear();
		movers.clear();
		scenery.clear();
		getGrid().getElements().clear();
		
		level.reset();
		badass = new Badass(0, 0, 20, 20, Color.red, "Ball", true);
		grid = new Grid(WIDTH, HEIGHT);
		bg = level.getColor();
		scenery = level.getScenery();
		
		elements = level.getWalls();
		grid.setElements(elements);
		
		enemies = new ArrayList<Enemy>();
		for (Element e : elements)
		{
			if (e instanceof Enemy)
				enemies.add((Enemy) e);
		}
				
		items = new ArrayList<Item>();
		for (Element e : elements)
		{
			if (e instanceof Item)
				items.add((Item) e);
		}
		
		for (Element e : elements)
		{
			if (e instanceof MovingPlatform)
				 movers.add((MovingPlatform) e);
		}	
		
		badass.revertStats();
		health = badass.getHealth();

		grid.addElement(badass);
		hud = new HUD(badass);
		
		controller = new Physics(1, 1, this);
		
		elements = grid.getElements();
		gravities = grid.getGravities();
		
		weapons.add(new Weapon(Bullet.class, "regular"));
		weapons.add(new Weapon(ShotgunBullet.class, "shotgun"));
		weapons.add(new Weapon(Rocket.class, "rocket"));
		
		loading = false;
		pause = false;	
		repaint();
	}
	
	public boolean isComplete()
	{
		return complete;
	}
	
	private void resetVariables()
	{
		gameOver = false;
		complete = false;
		bulletActive = false;
		rightDown = false;
		leftDown = false;
		upDown = false;
		spaceDown = false;
	}
}
