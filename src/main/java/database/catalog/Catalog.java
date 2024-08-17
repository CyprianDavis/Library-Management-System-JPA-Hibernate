package database.catalog;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import data.model.book.Book;
import enitiyFactory.EntityFactoryGen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * 
 * @author CYPRIAN DAVIS
 *
 */

public class Catalog {
	protected static EntityManager entityManager = EntityFactoryGen.getEntityManager();
	static EntityTransaction transaction = null;
	/**
	 * 
	 * @returns the next id number from the id generation table for the next book
	 */
	 public static int getNextTableGeneratorValue() {
		 
		 int nextValue =0;
		 try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			 // SQL query to get the next value for the sequence
			 String selectSql = "SELECT idValue FROM ID_Gen WHERE idName='BookId'";
		        Query selectQuery = entityManager.createNativeQuery(selectSql);
		        int currentValue = ((Number) selectQuery.getSingleResult()).intValue();

		        // Increment the value
		         nextValue = currentValue + 1;

		        // SQL query to update the sequence value
		        String updateSql = "UPDATE ID_GEN SET idValue = :nextValue WHERE idName = 'BookId'";
		        Query updateQuery = entityManager.createNativeQuery(updateSql);
		        updateQuery.setParameter("nextValue", nextValue);
		        int rowsUpdated = updateQuery.executeUpdate();
		        if(rowsUpdated ==0) {
		        	throw new RuntimeException("No rows updated. Check if the 'BookId' record exists.");
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
	 * Inserts a new book in the Library Catalog
	 * @param book
	 * @return
	 */
	public static boolean insertBook(Book book) {
		boolean value = true;
		
		
		return value;
	}
	/** 
	 * 
	 * @param isbn
	 * @returns return if the ISBN number exists
	 */
	public static boolean checkISBN(String isbn) {
		
	        return false;
		}
	
	/**
	 * Search and returns the book from catalog if it exists 
	 * @param bookId
	 * @returns Book or null
	 */
	public static Book searchBook(String bookId) {
		Book book = null;
		
		
		return book;
	}
	/**
	 * 
	 * @param id or ISBN
	 * @returns true if the book  exists in the database or false otherwise
	 */
	public static boolean searhBook(String id) {

       
        return false;
	}
	/**
	 * Removes book from the database catalog
	 * @param bookId
	 * @returns true if the book is removable
	 */
	public static boolean removeBook(String bookId) {
		boolean condition = true;
		
			
		return condition;
		}
	/**
	 * 
	 * @param bookId
	 * @returns true if the book is checked out or false otherwise
	 */
	public static boolean isBookCheckout(String bookId) {
		
		return false;
	}
	/**
	 * 
	 * @returns list of the books from the database
	 */
	public static ObservableList<Book> getCatalogBooks(){
		ObservableList<Book> catalog = FXCollections.observableArrayList(); //List of books from the database
		
		return catalog;
	}
	/**
	 * 
	 * @param search 
	 * @returns books from data using text search 
	 */
	public static ObservableList<Book> getBooks(String search){
		ObservableList<Book> catalog = FXCollections.observableArrayList(); //List of books from the database
		
			
		
		return catalog;
	}
	/**
	 * 
	 * @param searchs book based on book id or ISNB
	 * @returns a Book 
	 */
	public static Book getBook(String search) {
		Book book = new Book();
		
			
		
		return book;
	}
	
	/**
	 * 
	 * @returns the total number of books the library owns
	 */
	public static int totalNumberOfBooks() {
		int count=0;
		return count;
		
	}
		
		
	
	/**
	 * 
	 * @param status
	 * @returns number of books based on their status
	 */
	public static int getBooksByStatus(String status) {
		int count =0;
		
		return count;
		
	}
}
