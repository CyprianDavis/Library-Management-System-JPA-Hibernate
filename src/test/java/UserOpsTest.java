import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.model.user.User;
import database.library.UserOps;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
public class UserOpsTest {
	User user;
	@BeforeEach
	void init() {
		 user = new User("CyprianDavis","Cyprian_2024#");
	}
	@Test
	void testAddUser() {
		UserOps.addUser(user);
	}

}
