import java.awt.*;

// ------------------------------------------------------------------------------------------------
//																																class onlineHelp
// ------------------------------------------------------------------------------------------------
public class onlineHelp extends Frame
{
	TextArea text;
	
	onlineHelp(int language, int Content) {
		text = new TextArea(22,80);
		text.setText(Data.help[Content][language]);	       // 0 english 1 german 2 french
		this.setTitle("Logo");

		this.add("North",text);
		this.pack(); this.show();	
	}
	
	public boolean handleEvent( Event evt ) {
		if ( evt.id == Event.WINDOW_DESTROY ) {
  		dispose();
   		return true;	
  	}
  	return false;
	}
}