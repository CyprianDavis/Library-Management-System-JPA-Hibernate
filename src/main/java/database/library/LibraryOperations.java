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
	
	
	
	

}
