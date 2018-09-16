// ------------------------------------------------------------------------------------------------
//																																class wait
// ------------------------------------------------------------------------------------------------
public class wait extends Thread
{
	wait() {
	}
	
	public void run() {
		try {
			Thread.sleep(200);
		}
		catch (Exception e) {System.out.println(e);}
	}
}