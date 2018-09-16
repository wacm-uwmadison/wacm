import java.applet.Applet;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Hashtable;
import java.util.StringTokenizer;

// ------------------------------------------------------------------------------------------------
//																																class Logo
// ------------------------------------------------------------------------------------------------
public class Logo extends Applet {
	static Logo L;
	boolean isStepByStep = true;
	boolean nextStep = false;
	boolean answer;
	
	int language = 0;
	
	logoCanvas canvas;
	TextArea ta;
	List codeList = new List();
	
	Button loadMaze = new Button("Load Maze");
	Button clear = new Button("Clear");
	Button stop = new Button("stop");
	Button execute = new Button();
	Button stepByStep = new Button();
	Button next = new Button();
	Button allAtOnce = new Button();
	Button upload = new Button();
	Button syntax = new Button();
	Button interpreter = new Button();
	Button loadCode = new Button("Load Code");
	
	Label explication = new Label();
	
	Panel codePanel;
	Color bgcolor;
	
	CardLayout cd;
	execute executeObj;
	
	boolean isApplet = true;
	Hashtable params;
	
	public Logo()
	{
	}
	
	public Logo(Hashtable params)
	{
		isApplet = false;
		this.params = params;
		init();
	}
	
	public String getParameter(String key)
	{
		if ( isApplet )
		{
			return super.getParameter(key);
		} else
		{
			return (String) params.get(key);
		}
	}
	
