import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringTokenizer;

public class doUpload extends Thread
{
	uploadFrame uF;
	
	public doUpload(uploadFrame uF)
	{
		this.uF = uF;
		this.start();
	}
	
	public void run()
	{
		uF.setEnabled(false);
		String URLString = null;
		
		try {
			URLString = "procedureName=" + URLEncoder.encode(uF.procedureName.getText(), "UTF-8") + "&";
	  		URLString = URLString + "description=" + URLEncoder.encode(replace.replaceAll(uF.description.getText(),"\n","<BR>"), "UTF-8") + "&";
	  		URLString = URLString + "code=" + URLEncoder.encode(replace.replaceAll(uF.lo.ta.getText(), "\n", "<BR>"), "UTF-8") + "&";
	  		URLString = URLString + "authorName=" + URLEncoder.encode(uF.authorName.getText(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

  		String directory = "/";
  						
  		String temp = Logo.L.getDocumentBase().toExternalForm();
  		
  		StringTokenizer t = new StringTokenizer(temp, "/");
  		if ( t.countTokens() > 2 ) {
  			temp = t.nextToken();
  			temp = t.nextToken();
				while (t.countTokens() > 1) {
					directory = directory + t.nextToken() + "/";
				}
  		}
  		
  		URLString = "POST " + directory + "cgi-bin/procedureupload.cgi HTTP/1.0\nAuthorization: Basic MTg0NDY6YmVybGluOTg=\nContent-Length: " + URLString.length() + "\n\n" + URLString;
						
		String CGIanswer = null;
		
		try {
			CGIanswer = new HTTPURL(Logo.L.getDocumentBase().getHost(), Logo.L.getDocumentBase().getPort()).getContent(URLString);
		} catch (Exception e) {}
		
		if ( CGIanswer == null ) {
			uF.status.setText(Data.button[22][uF.language]);
		} else if ((CGIanswer.substring(0,2)).equalsIgnoreCase("ok")) {
			uF.status.setText(Data.button[21][uF.language]);
			uF.upload.setEnabled(false);
		} else {
			uF.status.setText(Data.button[22][uF.language]);
		}
		uF.setEnabled(true);
	  	uF.validate();
	}
}