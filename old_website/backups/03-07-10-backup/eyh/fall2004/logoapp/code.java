import java.util.*;
import java.awt.*;

public class code
{
	String code[];
	int lenOfCode = 0;
	wait w = new wait();
	logoCanvas canvas;
	Logo l;
	
	code(String[] code, int lenOfCode, logoCanvas canvas, Logo l) {
		this.l = l;
		this.canvas = canvas;
		this.lenOfCode = lenOfCode;
		this.code = new String[lenOfCode];
		for (int i = 0; i < lenOfCode; i++) this.code[i] = code[i];
	}

	public boolean execute(String methodName, Hashtable lV)
	{
		Hashtable localVariables = (Hashtable) lV.clone();
		
		StringTokenizer t;
		String varchanges;
		String temp;
		boolean isReturn = false;
		
		int startline = 0;
		while (startline < lenOfCode-1)
		{
			try {
				t = new StringTokenizer(code[startline], " ");
				temp = t.nextToken();
				//System.out.println("temp : " + temp + "/in startline :" +startline +"/lenOfCode :" + lenOfCode);
				if (temp.equalsIgnoreCase("procedure") | temp.equalsIgnoreCase("prozedur") | temp.equalsIgnoreCase("procédure")) {
					if (t.nextToken().equalsIgnoreCase(methodName)) break;
				}
			} catch (Exception e) {}
			startline++;
		}
		//System.out.println("startline ="+startline);
		
		// find the return operation of the method
		int returnline = startline;
		isReturn = false;
		while (returnline < lenOfCode)
		{
			try {
				t = new StringTokenizer(code[returnline], " ");
				temp = t.nextToken();
				if (temp.equalsIgnoreCase("end") | temp.equalsIgnoreCase("ende") | temp.equalsIgnoreCase("fin")) {
					isReturn = true;
					break;
				}
			} catch (Exception e) {}
			returnline++;
		}
		//System.out.println("returnline ="+returnline);
		
		if ( startline == lenOfCode-1 ) {
			canvas.errorMessage("procedure \"" + methodName + "\" not found!");
			return false;
		}
		if ( isReturn == false) {
			canvas.errorMessage("procedure \"" + methodName + "\" has no end!");
			return false;
		}
		
		for (int line=startline; line <= returnline; line++)
		{
			if (l.isStepByStep == true) {
				while (true) {
					if (l.nextStep == true) break;
					w.run();
				}
				l.codeList.select(line);
				l.codeList.makeVisible(line);
				//System.out.println(Logo.code[line]);
				l.nextStep = false;
			}
		
			varchanges = interpreteLine(changeSeperators(code[line].toLowerCase()), localVariables);
			if (varchanges.equalsIgnoreCase("error")) return false;
			if (varchanges.length() > 6) {
				if (varchanges.substring(0,6).equalsIgnoreCase("change")) {
					 t = new StringTokenizer(varchanges.substring(6,varchanges.length()), "=");
					 localVariables.put(t.nextToken().toLowerCase(), t.nextToken());
				}
			}
		}
		return true;
	}
	
	public String changeSeperators(String line)
	{
		
		String localLine = line;
		
		localLine = localLine.trim();	
		localLine = replace.replaceAll(localLine, "=", "+49307125410");
		localLine = replace.replaceAll(localLine, "+49307125410", " =");
		
		try {
			StringTokenizer t = new StringTokenizer(localLine, " ");
			StringTokenizer st = new StringTokenizer(localLine + ",", ",");
			
			String temp = t.nextToken();
			if ( st.nextToken().length() > temp.length() ) {
				localLine = localLine.substring(0, temp.length()) + "," + localLine.substring(temp.length()+1, localLine.length());
			}
		} catch (Exception e) {}
		
		localLine = replace.replaceAll(localLine, " " , "");
		
		return localLine;
	}
	
	// ---------------------------------- public String interpreteLine ---------------------------------------
	
