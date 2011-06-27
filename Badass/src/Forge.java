import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Forge extends JFrame implements MouseListener, ActionListener
{
	
	ArrayList<String> levels = new ArrayList<String>();
	int step = 0;
	int xCoord, yCoord, width, height;
	Button leave = new Button("Done");
	Button nextStep = new Button("Next");
	Label levelLabel = new Label("Level: ");
	Window window;
	ForgeLevel level;
	Boolean finished = false;
	Container c;
	ColorPicker picker;
	

	public Forge(Window w)
	{	
		window = w;
		level = new ForgeLevel();
		
		JPanel panel = new JPanel();
		c = this.getContentPane();
		
		leave.addActionListener(this);
		nextStep.addActionListener(this);
		
		panel.add(leave);
		panel.add(nextStep);
		
		c.add(panel);
		
		/*picker = new ColorPicker();
		
		while(!picker.isDone())
		{System.out.print("");}
		System.out.println("done with color");*/
		
		level.setColor(Color.green);
		System.out.println(" Color!");
		
		setSize(1200, 600);
		setVisible(true);
		
		panel.addMouseListener(this);
		this.addMouseListener(this);
	}


	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getSource() == leave)
		{
			level.finalize();
			window.setLevel(level);
			setVisible(false);
		}
		
		else if (e.getSource() == nextStep)
			step++;
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		if (step == 1)
		{
			xCoord = arg0.getX();
			yCoord = arg0.getY();
			
			BearEnemy toAdd = new BearEnemy(xCoord, yCoord, 10, 10, Color.black, "Enemy", true, level.getWalls(), window);
			
			level.addWall(toAdd);
		}
	}


	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		if (step == 0)
		{
			xCoord = arg0.getX();
			yCoord = arg0.getY();	
		}
	}


	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		if (step == 0)
		{
			width = arg0.getX() - xCoord;
			height = 20;
			EarthFloor toAdd;
		
			toAdd = new EarthFloor(new Dimension(xCoord, yCoord + 5), width, 20);
			level.addWall(toAdd);
		}
		
		update();
		System.out.println("wall added");
	}
	
	public ForgeLevel getLevel()
	{
		return level;
	}
	
	public boolean isFinished()
	{
		return finished;
	}
	
	public void update()
	{
		repaint();
	}
	
	public void paint(Graphics g)
	{
		System.out.println("painting*****");
		g.setColor(Color.DARK_GRAY);
		g.fillRect(1, 1, WIDTH-2, HEIGHT-2);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
		

			/*for (int i = 0; i < scenery.size(); i++)
			{
				NotTangible e = scenery.get(i);					
				g.drawImage(e.getImage(), (int)e.getDimension().getWidth()*5, (int)e.getDimension().getHeight()*5, this);
			}
			 **/
			
			for (int i = 0; i < level.getWalls().size(); i++)
			{
				Element e = level.getWalls().get(i);
				
				System.out.println(e.getClass());
				if (e.getName().compareTo("Floor") == 0)
				{
					if (e instanceof MovingPlatform)
					{
						g.setColor(Color.blue);
						g.fillRect((int)e.getDimension().getWidth()*5, (int)e.getDimension().getHeight()*5, e.getWidth()*5, e.getHeight()*5);
						System.out.println(e.getDimension().getWidth()*5 + "   " + e.getDimension().getHeight()*5);
						System.out.println(e.getWidth());
					}
					
					for (int x = 0; x < (e.getWidth()*5)/5; x++)
						g.drawImage(e.getImage(), (int) e.getDimension().getWidth()*5 + (x*5), (int) e.getDimension().getHeight()*5, this);
				}
				
				if (e.getName().compareTo("Enemy")==0)
				{
					g.drawImage(e.getImage(), (int) e.getDimension().getWidth()*5, (int) e.getDimension().getHeight()*5 , this);
				}
				
				else if (e.hasImage() && !(e instanceof Bullet))
				{
					g.drawImage(e.getImage(), (int) e.getDimension().getWidth()*5, (int) e.getDimension().getHeight()*5, this);
				}
				
				else if (e.getName().compareTo("bullet")==0)
				{
					g.drawImage(e.getImage(), (int) e.getDimension().getWidth()*5, (int) e.getDimension().getHeight()*5, this);
				}
				
				else if (e.getName().compareTo("Ball")==0)
				{
					g.setColor(e.getColor());
					g.fillOval((int)e.getDimension().getWidth()*5, (int)e.getDimension().getHeight()*5, e.getWidth()*5, e.getHeight()*5);
				}
				
				else
				{
					g.setColor(e.getColor());
					g.fillRect((int)e.getDimension().getWidth()*5, (int)e.getDimension().getHeight()*5, e.getWidth()*5, e.getHeight()*5);
				}
				
			}
			
	}	
	
	
}
