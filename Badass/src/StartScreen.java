import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.event.MouseInputListener;


public class StartScreen extends Frame implements ActionListener, MouseInputListener
{
	BufferedImage bg;
	int time = 0;
	int interval = 0;
	int begin;
	BufferedImage start;
	BufferedImage startDown;
	BufferedImage toPaint;
	BufferedImage levelOneImage;
	BufferedImage forgeImage;
	BufferedImage levelOneImageDown;
	BufferedImage forgeImageDown;
	BufferedImage levelTwoImage;
	BufferedImage levelTwoDownImage;
	StartButton levelTwo;
	StartButton startButton;
	StartButton levelOne;
	StartButton forge;
	Forge forgeLevel;
	ArrayList<StartButton> levels = new ArrayList<StartButton>();
	boolean go = false;
	boolean levelSelect = false;
	MenuSystem menu;
	
	public StartScreen()
	{
		Panel panel = new Panel();
		addMouseMotionListener(this);
		addMouseListener(this);
		
		begin = (int) System.currentTimeMillis()/1000;

		try {bg = ImageIO.read(new File("startScreen.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {start = ImageIO.read(new File("Start.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {startDown = ImageIO.read(new File("StartDown.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {levelOneImage = ImageIO.read(new File("LevelTwoImage.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {forgeImage = ImageIO.read(new File("ForgeImage.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {forgeImageDown = ImageIO.read(new File("ForgeImageDown.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {levelOneImageDown = ImageIO.read(new File("LevelTwoImageDown.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {levelTwoImage = ImageIO.read(new File("LevelThreeImage.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {levelTwoDownImage = ImageIO.read(new File("LevelThreeDownImage.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		toPaint = start;
		
		levelOne = new StartButton(100, 200, levelOneImage.getWidth(), levelOneImage.getHeight(), levelOneImage, levelOneImageDown);
		forge = new StartButton(300, 450, forgeImage.getWidth(), forgeImage.getHeight(), forgeImage, forgeImageDown);
		startButton = new StartButton(400, 100, start.getWidth(), start.getHeight(), start, startDown);
		levelTwo = new StartButton(500, 200, levelTwoImage.getWidth(), levelTwoImage.getHeight(), levelTwoImage, levelTwoDownImage);
		
		levelOne.setVisible(false);
		forge.setVisible(false);
		levelTwo.setVisible(false);
		startButton.setVisible(true);
		
		levels.add(levelOne);
		levels.add(forge);
		levels.add(levelTwo);
		
		menu = new MenuSystem();
		menu.addItem(startButton);
		menu.addItem(levelOne);
		menu.addItem(forge);
		menu.addItem(levelTwo);
		
		this.setSize(1000, 600);
		setVisible(true);
		run();
	}
	
	public void run()
	{
		if (interval > 2)
			begin = (int) System.currentTimeMillis()/1000;
		
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
	public void paint(Graphics g)
	{		
		interval = (int) System.currentTimeMillis()/1000 - begin;

		g.drawImage(bg, 0, 0, this);
		
		menu.draw(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		System.out.println("exited **********");
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		StartButton clicked = menu.checkClicks(arg0);
		
		if (clicked == startButton)
		{
			startButton.setClicked(true);
			
			for (StartButton level : levels)
				level.setVisible(true);
		}
		
		else if (startButton.isClicked() && clicked != null)
		{
			if (clicked == levelOne)
				Window.getWindow().setLevel(new LevelTwo(Window.getWindow()));
			
			else if (clicked == forge)
				forgeLevel = new Forge(Window.getWindow());
			
			else if (clicked == levelTwo)
				Window.getWindow().setLevel(new LevelThree(Window.getWindow()));
			
			setVisible(false);
		}
		
		repaint();
	}
	
	public boolean startGame()
	{
		return go;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{
		menu.checkMouseOvers(arg0);
		
		repaint();
	}
}
