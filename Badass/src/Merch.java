import java.awt.image.BufferedImage;


public class Merch extends StartButton
{
	String name;
	int price;
	boolean selected;
	
	public Merch(String name, int price, BufferedImage i, BufferedImage j)
	{
		super(i, j);
		this.name = name;
		this.price = price;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int buy(int x)
	{
		return x - price;
	}
	
	public void setSelected(boolean b)
	{
		selected = b;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
}
