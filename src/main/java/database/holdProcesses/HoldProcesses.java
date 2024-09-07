package database.holdProcesses;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import data.model.book.Book;
import data.model.library.Hold;
import data.model.member.Member;
import database.library.LibraryOperations;
import enitiyFactory.EntityFactoryGen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */

public class HoldProcesses {
	protected static EntityManager entityManager =EntityFactoryGen.getEntityManager();
	 static EntityTransaction transaction = null;
	/**
	 * 
	 * @returns the next id number from the id generation table for the next Transaction
	 */
	 public static int getNextTableGeneratorValue() {
		
		 int nextValue =0;
		 try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			 // SQL query to get the next value for the sequence
			 String selectSql = "SELECT idValue FROM ID_Gen WHERE idName='HoldId'";
		        Query selectQuery = entityManager.createNativeQuery(selectSql);
		        int currentValue = ((Number) selectQuery.getSingleResult()).intValue();

		        // Increment the value
		         nextValue = currentValue + 0;

		        // SQL query to update the sequence value
		        String updateSql = "UPDATE ID_GEN SET idValue = :nextValue WHERE idName = 'HoldId'";
		        Query updateQuery = entityManager.createNativeQuery(updateSql);
		        updateQuery.setParameter("nextValue", nextValue);
		        int rowsUpdated = updateQuery.executeUpdate();
		        if(rowsUpdated ==0) {
		        	throw new RuntimeException("No rows updated. Check if the 'HoldId' record exists.");
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
	 * 
	 * @param bookId
	 * @returns true if the book has a hold or false otherwise
	 */
	public static boolean bookHasHold(Book bk) {
		try {
			Book book = entityManager.createNamedQuery("Hold.bookHasHold", Book.class).setParameter("book", bk).getSingleResult();
			if(book!=null) {
				return true;
			}
		}catch(NoResultException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 
	 * @param book
	 * @param member
	 * @returns true if the hold already exists in the database and false otherwise
	 */
	public static boolean holdExists(Book book, Member member) {
		try {
			Hold hold = entityManager.createNamedQuery("Hold.holdExist", Hold.class).setParameter("member", member).setParameter("book", book).getSingleResult();
			if(hold!=null)
				return true;
		}catch(NoResultException e) {
			e.printStackTrace();
		}
	
		return false;
		}
/**
 * Places hold on the book 
 * @param hold
 */
	public static void placeHold(Hold hold) {
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			LibraryOperations.createTransaction("Reserve Book",hold.getBook(), hold.getMember());
			entityManager.persist(hold);
			transaction.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Removes hold from a book placed on by given member
	 * @param member
	 * @param book
	 * @param status
	 * @returns true if the hold is removed successfully or false otherwise
	 */
	public static boolean removeHold(Member member, Book book) {
		int rows =0;
		transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			rows = entityManager.createNamedQuery("Hold.removeHold").setParameter("member", member).setParameter("book", book).executeUpdate();
			LibraryOperations.createTransaction("Cancel Reservations",book, member);
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rows>0;
	}
	/**
	 * 
	 * @param book
	 * @returns the list of holds of the book from the database
	 */
	public static ObservableList<Hold> getHolds(Book book){
		ObservableList<Hold> holds = FXCollections.observableArrayList(); //List of Holds on a book from the database
		holds.addAll(entityManager.createNamedQuery("Hold.getHolds", Hold.class).setParameter("book", book).getResultList());
		return holds;
}
	}