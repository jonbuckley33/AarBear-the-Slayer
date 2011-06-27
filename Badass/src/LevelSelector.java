import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LevelSelector extends Frame implements ActionListener
{
	ArrayList<String> levels = new ArrayList<String>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	Label levelLabel = new Label("Level: ");
	Window window;
	String level = null;
	

	public LevelSelector(Window w)
	{
		window = w;
		
		Panel panel = new Panel();
		panel.add(levelLabel);
		
		levels.add("FirstLevel");
		levels.add("LevelTwo");
		levels.add("Forge");
		
		for (String e : levels)
			buttons.add(new Button(e));
		
		for (Button b : buttons)
		{
			b.addActionListener(this);
			panel.add(b);
		}
		
		this.add(panel);
		
		setSize(500, 300);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent e) 
	{
		setVisible(false);
		
		if (e.getSource()==buttons.get(0))
			level = buttons.get(0).getLabel();
		
		if (e.getSource()==buttons.get(1))
			level = buttons.get(1).getLabel();
		
		if (e.getSource()==buttons.get(2))
			level = buttons.get(2).getLabel();
	}
	
	public String getLevel()
	{
		return level;
	}
	
	public void addLevel(Level l)
	{
		levels.add(l.getName());
	}
	
	
}
