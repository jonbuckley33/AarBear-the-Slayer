import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class FileSaver 
{
	File save = new File("save.txt");
	
	public FileSaver()
	{
		
	}
	
	public void writeTo(String body) throws IOException
	{
		Writer output = null;
		
		output = new BufferedWriter(new FileWriter(save));
	    output.write(body);
	    output.close();
	}
	
}