	public URL getDocumentBase()
	{
		if ( isApplet )
		{
			return super.getDocumentBase();
		} else
		{
			try {
				return new URL((String) params.get("document"));
			} catch (MalformedURLException e) {
				System.out.print(e);
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public void init()
	{
		L = this;
		new Data();
		canvas = new logoCanvas(this);
		
		bgcolor = getColorFromHexaString.getColor(this.getParameter("bgcolor"));
		language = Integer.parseInt(this.getParameter("language"));
		canvas.setSize(Integer.parseInt(this.getParameter("canvasWidth")), Integer.parseInt(this.getParameter("canvasHeight")));
		ta = new TextArea(Integer.parseInt(this.getParameter("taHeight")), Integer.parseInt(this.getParameter("taWidth")));
		ta.setFont(new Font("Helvetica", Font.PLAIN, Integer.parseInt(this.getParameter("taTextSize"))));
		codeList.setFont(new Font("Helvetica", Font.PLAIN, Integer.parseInt(this.getParameter("taTextSize"))));
		
		explication.setText(Data.button[0][language]);
		explication.setFont(new Font("Helvetica", Font.PLAIN, 10));
		
		execute.setLabel(Data.button[1][language]);
		stepByStep.setLabel(Data.button[2][language]);
		next.setLabel(Data.button[3][language]);
		allAtOnce.setLabel(Data.button[4][language]);
		if (isApplet) upload.setLabel(Data.button[7][language]);
		syntax.setLabel(Data.button[5][language]);
		interpreter.setLabel(Data.button[6][language]);
		
		next.setEnabled(false);
		allAtOnce.setEnabled(false);
		
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		this.setBackground(bgcolor);
		
		this.setForeground(Color.black);
		canvas.setForeground(Color.black);
		canvas.setBackground(Color.white);		
		ta.setForeground(Color.black);
		ta.setBackground(Color.white);
		codeList.setForeground(Color.black);
		codeList.setBackground(Color.white);
		
		//Mark
		Panel buttonPanel = new Panel();
		buttonPanel.add(loadMaze);
		buttonPanel.add(clear);
		
		Panel leftPanel = new Panel();
		GridBagLayout leftGridBag = new GridBagLayout();
		
		c.gridx =0;c.gridy =0;
		leftGridBag.setConstraints(canvas, c);
		leftPanel.add(canvas);
		//this.add(canvas);
		c.gridy = 1;
		leftGridBag.setConstraints(buttonPanel, c);
		leftPanel.add(buttonPanel);
		leftPanel.setLayout(leftGridBag);
		
		gridbag.setConstraints(leftPanel, c);
		this.add(leftPanel);
		//Mark
		
		cd = new CardLayout();
		codePanel = new Panel();
		codePanel.setLayout(cd);
		codePanel.add("codeList",codeList);
		codePanel.add("ta",ta);
		cd.show(codePanel, "ta");		
		
		buttonPanel = new Panel();
		buttonPanel.add(execute);
		buttonPanel.add(stepByStep);
		buttonPanel.add(next);
		buttonPanel.add(allAtOnce);
		buttonPanel.add(stop);
		Panel lowerButtonPanel = new Panel();
		if (isApplet) lowerButtonPanel.add(upload);
		lowerButtonPanel.add(loadCode);
		lowerButtonPanel.add(syntax);
		lowerButtonPanel.add(interpreter);
		
		Panel rightPanel = new Panel();
		GridBagLayout rightGridBag = new GridBagLayout();
		
		c.gridx =0;c.gridy =0;
		rightGridBag.setConstraints(explication, c);
		rightPanel.add(explication);
		c.gridy =1;
		rightGridBag.setConstraints(codePanel, c);
		rightPanel.add(codePanel);
		c.gridy =2;
		rightGridBag.setConstraints(buttonPanel, c);
		rightPanel.add(buttonPanel);
		c.gridy =3;
		rightGridBag.setConstraints(lowerButtonPanel, c);
		rightPanel.add(lowerButtonPanel);
		
		rightPanel.setLayout(rightGridBag);
		
		c.gridx =2;c.gridy =1;
		Label space = new Label(" ");
		space.setFont(new Font("Helvetica", Font.PLAIN, 10));
		gridbag.setConstraints(space, c); 		
		this.add(space);
		
		c.gridx =3;c.gridy =1;
		gridbag.setConstraints(rightPanel, c); 		
		this.add(rightPanel);
		
		this.setLayout(gridbag);
		
		canvas.repaint();
		
		ta.setText(replace.replaceAll(this.getParameter("source"), "\\n", "\n" ));;
	}
	
	public boolean handleEvent( Event evt ) {
		
		if (evt.id != Event.ACTION_EVENT) return false;
		
		if(evt.target == loadMaze) {
			canvas.setupMaze();
			canvas.repaint();
			return true;
		}
		
		if(evt.target == clear)  {
			canvas.setupPaper();
			canvas.repaint();
			return true;
		}
		
		if (evt.target == stop) {
			if (executeObj != null) executeObj.stop();
			setButtonsToNormal();
		}
		
		if ( evt.target == execute | evt.target == stepByStep) {
			Image image = canvas.getImage();
			canvas.repaint();
			
			// the replace.replaceAllments makes the interpreter code have the same lines as the normal one
			// and we replace.replaceAll \t (Tabstop) with " " so the interpreter will like it ;-)
			StringTokenizer t = new StringTokenizer(replace.replaceAll(replace.replaceAll(ta.getText(),"\n\n","\n \n"),"\t","                "), "\n");
			int numLines = t.countTokens();
			String[] code = new String[numLines];
			for (int i=0; i < numLines; i++) code[i] = t.nextToken();
			
			canvas.reset();
			code codeObj = new code(code, numLines, canvas, this);
			
			codeList.removeAll();
			for (int i=0; i < numLines; i++) codeList.add(code[i]);
			
			if ( evt.target == execute ) {
				setButtonsToExecuteAll();
				executeObj = new execute(codeObj, this);
			}
			else {
				setButtonsToExecuteStepByStep();
				executeObj = new execute(codeObj, this);
			}
			return true;	
			
		}
		if ( evt.target == next ) {
			nextStep = true;
			return true;
		}
		
		if ( evt.target == upload) {
			new uploadFrame(this, language);
			return true;
		}
		
		if(evt.target == loadCode) {
			ta.setText(Data.code);
			return true;
		}
		
		if ( evt.target == interpreter) {
			new onlineHelp(language, 1);
			return true;
		}
		
		if ( evt.target == syntax) {
			new onlineHelp(language, 0);
			return true;
		}
		
		if ( evt.target == allAtOnce) {
			isStepByStep = false;
			nextStep = true;
			next.setEnabled(false);
			return true;
		}
		
		return false;
	}
	
	public void setButtonsToExecuteAll() {
		next.setEnabled(false);
		allAtOnce.setEnabled(false);
		execute.setEnabled(false);
		stepByStep.setEnabled(false);		
		isStepByStep = false;
		ta.setEditable(false);
	}
	
	public void setButtonsToExecuteStepByStep() {
		isStepByStep = true;
		ta.setEditable(false);
		
		//cd.first(codePanel);
		cd.show(codePanel, "codeList");
		
		next.setEnabled(true);
		allAtOnce.setEnabled(true);
		execute.setEnabled(false);
		stepByStep.setEnabled(false);		
	}
	
	public void setButtonsToNormal() {
		execute.setEnabled(true);
		stepByStep.setEnabled(true);
		
		isStepByStep = true;
		canvas.repaint();
		
		ta.setEditable(true);
		
		next.setEnabled(false);
		allAtOnce.setEnabled(false);
		
		//cd.last(codePanel);	
		cd.show(codePanel, "ta");
	}
}

class getColorFromHexaString
{
	public static Color getColor(String in) {
		int intValues[] = new int[3];
		String temp1;
		String temp2;
		
		for (int i = 0; i < 3; i++) {
			temp1 = in.substring(i*2,i*2+1);
			temp2 = in.substring(i*2+1,i*2+2);
			
			intValues[i] = letterToInt(temp1)*16+letterToInt(temp2);
		}
		return new Color(intValues[0], intValues[1], intValues[2]);
	}
	
	private static int letterToInt(String let) {
		String letter = let.toLowerCase();
		if (letter.equals("a")) return 10;
		if (letter.equals("b")) return 11;
		if (letter.equals("c")) return 12;
		if (letter.equals("d")) return 13;
		if (letter.equals("e")) return 14;
		if (letter.equals("f")) return 15;										
		return Integer.parseInt(letter);
	}
	
}

class uploadFrame extends Frame
{
	Logo lo;
	TextField procedureName = new TextField(20);
	TextField var = new TextField(5);
	TextField authorName = new TextField();
	TextArea description = new TextArea(5,55);
	Button testProcedure = new Button();
	Button upload = new Button();
	
	Checkbox useVar = new Checkbox();
	
	int language;
	
	multiLineLabel status;
	Label writeHere;
	
	uploadFrame(Logo lo, int language) {
		this.language = language;
		this.lo = lo;
		
		description.setText("");
		testProcedure.setLabel(Data.button[11][language]);
		upload.setLabel(Data.button[12][language]);
		
		writeHere = new Label(Data.button[10][language]);
		status = new multiLineLabel(Data.button[18][language], 450);
		this.setTitle(Data.button[13][language]);
		
		this.add("North", status);
		
		Panel procedure = new Panel();
		GridLayout grid = new GridLayout(4,2);
		
		procedure.add(new Label(Data.button[14][language]));
		procedure.add(procedureName);
		
		//procedure.add(new Label(Data.button[15][language]));
		//procedure.add(var);
		//var.setText("");
		
		//procedure.add(new Label(Data.button[15][language]));
		useVar.setLabel(Data.button[15][language]);
		useVar.setState(false);
		procedure.add(useVar);
		procedure.add(var);
		var.setText("");
		
		procedure.add(new Label(Data.button[17][language]));
		procedure.add(authorName);
		
		procedure.setLayout(grid);
		
		this.add("Center", procedure);
		
		this.add("East", new Label("  "));
		this.add("West", new Label("  "));
		
		Panel low = new Panel();
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx =0;c.gridy =0;
		gridBag.setConstraints(writeHere, c);
		low.add(writeHere);
		
		c.gridx =0;c.gridy =1;
		gridBag.setConstraints(description, c);
		low.add(description);
		
		Panel buttons = new Panel();
		buttons.add(testProcedure);
		buttons.add(upload);
		upload.setEnabled(false);
		c.gridx =0;c.gridy =2;
		gridBag.setConstraints(buttons, c);
		low.add(buttons);
		
		low.setLayout(gridBag);
		
		this.add("South", low);
		
		this.pack();
		this.show();
	}
	
	public boolean handleEvent( Event evt ) {
		if (evt.id == Event.WINDOW_DESTROY) {
			lo.setEnabled(true);
			this.dispose();
		}
		
		if (evt.id != Event.ACTION_EVENT) return false;
		
		if ( evt.target == testProcedure) {
			testProcedure.setEnabled(false);
			Image image = lo.canvas.getImage();
			
			// the replace.replaceAllments makes the interpreter code have the same lines as the normal one
			// and we replace.replaceAll \t (Tabstop) with " " so the interpreter will like it ;-)
			StringTokenizer t = new StringTokenizer(replace.replaceAll(replace.replaceAll(lo.ta.getText(),"\n\n","\n \n"),"\t","                "), "\n");
			int numLines = t.countTokens();
			String[] code = new String[numLines];
			for (int i=0; i < numLines; i++) code[i] = t.nextToken();
			
			lo.canvas.reset();
			code codeObj = new code(code, numLines, lo.canvas, lo);
			
			lo.setButtonsToExecuteAll();
			
			Hashtable variables = new Hashtable();
			if (useVar.getState())
			{
				t = new StringTokenizer(var.getText() + "," , ",");
				StringTokenizer st;
				while ( t.hasMoreTokens()) {
					try {
						st = new StringTokenizer(t.nextToken(), "=");
						variables.put(st.nextToken(), "" + Integer.parseInt(st.nextToken()));
					} catch (Exception e) {
						status.setText("error in the variable field!");
						testProcedure.setEnabled(true);
						return true;
					}
				}
			}
			
			boolean answer = codeObj.execute(procedureName.getText(), variables);
			lo.setButtonsToNormal();
			
			if (answer == true) {
				lo.setEnabled(false);
				procedureName.setEnabled(false);
				upload.setEnabled(true);
				testProcedure.setEnabled(false);
				status.setText(Data.button[19][language]);
			} else {
				testProcedure.setEnabled(true);
				status.setText(Data.button[20][language]);
			}
			this.invalidate();
			this.pack();
			return true;	
		}
		if ( evt.target == upload) {
			new doUpload(this);
			return true;
		}
		return false;
	}
}

class HTTPURL
{
	String server;
	int port;
	
	HTTPURL(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	String getContent(String request) {
		try {
			String answer = "";
			if ( port == -1 ) port = 80;
			Socket socket = new Socket(server, port);
			System.out.println("server:"+server+" port:"+port);
			
			BufferedReader serverIn
			= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			DataOutputStream serverOut = 
				new DataOutputStream(
						socket.getOutputStream());
			
			serverOut.writeBytes(request);
			
			String tempIn = serverIn.readLine();
			
			while (tempIn != null) {
				answer = answer + tempIn + "\n";
				tempIn = serverIn.readLine();
			}
			
			for (int i = 0; i < answer.length()-2; i++) {
				if (answer.substring(i,i+2).equals("\n\n")) {
					answer = answer.substring(i+2, answer.length());
				}
			}
			System.out.println("answer ="+answer+"|");
			
			return answer;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
}