	public String interpreteLine(String line, Hashtable lVar)
	{
		line = line.toLowerCase();
		StringTokenizer t = new StringTokenizer(line, ",");
		String command = line;
		try {
			command = t.nextToken();
		} catch (NoSuchElementException e) {}
		
		int numLines = t.countTokens() + 1;
		boolean answer;

		try {
		if (	command.equalsIgnoreCase("procedure") |
					command.equalsIgnoreCase("prozedur") |
					command.equalsIgnoreCase("procédure") |
					command.equalsIgnoreCase("end")|
					command.equalsIgnoreCase("ende") |
					command.equalsIgnoreCase("fin") ) {
			return "ok";	
		}
		
		if (line.length() > 0) {
			if ( line.trim().substring(0,1).equalsIgnoreCase("/") ) return "ok";
		}
		
		if (command.equalsIgnoreCase("rt") | command.equalsIgnoreCase("right") | command.equalsIgnoreCase("re") | command.equalsIgnoreCase("rechts") | command.equalsIgnoreCase("dr") | command.equalsIgnoreCase("droite")) {
				int angle = calculate.docalculate(replaceVariables(t.nextToken(), lVar));
				canvas.turnright(angle);
				return "ok";	
		}

		if (command.equalsIgnoreCase("lt") | command.equalsIgnoreCase("left") | command.equalsIgnoreCase("li") | command.equalsIgnoreCase("links") | command.equalsIgnoreCase("ga") | command.equalsIgnoreCase("gauche")) {
				int angle = -(calculate.docalculate(replaceVariables(t.nextToken(), lVar)));
				canvas.turnright(angle);
				return "ok";	
		}

		if (command.equalsIgnoreCase("go") | command.equalsIgnoreCase("vw") | command.equalsIgnoreCase("vorwärts") | command.equalsIgnoreCase("va")  | command.equalsIgnoreCase("vorwarts")) {
				int trip = calculate.docalculate(replaceVariables(t.nextToken(), lVar));
				canvas.go(trip);
				return "ok";	
		}

		if (command.equalsIgnoreCase("goto") | command.equalsIgnoreCase("gehezu") | command.equalsIgnoreCase("vavers")) {
			
				String procedure = t.nextToken();
				
				Hashtable gotoParams = new Hashtable();
				StringTokenizer keyValue;
				while (t.hasMoreElements()) {
					keyValue = new StringTokenizer(t.nextToken(), "=");
					gotoParams.put( keyValue.nextToken(), "" + calculate.docalculate(replaceVariables(keyValue.nextToken(), lVar)) );
				}
				
				answer = execute(procedure, gotoParams);
				if ( answer == false) return "error";
				return "ok";
		}

		if (command.equalsIgnoreCase("repeat") | command.equalsIgnoreCase("wh") | command.equalsIgnoreCase("wiederhole") | command.equalsIgnoreCase("repeter")) {
				int loop = calculate.docalculate(replaceVariables(t.nextToken(), lVar));
				String procedurename = t.nextToken();
				
				Hashtable gotoParams = new Hashtable();
				StringTokenizer keyValue;
				while (t.hasMoreElements()) {
					keyValue = new StringTokenizer(t.nextToken(), "=");
					gotoParams.put( keyValue.nextToken(), "" + calculate.docalculate(replaceVariables(keyValue.nextToken(), lVar)) );
				}

				for (int i=1; i <= loop; i++) {
					gotoParams.put("loop", "" + i);
					answer = execute(procedurename, gotoParams);
					if ( answer == false) return "error";
				}
				return "ok";
		}
		
		if (command.equalsIgnoreCase("newvar") | command.equalsIgnoreCase("neuevar") | command.equalsIgnoreCase("nouveauvar")) {
				StringTokenizer keyValue = new StringTokenizer(t.nextToken(), "=");
				return "change" + keyValue.nextToken() + "=" + calculate.docalculate(replaceVariables(keyValue.nextToken(), lVar));
		}
		
		if (command.equalsIgnoreCase("up") | command.equalsIgnoreCase("penup") | command.equalsIgnoreCase("hoch") | command.equalsIgnoreCase("stifthoch") | command.equalsIgnoreCase("marcher")) {
			canvas.isPenUp = true;
			return "ok";
		}

		if (command.equalsIgnoreCase("down") | command.equalsIgnoreCase("pendown") | command.equalsIgnoreCase("runter") | command.equalsIgnoreCase("stiftrunter") | command.equalsIgnoreCase("dessiner")) {
			canvas.isPenUp = false;
			return "ok";
		}
		
		if (command.equalsIgnoreCase("color") | command.equalsIgnoreCase("farbe") | command.equalsIgnoreCase("couleur")) {
			String c = t.nextToken();

			if (c.equalsIgnoreCase("black") | c.equalsIgnoreCase("schwarz") | c.equalsIgnoreCase("noir")) { canvas.turtle.color = Color.black; return "ok";}
			if (c.equalsIgnoreCase("white") | c.equalsIgnoreCase("weiss") | c.equalsIgnoreCase("blanc")) { canvas.turtle.color = Color.white; return "ok";}
			if (c.equalsIgnoreCase("red") | c.equalsIgnoreCase("rot") | c.equalsIgnoreCase("rouge")) { canvas.turtle.color = Color.red; return "ok";}
			if (c.equalsIgnoreCase("blue") | c.equalsIgnoreCase("blau") | c.equalsIgnoreCase("bleu")) { canvas.turtle.color = Color.blue; return "ok";}
			if (c.equalsIgnoreCase("gray") | c.equalsIgnoreCase("grau") | c.equalsIgnoreCase("gris")) { canvas.turtle.color = Color.gray; return "ok";}
			if (c.equalsIgnoreCase("green") | c.equalsIgnoreCase("grun") | c.equalsIgnoreCase("grün") | c.equalsIgnoreCase("vert")) { canvas.turtle.color = Color.green; return "ok";}
			if (c.equalsIgnoreCase("orange")) { canvas.turtle.color = Color.orange; return "ok";}
			if (c.equalsIgnoreCase("yellow") | c.equalsIgnoreCase("gelb") | c.equalsIgnoreCase("jaune")) { canvas.turtle.color = Color.yellow; return "ok";}
	
			if (c.equalsIgnoreCase("cyan")) { canvas.turtle.color = Color.cyan; return "ok";}
			if (c.equalsIgnoreCase("darkGray")) { canvas.turtle.color = Color.darkGray; return "ok";}
			if (c.equalsIgnoreCase("pink")) { canvas.turtle.color = Color.pink; return "ok";}
			if (c.equalsIgnoreCase("lightGray")) { canvas.turtle.color = Color.lightGray; return "ok";}
			if (c.equalsIgnoreCase("magenta")) { canvas.turtle.color = Color.magenta; return "ok";}

			canvas.errorMessage("unknown color :\"" + c + "\"");
			return "error";
		}
		
		Enumeration e = lVar.keys();
		while(e.hasMoreElements()) {
			if( ((String) e.nextElement()).equalsIgnoreCase(command) ) {
				String value = t.nextToken();
				return "change" + command + "=" + calculate.docalculate(replaceVariables(value.substring(1,value.length()), lVar));
			}
		}
		
		// see if the line is made out of spaces and say that else the command is unknown
		String temp = "";
		for (int i=1; i <= command.length(); i++) {
			temp = temp + " ";
		}
		if (temp.equalsIgnoreCase(command)) return "ok";
			
		} catch (Exception e) {
			canvas.errorMessage("error in command :\"" + command + "\"");
			return "error";
		}
		canvas.errorMessage("unknown command :\"" + command + "\"");
		return "error";
	}
	
