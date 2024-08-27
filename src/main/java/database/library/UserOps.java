package database.library;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import data.model.user.PasswordUtils;
import data.model.user.User;
import enitiyFactory.EntityFactoryGen;

public class UserOps {
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
		User user = null;
		try {
			user = entityManager.createNamedQuery("User.UserPasswordExists",User.class)
					.setParameter("password", passWord).getSingleResult();
			
		}catch(NoResultException e) {
			
		}
		return	PasswordUtils.checkPassword(passWord, user.getPassword());
	}
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return user object from the databse
	 */
	public static User getUser(String userName, String password) {
		User user = null;
		try {
			 user = entityManager.createNamedQuery("User.login", User.class).setParameter("userName", userName)
					.setParameter("password", PasswordUtils.hashPassword(password)).getSingleResult();
		
		}catch(NoResultException e) {
			
		}
		return user;
	}
	

}
