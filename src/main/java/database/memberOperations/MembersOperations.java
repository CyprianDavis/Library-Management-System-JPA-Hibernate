package database.memberOperations;
import java.io.File;
import java.io.FileNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
	public static boolean insertMember(Member member, File file) throws FileNotFoundException {
		boolean condition = true;
		
		return condition;
	}
	/**
	 * 
	 * @returns a list of avaiable members in the system from the database
	 */
	public static ObservableList<Member> viewMembers(){
		ObservableList<Member> members = FXCollections.observableArrayList(); //List of members from the database
		
		return members;
	}
	public static ObservableList<Member> searchMemberByID(String memberID){
		ObservableList<Member> members = FXCollections.observableArrayList();
		
		return members;
		
		}
	
	/**
	 * 
	 * @param memberId
	 * @returns member from the database if the member exists or null otherwise
	 */
	public static Member searchMember(String memberId) {
		Member member = null;
		
		
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


