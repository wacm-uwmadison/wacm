import java.util.Hashtable;

// ------------------------------------------------------------------------------------------------
//																																class exe
// ------------------------------------------------------------------------------------------------
public class execute extends Thread
{
	code c;
	Logo l;
	
	execute(code c, Logo l) {
		this.l = l;
		this.c = c;
		this.start();
	}
	
	public void run() {
		this.setPriority(9);
		Hashtable temp = new Hashtable();
		temp.put("Sebastian", "18");
		temp.put("Vinny", "100");

		boolean answer = c.execute("main", temp);
		l.setButtonsToNormal();
		this.stop();
	}
}