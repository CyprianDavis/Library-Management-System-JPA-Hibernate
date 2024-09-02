package database.library;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import data.model.book.Book;
import data.model.member.Member;
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
	 * 
	 * @param member
	 * @param book
	 * @return
	 */
	
	public static boolean issueBook(Member member,Book book) {
		member.getIssuedBooks().add(book);
		book.setStatus("Issued");
		return false;
		
	}
	public static boolean returnBook(Book book) {
		return false;
		
	}
	public static boolean renewBook(Book book) {
		return false;
		
	}
	
	
	
	
	

}
