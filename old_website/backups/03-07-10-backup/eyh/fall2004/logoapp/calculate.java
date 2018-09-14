public class calculate
{	
	
	public static int docalculate(String expression) {
		return logoCanvas.doubleToInt(calculateWithDouble(expression));
	}
			
	public static double calculateWithDouble(String expression)
	{
		String exp = expression;
				
		double result = 0;
		int pos = -1;
		int lastpos = -1;
		int nextpos = -1;
		int lastopen = -1;
		int firstclose = -1;
		int len;
		double klammerresult;
		double tempresult;
				
		while (true){
			if ( isNumber(exp) ) {
				result = Double.valueOf(exp).doubleValue();
				//System.out.println("+Ok Zahl!!!");
				break;
			} else
			{
				//System.out.println("Es ist keine Zahl!");
				// find first (...) structure
				lastopen = -1;
				firstclose = -1;
				len = exp.length();
				for (int i=0; i < len; i++) {
					if (exp.substring(i,i+1).equals("(")) lastopen = i;
					if (exp.substring(i,i+1).equals(")")) {
						firstclose = i;
						break;
					}
				}
				if (lastopen != -1) {
					//System.out.println("Es ist eine Klammerkonstruktion!");

					klammerresult = calculateWithDouble(exp.substring(lastopen+1,firstclose));
					exp = exp.substring(0,lastopen) + klammerresult + exp.substring(firstclose+1,len);
					//System.out.println(exp);
				}
				else {
					// see if the expression contains more than 0123456789/*-+.
					// if there are other signs return 0
					if (containsOnlyMathSigns(exp) == false) {
						Logo.L.canvas.errorMessage("invalid sign in math-operation so assuming 0!");
						return 0;
					}
					
					
					// here we finaly realy calculate!
					// here do all * and / calculations
					while (false == (-1 == getPosition(exp,"*") & -1 == getPosition(exp,"/"))) {
						//System.out.println("len of exp in * and - Schleife :" + exp.length());
						
						// going from left to right is there first a * or a /? if there is none pos = -1 and break
							if (( getPosition(exp, "*") > getPosition(exp, "/") & getPosition(exp, "/") != -1) |
									( getPosition(exp, "*") == -1 & getPosition(exp, "/") != -1) )	{
									
									pos = getPosition(exp, "/");
							}
							else {
									pos = getPosition(exp, "*");
							}
							//System.out.println("pos :" + pos);
							//System.out.println("pos direct =" + getPosition(exp, "/"));

							
							if (pos == -1) break;
							lastpos = getLastSign(exp, pos);
							nextpos = getNextSign(exp, pos);
						
						tempresult = 0;	
						
						if (exp.substring(pos,pos+1).equals("*")) {
							tempresult = Double.valueOf(exp.substring(lastpos,pos)).doubleValue() * Double.valueOf(exp.substring(pos+1,nextpos)).doubleValue();
						}
						if (exp.substring(pos,pos+1).equals("/")) {
							tempresult = Double.valueOf(exp.substring(lastpos,pos)).doubleValue() / Double.valueOf(exp.substring(pos+1,nextpos)).doubleValue();
						}
		
						//System.out.println("tempresult :" + tempresult);

						exp = exp.substring(0,lastpos) + tempresult + exp.substring(nextpos,exp.length());
						//System.out.println("exp :" + exp);
					}
						//System.out.println("exp after while =" + exp);

					// here do all + and - calculations
						// replace all number-number by number+-number and -- by +
							while (true) {
								pos = getPosition(exp,"-");
								if (pos == -1) { break;}
								else {
									if( exp.substring(pos+1,pos+2).equals("-")) {
										// replace -- by a simple +
										exp = exp.substring(0,pos) + "+" + exp.substring(pos+2,exp.length());
									}
									else {
										if (false == isMathSign(exp.substring(pos-1,pos))) {
											// replace number-number by +- so there will be no - as an operation in the calculations
											exp = exp.substring(0,pos) + "+" + exp.substring(pos,exp.length());
										}
										else { break; }
									}
								}
							}
						
					while (true) {
							pos = getPosition(exp, "+");
							if (pos == -1) break;
	
							lastpos = getLastSign(exp, pos);
							nextpos = getNextSign(exp, pos);
						tempresult = 0;	
						tempresult = Double.valueOf(exp.substring(lastpos,pos)).doubleValue() + Double.valueOf(exp.substring(pos+1,nextpos)).doubleValue();
						exp = exp.substring(0,lastpos) + tempresult + exp.substring(nextpos,exp.length());
					}					
				}
			}
		}
		//System.out.println("result ="+result);
		return result;
	}
	
	public static String replace(String data, String replaceThis, String replaceWith)
	{
		String string = data;
		while (true)
		{
			if (replaceOnce(string, replaceThis, replaceWith).equals(string)) break;
			string = replaceOnce(string, replaceThis, replaceWith);
		}
		return string;
	}
		
	public static boolean containsOnlyMathSigns(String exp) {
		//If there are only math signs replacing them all with "" should give ""
		String s = exp;
		s = replace(s,"0","");
		s = replace(s,"1","");
		s = replace(s,"2","");
		s = replace(s,"3","");
		s = replace(s,"4","");
		s = replace(s,"5","");
		s = replace(s,"6","");
		s = replace(s,"7","");
		s = replace(s,"8","");
		s = replace(s,"9","");
		s = replace(s,"+","");
		s = replace(s,"-","");
		s = replace(s,"*","");
		s = replace(s,"/","");
		s = replace(s,".","");
		if (s.equals("")) return true;
		return false;
	}
	
	public static boolean isNumber(String exp)
	{
		String s = exp;
		s = replace(s,"0","");
		s = replace(s,"1","");
		s = replace(s,"2","");
		s = replace(s,"3","");
		s = replace(s,"4","");
		s = replace(s,"5","");
		s = replace(s,"6","");
		s = replace(s,"7","");
		s = replace(s,"8","");
		s = replace(s,"9","");
		s = replace(s,".","");
		if (s.equals("")) return true;
		return false;
	}
	
	public static String replaceOnce(String data, String replaceThis, String replaceWith)
	{
		String string = data;
		int len = string.length();
		int repLen = replaceThis.length();
				
		for(int i=0; i <= len-repLen; i++) {
			if (string.substring(i,i+repLen).equals(replaceThis)) {
				string = string.substring(0, i) + replaceWith + string.substring(i+repLen, len);
				break;
			}
		}
		return string;	
	}
	
	public static int getNextSign(String exp, int pos) {
		int nextpos = -1;
		
		if (pos+2 <= exp.length()) {
				for (int i=pos+2; i < exp.length(); i++) {
						//System.out.println("substring bei i="+i+" :"+exp.substring(i,i+1)+"|");
					if (isMathSign(exp.substring(i,i+1))) {nextpos = i; break;}
				}
		}

		//System.out.println("nextpos before correction:" + nextpos);
		if (nextpos == -1) nextpos = exp.length();
		//System.out.println("nextpos :" + nextpos);
		return nextpos;
	}
		
	public static int getLastSign(String exp, int pos) {
		int lastpos = -1;
		
		for (int i=1; i < pos; i++) {
			if (isMathSign(exp.substring(i,i+1))) {lastpos = i; break;}
		}
								
		//System.out.println("lastpos before correction:" + lastpos);

		
		if (lastpos == -1) {lastpos = 0;}
		else {
				// See if a "-" represents a negativ number and not an operation
				if (exp.substring(lastpos,lastpos+1).equals("-") & isMathSign(exp.substring(lastpos-1,lastpos)))	{
						lastpos--;
				}
				//lastpos++;
		}
						
		//System.out.println("lastpos :" + lastpos);
		//System.out.println("exp :" + exp);
		return lastpos;
	}

	

	public static int getPosition(String exp, String lookFor)
	{
		for (int i=1; i < exp.length(); i++) {
			if (exp.substring(i,i+1).equals(lookFor)) {return i;}
		}
		return -1;
	}
	
	
	public static boolean isMathSign(String sign) {
		if (sign.equals("*") | sign.equals("+") | sign.equals("-") | sign.equals("/"))
			{	return true; }
		else 
			{ return false;}
	}

}