public class replace
{
	static String replaceAll(String in, String lookFor, String replaceWith) {
		String input = in;
		for (int i = 0; i <= (input.length()-lookFor.length()); i++) {
			if ( input.substring(i,i+lookFor.length()).equalsIgnoreCase(lookFor) ) {
				input = input.substring(0,i)+replaceWith+input.substring(i+lookFor.length(),input.length());
				i--;
			}
		}
		return input;
	}
	
	static String replaceAllSignsInAnfuerungszeichen(String input, String lookFor, String replaceWith) {
		String in = input;
		boolean isIn = false;
		int i = 0;
		while ( i < in.length() ) {
			if ( in.substring(i,i+1).equals("\"") ) {
				if ( isIn == true ) isIn = false;
				else if ( isIn == false ) isIn = true;
			} else if ( in.substring(i,i+1).equals(lookFor) & isIn == true) {
				in = in.substring(0,i) + replaceWith + in.substring(i+1,in.length());
			}
			i++;
		}
		return in;
	}
}