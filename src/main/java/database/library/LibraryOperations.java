package database.library;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import data.model.user.User;
import enitiyFactory.EntityFactoryGen;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
public class LibraryOperations {
	protected static EntityManager entityManager= EntityFactoryGen.getEntityManager();
	protected static EntityTransaction transaction =null;
	/**
	 * Adds new user in the system
	 * @param user
	 * @return User
	 */
	public static User addUser(User user) {
		transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(user);
		transaction.commit();
		return user;
	}
	/**
	 * Checks if the user name exsists and return True or false otherwise
	 * @param userName
	 * @return User
	 */
	public static boolean  userNameExists(String userName) {
		User user = entityManager.find(User.class, userName);
		if(user !=null)
		return true;
		else
			return false;
	}
	/**
	 * Checks if the user password exists
	 * @param passWord
	 * @return
	 */
	public static boolean userPasswordExists(String passWord) {
		User user = entityManager.find(User.class, passWord);
		if(user!=null)
			return true;
		else 
			return false;
	}
	
	
	
	
	
	
	

}
