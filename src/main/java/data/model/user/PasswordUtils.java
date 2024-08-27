package data.model.user;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Hash a password
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Check if a password matches a hashed password
    public static boolean checkPassword(String password, String hashed) {
    	boolean value=false;
        try {
        	value=BCrypt.checkpw(password, hashed);
        	return value;
        }catch(IllegalArgumentException e) {
        	
        }
		return value;
    }
}
