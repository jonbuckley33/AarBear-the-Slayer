import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.event.MouseInputListener;


public class Store extends Frame implements MouseInputListener
{
	int credits;
	SaveFile save;
	//MenuSystem menu = new MenuSystem();
	LinearMenu selling = new LinearMenu(true ,10 , 30);
	BufferedImage placeHolder;
	BufferedImage guitarImage;
	BufferedImage teleporterImage;
	BufferedImage weaponIndex;
	BufferedImage sellingImage;
	Merch guitar;
	Merch teleporter;
	
	public Store(SaveFile save)
	{
		this.save = save;
		
		try {placeHolder = ImageIO.read(new File("PlaceHolderWeapon.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {guitarImage = ImageIO.read(new File("guitarWeapon.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {teleporterImage = ImageIO.read(new File("TeleporterWeapon.png"));}
		catch (IOException e){System.out.println("*****ERROR**** teleport");}
		
		try {weaponIndex = ImageIO.read(new File("weaponIndex.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {sellingImage = ImageIO.read(new File("selling.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}	
		
		selling.setBackgroundImage(sellingImage, 10, 42);
		
		credits = save.getCredits();
		
		guitar = new Merch("guitar", 10, guitarImage, guitarImage);
		teleporter = new Merch("teleporter", 15, teleporterImage, teleporterImage);

		selling.addItem(guitar);
		selling.addItem(teleporter);
		
		addMouseMotionListener(this);
		addMouseListener(this);
		setSize(1000, 600);
		setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		selling.draw(g);
		System.out.println("selling1");
		
		for (StartButton s : selling.getItems())
		{
			if (s.isDepressed())
				g.drawImage(weaponIndex, (int)s.getPoint().getX()-7, (int)s.getPoint().getY()-7, null);
			
			
		}
		
	}
	
	
	@Override	public void mouseClicked(MouseEvent arg0) 
	{
		StartButton toCheck = selling.checkClicks(arg0);
		
		if (toCheck == guitar)
			save.addWeapons(new Weapon(GuitarBullet.class, "guitar"));
		
		else if (toCheck == teleporter)
			save.addWeapons(new Weapon(Teleport.class, "teleporter"));
		
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		selling.checkMouseOvers(arg0);
		
		repaint();
	}
	
}
