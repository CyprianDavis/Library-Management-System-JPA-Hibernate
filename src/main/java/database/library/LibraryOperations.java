package database.library;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import data.model.book.Book;
import data.model.library.IssueBook;
import data.model.library.Transaction;
import data.model.member.Member;
import database.transactions.TransactionsOps;
import enitiyFactory.EntityFactoryGen;


/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
public class LibraryOperations {
	protected static EntityManager entityManager= EntityFactoryGen.getEntityManager();
	protected static EntityTransaction transaction =null;
	
	
	private static void createTransaction(String type,Book book,Member member) {
		Transaction trans = new Transaction(type,book,member);//create transaction object
		TransactionsOps.saveTransaction(trans);
	}
	/**
	 * computes the due date for the book
	 * @param duration
	 * @return
	 */
	private static String computeDueDate(int duration) {
		 // Get the current date
        LocalDate today = LocalDate.now();
        
        // Add 14 days to the current date
        LocalDate futureDate = today.plusDays(duration);
        
        // Define a formatter for the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // You can change the pattern to your preferred format
        
        // Format the date as a string
        String futureDateString = futureDate.format(formatter);
		
		return futureDateString;
	}
	/**
	 * 
	 * @param member
	 * @param book
	 * @return
	 */
	public static String issueBook(Member member,Book book) {
		transaction = entityManager.getTransaction();
		transaction.begin();
		member.getIssuedBooks().add(book);
		book.setStatus("Issued");
		entityManager.merge(book);
		entityManager.merge(member);
		IssueBook issuedBk = new IssueBook(member,book);
		issuedBk.setDueDate(computeDueDate(14));//set due date to 14 days from now
		issuedBk.setDateOfIssuing(getDate());//set date of book check-out
		createTransaction("Check-Out",book, member);
		entityManager.persist(issuedBk);
		String dueDate = issuedBk.getDueDate();
		transaction.commit();
		
		return dueDate;
		
	}
	/**
	 * 
	 * @param book
	 * @return
	 */
	public static int returnBook(Book book,Member member) {
		int rows=0;
		transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();//Begin transaction
			book.setStatus("Avaliable");// update book's status
			member.getIssuedBooks().remove(book); //remove book from the list of issued books
			entityManager.merge(book);
			entityManager.merge(member);
			rows=entityManager.createNamedQuery("IssueBook.returnBook").setParameter("date", getDate()).setParameter("book", book).executeUpdate();
			createTransaction("Check-in",book, member);
			transaction.commit();
			return rows;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return rows;
		
	}
	/**
	 * 
	 * @param book
	 * @returns true after adding 7 days to the remaining days to due date
	 */
	public static boolean renewBook(Book book,Member member) {
		transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			IssueBook issuedBook = entityManager.createNamedQuery("IssueBook.findBook", IssueBook.class).setParameter("book", book).getSingleResult();
			//Add more 7 days to the remaining days to due date
			issuedBook.setDueDate(computeDueDate(7+getDaysRemaining(book)));
			entityManager.merge(issuedBook);
			createTransaction("Renewal",book, member);
			transaction.commit();
			return true;
		}catch(NoResultException e) {
			e.printStackTrace();
		}
		return false;
	}
	private static String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateT = dtf.format(now);
		return dateT;
	}
	/**
	 * 
	 * @param book
	 * @returns the number of remaining days to book date over due
	 */
	public static int getDaysRemaining(Book book) {
		String query = "SELECT i.dueDate FROM IssueBook i WHERE i.book=:book AND i.dateOfReturn= null";
		int days =0;
		
		 // Get the current date
		LocalDateTime now = LocalDateTime.now();
		String dueDate = null;
		//Get book Due date from database
		try {
			dueDate = (String) entityManager.createQuery(query).setParameter("book", book).getSingleResult();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  // Parse the date string into a LocalDate object
	           LocalDate date = LocalDate.parse(dueDate, formatter);
	        // Define two dates
	           LocalDate startDate = LocalDate.of(date.getYear(),date.getMonth(),date.getDayOfMonth());
	           LocalDate endDate = LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth());

	           // Calculate the period between the two dates
	           Period period = Period.between(endDate, startDate);
	           days = period.getDays();
		}catch(NoResultException |DateTimeParseException e) {
			e.printStackTrace();
		}
		return days;
	}
	
	
	
	
	
	

}
