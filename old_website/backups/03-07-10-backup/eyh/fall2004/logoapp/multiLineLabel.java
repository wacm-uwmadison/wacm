import java.awt.*;
import java.util.*;

public class multiLineLabel extends Canvas
{
	// private String text;
	private Vector textLines;
	private int width, heigth;
	private Font font = new Font("Helvetica", Font.PLAIN, 14);
	private FontMetrics fM;
	
	public multiLineLabel(String text, int width)
	{
		//this.text = text;
		this.width = width;
		setText(text);
	}
	
	public void setText(String text)
	{
		// size?
		textLines = new Vector();
		fM = this.getFontMetrics(font);
		StringTokenizer t = new StringTokenizer(text, " ");
		String tryIt = "";
		String nextWord;
		
		while (t.hasMoreTokens())
		{
			nextWord = t.nextToken();
			if ( fM.stringWidth(tryIt+" "+nextWord) > width-20 )
			{
				textLines.addElement(tryIt);
				tryIt = nextWord;
			} else
			{
				if (tryIt.length() == 0)
					tryIt = nextWord;
				else
					tryIt += " " + nextWord;
			}
			if (!t.hasMoreTokens() & !tryIt.equals(""))
				textLines.addElement(tryIt);
		}
		this.repaint();
		this.invalidate();
	}
	
	public void paint (Graphics g)
	{
		g.setFont(this.font);
		int vertOffset = 10;
		
		for ( int i = 0; i < textLines.size(); i++)
		{
			g.drawString( (String) textLines.elementAt(i), 10, vertOffset+fM.getAscent());
			vertOffset += fM.getAscent()+fM.getDescent()+5;
		}
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(this.width, 20 + textLines.size()*(fM.getAscent()+fM.getDescent()+5) );
	}
	
	public Dimension getMinimumSize()
	{
		return new Dimension(this.width, 20 + textLines.size()*(fM.getAscent()+fM.getDescent()+5) );
	}
	
	public Dimension getSize()
	{
		return new Dimension(this.width, 20 + textLines.size()*(fM.getAscent()+fM.getDescent()+5) );
	}
	
	public Dimension getMaximumSize()
	{
		return new Dimension(this.width, 20 + textLines.size()*(fM.getAscent()+fM.getDescent()+5) );
	}
	
	public Dimension preferredSize()
	{
		return new Dimension(this.width, 20 + textLines.size()*(fM.getAscent()+fM.getDescent()+5) );
	}
	
	public Dimension minimumSize()
	{
		return new Dimension(this.width, 20 + textLines.size()*(fM.getAscent()+fM.getDescent()+5) );
	}
	
	public Dimension size()
	{
		return new Dimension(this.width, 20 + textLines.size()*(fM.getAscent()+fM.getDescent()+5) );
	}
	
	public Dimension maximumSize()
	{
		return new Dimension(this.width, 20 + textLines.size()*(fM.getAscent()+fM.getDescent()+5) );
	}

}