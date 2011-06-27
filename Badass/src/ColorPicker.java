import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JPanel;


public class ColorPicker extends Popup
{
	Container c;
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<TextField> fields = new ArrayList<TextField>();
	ArrayList<Label> labels = new ArrayList<Label>();
	JColorChooser colorChooser;
	Color color;
	boolean done = false;
	
	public ColorPicker()
	{
		JPanel panel = new JPanel();
		c = this.getContentPane();
		
		buttons.add(new Button("Okay"));
		labels.add(new Label("Color: "));
		//fields.add(new TextField("text goes here..."));
		
		for (Label l : labels)
			panel.add(l);
		
		for (TextField f : fields)
			panel.add(f);
		
		colorChooser = new JColorChooser();
		panel.add(colorChooser);
		System.out.println("added color panel");
		
		for (Button b : buttons)
		{
			b.addActionListener(this);
			panel.add(b);
		}
		
		c.add(panel);
		
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		System.out.println("button pressed");
		if (arg0.getSource() == buttons.get(0))
		{
			System.out.println("action performed");
			color = colorChooser.getColor();
			done = true;
			setVisible(false);
		}
		
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public boolean isDone()
	{
		return done;
	}
}
