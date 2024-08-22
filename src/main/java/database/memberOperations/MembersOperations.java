package database.memberOperations;
import java.io.FileNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import data.model.member.Member;
import enitiyFactory.EntityFactoryGen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Handles database operations for Member (CRUD Operations)
 * @author CYPRIAN DAVIS
 *
 */

public class MembersOperations {
	//Creates entity Manager
	protected static EntityManager entityManager =EntityFactoryGen.getEntityManager();
	protected static EntityTransaction transaction = null;
	/**
	 * 
	 * @returns the next id number from the id generation table for the next Member
	 */
	 public static int getNextTableGeneratorValue() {
		 EntityTransaction transaction = null;
		 int nextValue =0;
		 try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			 // SQL query to get the next value for the sequence
			 String selectSql = "SELECT idValue FROM ID_Gen WHERE idName='MemberId'";
		        Query selectQuery = entityManager.createNativeQuery(selectSql);
		        int currentValue = ((Number) selectQuery.getSingleResult()).intValue();

		        // Increment the value
		         nextValue = currentValue + 1;

		        // SQL query to update the sequence value
		        String updateSql = "UPDATE ID_GEN SET idValue = :nextValue WHERE idName = 'MemberId'";
		        Query updateQuery = entityManager.createNativeQuery(updateSql);
		        updateQuery.setParameter("nextValue", nextValue);
		        int rowsUpdated = updateQuery.executeUpdate();
		        if(rowsUpdated ==0) {
		        	throw new RuntimeException("No rows updated. Check if the 'MemberId' record exists.");
		        }
		        //commit transaction
		        transaction.commit();
		 }catch(Exception e) {
			 if(transaction !=null && transaction.isActive()) {
				 transaction.rollback();
			 }
			 e.printStackTrace();
			 throw new RuntimeException("Error updating sequence value", e);
		 }
	        
	        return nextValue;
	    }
	/**
	 * Inserts a member in the database
	 * @param member
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static Member insertMember(Member member) throws FileNotFoundException {
		transaction = entityManager.getTransaction();//extracting transaction
		transaction.begin();
		entityManager.persist(member);//persisting member entity
		transaction.commit();
		return member;	
	}
	/**
	 * 
	 * @returns a list of avaiable members in the system from the database
	 */
	public static ObservableList<Member> viewMembers(){
		ObservableList<Member> members = FXCollections.observableArrayList(); //List of members from the database
		members.addAll(entityManager.createNamedQuery("Member.members",Member.class).getResultList());
		return members;
	}
	public static ObservableList<Member> searchMemberByID(String memberID){
		ObservableList<Member> members = FXCollections.observableArrayList();
		members.addAll(entityManager.createNamedQuery("Member.searchById", Member.class).setParameter("idNum", "%"+memberID+"%").getResultList());
		return members;
		}
	/**
	 * 
	 * @param memberId
	 * @returns member from the database if the member exists or null otherwise
	 */
	public static Member findMember(String memberId) {
		Member member =null;
		try {
			 member = entityManager.find(Member.class, memberId);
		}catch(NoResultException e) {
			
		}
		return member;
	}
	/**
	 * 
	 * @param id
	 * @returns true if the member id exists in the database or false otherwise
	 */
	public static boolean searhMember(String id) {

        return false;
	}
	/**
	 * 
	 * @returns the total number of library Members 
	 */
	public static int totalNumberOfMembers() {
		int count=0;
		
		
		return count;
		
		
	}
	
}


