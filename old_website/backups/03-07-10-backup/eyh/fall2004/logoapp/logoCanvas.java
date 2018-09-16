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
	boolean isBlank = true;
	
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
	
	public void setupMaze() {
		isBlank = false;
		image = getMaze();
		isPenUp = false;
		turtle.reset(275,340);
	}
	
	public void setupPaper() {
		isBlank = true;
		image = getPaper();
		isPenUp = false;
		turtle.reset(170, 220);
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
		if(isBlank) return getPaper();
		return getMaze();
	}
	
	public Image getPaper() {
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
	
	public Image getMaze() {
        int border = 8;
        int opening = 40;
        java.awt.Dimension di = this.getSize();
        java.awt.Image answer = this.createImage(di.width, di.height);
        java.awt.Graphics g = answer.getGraphics();
        // design paper


      // Draw Background
              g.setColor(java.awt.Color.lightGray);
        g.fillRect(0,0,di.width,di.height);


        java.awt.Color c = new java.awt.Color(0,50,0);
        g.setColor(c);
        // Draw Edges
        g.fillRect(0,0,di.width,border);
        g.fillRect(di.width-border,0,border,di.height);
        g.fillRect(0,0,border,di.height);
        g.fillRect(0,di.height-border,di.width-(2*border+2*opening),border);

      // Draw Columns
        g.fillRect(border+opening,border+opening,border,border+opening);
        g.fillRect(di.width-(border*2+opening),border+opening,border,di.height-(border+opening));

        // Draw Rows
        g.fillRect(border+opening,border+opening,di.width-(border*2+opening*2),border);
        g.fillRect(border*2+opening*2,border*2+opening*2,di.width-(4*border+3*opening),border);
        g.fillRect(border+opening,di.height-(3*border+2*opening),di.width-(3*border+3*opening),border);
        g.fillRect(border+opening,di.height-(2*border+opening),di.width-(2*border+2*opening),border);
              g.fillRect(0,border*2+opening*3,opening+border,border);

              // Draw Polygons
              int[] xvalues1 = {border*2+opening*2, di.width-(border+opening), di.width-(border+opening), border*2+opening*2};
              int[] yvalues1 = {border*2+opening*2, border*2+opening*2, di.height-(2*border+3*opening), border*3+opening*2};
                    g.fillPolygon(xvalues1,yvalues1,4);
              int[] xvalues2 = {border+opening, di.width-(2*border+2*opening), di.width-(2*border+2*opening), border+opening};
              int[] yvalues2 = {border*2+opening*3, di.height-(3*border+opening*2), di.height-(2*border+2*opening), border*3+opening*3};
              g.fillPolygon(xvalues2,yvalues2,4);
              int[] xvalues3 = {border+opening, di.width-(4*border+4*opening), di.width-(4*border+4*opening), border+opening};
              int[] yvalues3 = {border*3+opening*4, di.height-(3*border+opening*2), di.height-(2*border+2*opening), di.height-(2*border+2*opening)};
              g.fillPolygon(xvalues3,yvalues3,4);

            // Speckle
              int[] pixels = new int[di.width*di.height];
              java.awt.image.PixelGrabber pg = new java.awt.image.PixelGrabber(answer, 0, 0, di.width, di.height, pixels, 0, di.width);
              try
              {
                      pg.grabPixels();
              }
              catch (InterruptedException e)  { }



              java.util.Random generator = new java.util.Random(10);

              int samples = 8000;
              int dotsize = 0;
              int treesize = 4;

              for(int i = 0; i < samples; i++)
              {
                      int x = generator.nextInt(di.width-2*treesize)+treesize;
                      int y = generator.nextInt(di.height-2*treesize)+treesize;

                      int  col = pixels[y*di.width+x];
                      int  red = (col & 0x00ff0000) >> 16;

                      if(red == 0)
                      {
                                      int green = generator.nextInt(155);
                                      c = new java.awt.Color(0,green+50,0);
                                      g.setColor(c);
                                      g.fillOval(x-treesize,y-treesize,2*treesize+1,2*treesize+1);
                      }
                            else
                      {
	                                  g.setColor(java.awt.Color.gray);
	                                  g.fillRect(x-dotsize,y-dotsize,2*dotsize+1,2*dotsize+1);
                      }
              }

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
		//if ( lo.isStepByStep == true ) {
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
		//}
	}
}

// ------------------------------------------------------------------------------------------------
//																																class turtle
// ------------------------------------------------------------------------------------------------
class turtle
{
	public double initial_x = 170;
	public double initial_y = 220;
	public double x = 170;
	public double y = 220;
	public double heading = 0;
	public Color color = Color.black;
	
	public void reset(int x, int y) {
		initial_x = x;
		initial_y = y;
		this.x = x;
		this.y = y;
		heading = 0;
		color = Color.black;
	}
	
	public void reset() {
		this.x = initial_x;
		this.y = initial_y;
		heading = 0;
		color = Color.black;
	}
}