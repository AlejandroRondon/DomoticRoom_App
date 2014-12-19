package utilitiesApps;



public class ValNumber {

 String msgErrNumber = "\nPlease Enter a number\n";
 String msgErrChar = "\nPlease Enter a char\n";
 String msgErrString= "\nPlease Enter a String\n";
	
	public boolean isInteger(String string){
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	public boolean isFloat(String string){
		try {
			Float.parseFloat(string);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public  boolean isHexa(String cadena) {
	    try {
	        Long.parseLong(cadena,16);
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
	
	public  boolean isBinary(String cadena) {
	    try {
	        Long.parseLong(cadena,2);
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
	
}
