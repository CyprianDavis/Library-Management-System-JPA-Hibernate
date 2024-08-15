package enitiyFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
public class EntityFactoryGen {
	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Library");
	  
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
		  
	  }

}
