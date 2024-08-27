import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
	@Disabled
	@BeforeEach
	void init() {
		 user = new User("CyprianDavis","Cyprian_2024#");
	}
	@Disabled
	@Test
	void testAddUser() {
		UserOps.addUser(user);
	}
	@Test
	void testUserNameExists() {
		Assertions.assertEquals(false,UserOps.userNameExists("CyprianDav"));
	}
	@Test
	void testgetUser() {
		Assertions.assertEquals(null,UserOps.getUser("CyprianDavis"));
		
	}

}
