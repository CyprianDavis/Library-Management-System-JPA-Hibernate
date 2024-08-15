package validateInput;


/**
 * 
 * @author CYPRIAN DAVIS
 * Handles user input validation
 *
 */

public class ValidateUserInput {
	/**
	 * Returns true if the String contains only characters and returns false otherwise
	 * @param txt
	 * @return
	 */
	public static boolean validateOnlyCharacterInput(String txt) {
		for(int i =0; i<txt.length(); i++) {
			if(!Character.isLetter(txt.charAt(i)) && txt.charAt(i)!=' ') {
				return false;
			}
			
		}
		return true;
		
	}
	/**
	 * 
	 * @param txt
	 * @returns true if the string can be converted to integer representation or false otherwise
	 */
	public static boolean validateOnlyIntegerInput(String txt) {
		try {
			Integer.parseInt(txt);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
}
