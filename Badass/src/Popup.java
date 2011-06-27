import java.awt.Button;
import java.awt.Container;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Popup extends JFrame implements ActionListener
{
	Container c;
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<TextField> fields = new ArrayList<TextField>();
	ArrayList<Label> labels = new ArrayList<Label>();
	
	public Popup()
	{
		JPanel panel = new JPanel();
		c = this.getContentPane();
		
		buttons.add(new Button("Okay"));
		labels.add(new Label("Color: "));
		fields.add(new TextField("text goes here..."));
		
		for (Label l : labels)
			panel.add(l);
		
		for (TextField f : fields)
			panel.add(f);
		
		for (Button b : buttons)
		{
			b.addActionListener(this);
			panel.add(b);
		}

		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (arg0.getSource() == buttons.get(0))
		{
			setVisible(false);
		}
		
	}
}
