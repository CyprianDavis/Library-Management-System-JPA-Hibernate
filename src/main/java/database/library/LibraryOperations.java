package database.library;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import data.model.book.Book;
import data.model.library.IssueBook;
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
	
	public static String issueBook(Member member,Book book,String issuedOn) {
		member.getIssuedBooks().add(book);
		book.setStatus("Issued");
		transaction = entityManager.getTransaction();
		transaction.begin();
		IssueBook issuedBk = new IssueBook(member,book);
		issuedBk.setDateOfIssuing(issuedOn);
		entityManager.persist(issuedBk);
		String dueDate = issuedBk.getDueDate();
		transaction.commit();
		
		return dueDate;
		
	}
	public static boolean returnBook(Book book) {
		return false;
		
	}
	public static boolean renewBook(Book book) {
		return false;
		
	}
	
	
	
	
	

}
