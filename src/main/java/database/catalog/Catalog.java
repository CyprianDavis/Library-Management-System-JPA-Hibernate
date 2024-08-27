package database.catalog;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
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
	protected static EntityTransaction transaction = null;
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
	public static Book insertBook(Book book) {
		transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(book);
		transaction.commit();
		return book;
	}
	/** 
	 * 
	 * @param isbn
	 * @returns return if the ISBN number exists
	 */
	public static boolean checkISBN(String isbn) {
		try {
			Book book= entityManager.createNamedQuery("Book.checkISBN", Book.class).setParameter("isbn", isbn).getSingleResult();
			if (book!=null) 
				return true;
			
		}catch(NoResultException e) {
			return false;
		}
		return false;  
	}
	
	/**
	 * Search and returns the book from catalog if it exists 
	 * @param bookId
	 * @returns Book or null
	 */
	public static Book findBook(String bookId) {
		Book book = entityManager.find(Book.class, bookId);
		return book;
	}
	/**
	 * 
	 * @param bookId
	 * @returns true if the book id exists in the database or false otherwise
	 */
	public static boolean bookExists(String bookId) {
		Book book =entityManager.find(Book.class, bookId);
		if(book!=null)
			return true;
		return false;
	}
	
	/**
	 * Removes book from the database catalog
	 * @param bookId
	 * @returns true if the book is removable
	 */
	public static boolean removeBook(String bookId) {
		Book book = entityManager.find(Book.class, bookId);
		transaction = entityManager.getTransaction();
		transaction.begin();
		if(book!=null) {
			entityManager.remove(book);
			transaction.commit();
			return true;
		}
		return false;
		
		}
	/**
	 * 
	 * @param bookId
	 * @returns true if the book is checked out or false otherwise
	 */
	public static boolean isBookCheckedout(String bookId) {
		try {
			Book book = entityManager.createNamedQuery("Book.isCheckedOut", Book.class).setParameter("id", bookId).getSingleResult();
			if(book!=null) 
				return true;
		}catch(NoResultException e) {
			return false;
		}
		return false;
		
		
	}
	/**
	 * 
	 * @returns list of the books from the database
	 */
	public static ObservableList<Book> getCatalogBooks(){
		ObservableList<Book> catalog = FXCollections.observableArrayList(); //List of books from the database
		catalog.addAll(entityManager.createNamedQuery("Book.Catalog", Book.class).getResultList());
		return catalog;
	}
	/**
	 * 
	 * @param search 
	 * @returns books from data using text search 
	 */
	public static ObservableList<Book> searchBooks(String search){
		ObservableList<Book> catalog = FXCollections.observableArrayList(); //List of books from the database
		catalog.addAll(entityManager.createNamedQuery("Book.searchBook", Book.class).setParameter("search", "%"+search+"%").getResultList());
		return catalog;
	}
	
	
	/**
	 * 
	 * @returns the total number of books the library owns
	 */
	public static int totalNumberOfBooks() {
		String numOfBks="SELECT COUNT(b)FROM Book b";
		try {		
			 Long count = (Long) entityManager.createQuery(numOfBks).getSingleResult();
		        return count.intValue(); // Convert Long to int
		}catch(NoResultException e){
			return 0;
		}
	}
		
	/**
	 * 
	 * @param status
	 * @returns number of books based on their status
	 */
	public static int getBooksByStatus(String status) {
		Long count = null;
		try {
			count =(Long) entityManager.createNamedQuery("Book.numberOfBooks", Long.class).setParameter("status", status).getSingleResult();
			return count.intValue(); // Convert Long to int
		}catch(NoResultException e) {
			
		}
		return 0;
		
	}
}
