import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;


public class Gameover extends JFrame implements MouseInputListener
{
	MenuSystem menu = new MenuSystem();
	BufferedImage yesImage, yesImageDown, noImage, noImageDown, gameover;
	StartButton yes, no;
	
	public Gameover()
	{
		try {gameover = ImageIO.read(new File("gameover.jpeg"));}
		catch (IOException e){}
		
		try {yesImage = ImageIO.read(new File("yesImage.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {yesImageDown = ImageIO.read(new File("yesImageDown.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {noImage = ImageIO.read(new File("noImage.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		try {noImageDown = ImageIO.read(new File("noImageDown.png"));}
		catch (IOException e){System.out.println("*****ERROR****");}
		
		yes = new StartButton(200, 400, yesImage.getWidth(), yesImage.getHeight(), yesImage, yesImageDown);
		
		no = new StartButton(700, 400, noImage.getWidth(), noImage.getHeight(), noImage, noImageDown);

		menu.addItem(yes);
		menu.addItem(no);
		
		setSize(1000, 600);
		setVisible(true);
		repaint();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent arg0) 
	{
		if (menu.checkClicks(arg0) == yes)
		{
			setVisible(false);
			Window.getWindow().reset();
			System.out.print("quitting");
		}
		
		else if (menu.checkClicks(arg0) == no)
		{
			setVisible(false);
			System.exit(0);
		}
		
		repaint();
		
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
		menu.checkMouseOvers(arg0);
		
		repaint();
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(gameover, 0, 0, null);
		menu.draw(g);
	}
}