	static String replaceVariables(String input, Hashtable h) {
		Vector vars = ordonnateAfterKeyLength(h);
		String in = input;
		Enumeration e = vars.elements();
		variable temp;
		
		while(e.hasMoreElements()) {
			temp = (variable) e.nextElement();
			in = replace.replaceAll(in, temp.name, "" + temp.value);
		}
		return in;
	}
	
	static Vector ordonnateAfterKeyLength(Hashtable h) {
		Vector answer = new Vector();
		Enumeration e = h.keys();
		Vector source = new Vector();
		while(e.hasMoreElements()) {
			source.addElement(e.nextElement());
		}
		
		int tempLength = 0;
		int tempLongest = 0;
		
		while(source.size() > 0) {
			tempLength = 0;
			tempLongest = 0;
			for( int i = 0; i < source.size(); i++) {
				if (((String) source.elementAt(i)).length() > tempLength ) {
					tempLength = ((String) source.elementAt(i)).length();
					tempLongest = i;
				}
			}
			answer.addElement(new variable(	(String) source.elementAt(tempLongest),
																			Integer.parseInt((String) h.get(source.elementAt(tempLongest))) ));
			source.removeElement(source.elementAt(tempLongest));
		}
		return answer;
	}
}

class variable
{
	String name;
	int value;
	
	variable(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String toString() {
		return "{" + name + "=" + value + "}";
	}
}