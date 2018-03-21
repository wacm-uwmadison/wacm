import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.StringTokenizer;

// ------------------------------------------------------------------------------------------------
//																																class logoCanvas
// ------------------------------------------------------------------------------------------------
public class logoCanvas extends Canvas
{
	turtle turtle;
	Image image;
	boolean isPenUp = false;
	boolean isFirstRepaint = true;
	Logo lo;
	
	logoCanvas(Logo l) {
		this.lo = l;
		turtle = new turtle();
	}
	
	public void reset() {
		image = getImage();
		isPenUp = false;
		turtle.reset();
	}
	
	public void errorMessage(String err) {
		System.out.println("err ="+err);
		repaint();
		Graphics g = image.getGraphics();
		Font font = new Font("Helvetica",Font.PLAIN,12);
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString(err,5,20);
		repaint();
	}
	
	public void go(double trip)	{
		double newx = turtle.x + ( trip * Math.sin( (turtle.heading / 360) * 2 * Math.PI) );
		double newy = turtle.y - ( trip * Math.cos( (turtle.heading / 360) * 2 * Math.PI) );
		Graphics g = image.getGraphics();
		if ( isPenUp == false) {
			g.setColor(turtle.color);
			g.drawLine(doubleToInt(turtle.x),doubleToInt(turtle.y),doubleToInt(newx),doubleToInt(newy));
		}
		turtle.x = newx;
		turtle.y = newy;
		repaint();
	}
	
	public void turnright(int angle) {
		turtle.heading = turtle.heading + angle;
		while ( (turtle.heading / 360) > 1) { turtle.heading = turtle.heading - 360; }
		if (lo.isStepByStep == true) repaint();
	}
	
	public static int doubleToInt(double d)
	{
		StringTokenizer t = new StringTokenizer(Double.toString(d),".");
		return Integer.parseInt(t.nextToken());
	}
	
	public Image getImage() {
		Dimension di = this.getSize();
		Image answer = this.createImage(di.width, di.height);
		Graphics g = answer.getGraphics();
		// design paper
		g.setColor(Color.gray);
		// vert
		g.drawLine(di.width-1, 4, di.width-1, di.height-1);
		g.drawLine(di.width-3, 2, di.width-3, di.height-3);
		g.drawLine(di.width-5, 0, di.width-5, di.height-5);
		g.drawLine(0, 0, 0, di.height-5);
		
		// horizontal
		g.drawLine(4, di.height-1, di.width-1, di.height-1);
		g.drawLine(2, di.height-3, di.width-3, di.height-3);
		g.drawLine(0, di.height-5, di.width-5, di.height-5);			
		g.drawLine(0, 0, di.width-5, 0);
		
		return answer;
	}
	
	public void fillCircle(Graphics g, double x, double y, int r) {
		g.fillOval(doubleToInt(x)-r,doubleToInt(y)-r,2*r,2*r);
	}
	
	public void paint(Graphics g) {
		if (isFirstRepaint == true) {
			this.image = getImage();
			isFirstRepaint = false;
		}
		
		// draw what is already drawn by Logo
		g.drawImage(image,0,0,this);
		
		// draw the Turtle
		if ( lo.isStepByStep == true ) {
			g.setColor(Color.green);
			// the body
			fillCircle(g, turtle.x, turtle.y, 8);
			// the head
			fillCircle(g, turtle.x+(11*Math.sin((turtle.heading/360)*2*Math.PI)),
					turtle.y-(11*Math.cos((turtle.heading/360)*2*Math.PI)), 3);
			// the legs
			fillCircle(g, turtle.x+(9*Math.sin(((turtle.heading+45)/360)*2*Math.PI)),
					turtle.y-(9*Math.cos(((turtle.heading+45)/360)*2*Math.PI)), 3);
			fillCircle(g, turtle.x+(9*Math.sin(((turtle.heading-45)/360)*2*Math.PI)),
					turtle.y-(9*Math.cos(((turtle.heading-45)/360)*2*Math.PI)), 3);
			fillCircle(g, turtle.x+(9*Math.sin(((turtle.heading+140)/360)*2*Math.PI)),
					turtle.y-(9*Math.cos(((turtle.heading+140)/360)*2*Math.PI)), 3);
			fillCircle(g, turtle.x+(9*Math.sin(((turtle.heading-140)/360)*2*Math.PI)),
					turtle.y-(9*Math.cos(((turtle.heading-140)/360)*2*Math.PI)), 3);
		}
	}
}

// ------------------------------------------------------------------------------------------------
//																																class turtle
// ------------------------------------------------------------------------------------------------
class turtle
{
	public double x = 170;
	public double y = 220;
	public double heading = 0;
	public Color color = Color.black;
	
	public void reset() {
		x = 170;
		y = 220;
		heading = 0;
		color = Color.black;
	}
}