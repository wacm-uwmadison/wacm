import java.awt.*;
import java.util.*;

public class LogoApplication extends Frame
{
	Logo L;
		
	public static void main(String args[])
	{
		String names[] = new String[11];
		names[0] = "height";
		names[1] = "width";
		names[2] = "bgcolor";
		names[3] = "language";
		names[4] = "source";
		names[5] = "canvasWidth";
		names[6] = "canvasHeight";
		names[7] = "taWidth";
		names[8] = "taHeight";
		names[9] = "taTextSize";
		names[10] = "document";

		if ( args.length != 11 )
		{
			System.out.println("Usage:");
			System.out.println("Parameter: height width bgcolor language source canvasWidth canvasHeight taWidth taHeight taTextSize document");
			System.exit(0);
		}

		Hashtable params = new Hashtable();
		for ( int i = 0; i < 11; i++)
		{
			params.put(names[i], args[i]);
		} 
		new LogoApplication(params);
	}
	
	public LogoApplication(Hashtable params)
	{
		L = new Logo(params);
		this.add(L);
		this.setSize(Integer.parseInt((String) params.get("width")), Integer.parseInt((String) params.get("height")));
		this.setResizable(false);
		this.show();
	}
	
	public boolean handleEvent( Event evt )
	{
  	if ( evt.id == Event.WINDOW_DESTROY )
  	{
  		this.dispose();
  		System.exit(0);
  		return true;
  	}
  	return false;
  }
}