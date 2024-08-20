package data.model.book;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import data.model.member.transaction.Transaction;
import database.catalog.Catalog;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
@Entity
@Table(name="Catalog")
@NamedQueries({
	@NamedQuery(name ="Book.Catalog",query="SELECT b FROM Book b"),
	@NamedQuery(name="Book.checkISBN",query="SELECT b FROM Book b WHERE b.ISBN=:isbn"),
	@NamedQuery(name="Book.searchBook",query="SELECT b FROM Book b WHERE b.bookId=:search OR b.ISBN=:search OR b.title=:search"),
	@NamedQuery(name="Book.isCheckedOut",query="SELECT b FROM Book b WHERE b.bookId=:id AND b.status= 'issued' "),
	@NamedQuery(name="Book.numberOfBooks",query="SELECT COUNT(b) FROM Book b WHERE b.status=:status"),
	
}
		
		)

public class Book {
	private static int auto_bkId;	//Auto book id  number
	private String title; 		//Books title
	private String author; 		//Author for the book
	private String coAuthor; 	//co author of the book
	@Id
	private String bookId;  	//unique identify for the book
	private String ISBN;		//International standard Book Number(it used as a unique numerical identifier assigned to each book edition)
	private int publicationYear; //year i n which the book was published
	private String publisher;	//publisher of the book
	private String edition; 	//book edition
	private String language;
	private String status; //status of the book in the library eg available or issued
	private String dateOfEntry;	// date when  the book was entered in the system
	private String  category;		//category of the book
	private String description;		// Book Description
	@OneToMany(targetEntity=Transaction.class,mappedBy="book")
	private Collection<Transaction>transactions =new LinkedList<>();

	//Constructors
	/**
	 * 
	 * @param title
	 * @param author
	 */
	public Book (String title, String author,String status) {
		this.author = author;
		this.title = title;
		this.status = status;
		
		
	int year = Year.now().getValue();
	auto_bkId = Catalog.getNextTableGeneratorValue();
	if(auto_bkId<=999) {
		String id = "BK000"+auto_bkId+""+year;
		this.bookId = id;	
	}
	else if(auto_bkId >= 1000) {
		String id = "BKOO"+auto_bkId+""+year;
		this.bookId = id;
	}
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	String dateT = dtf.format(now);
	this.dateOfEntry =dateT;
	
	}
	/**
	 * Constructors
	 * @param title
	 * @param author
	 * @param id
	 */
	public Book (String title, String author,String id,String status){
		this.bookId = id;
		this.title = title;
		this.author = author;
		this.status = status;
	}
	/**
	 * constructor that takes no value
	 */
	public Book () {
		
	}
	
	//Setter methods
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setCoAuthor(String co_author) {
		this.coAuthor =co_author;
	}
	public void setISNB(String isnb) {
		this.ISBN = isnb;
	}
	public void setPublicationYear(int year) {
		this.publicationYear = year;
	}
	public void setPublisher(String pub) {
		this.publisher = pub;
	}
	
	public void setBookId(String id) {
		this.bookId = id;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public void setEntryDate(String date) {
		this.dateOfEntry = date;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	public void setLanguage(String lag) {
		this.language = lag;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//Getter methods
	public String getBookId() {
		return bookId;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getCoAuthor() {
		return coAuthor;
	}
	public String getEntryDate() {
		return this.dateOfEntry;
	}
	public String getCategory() {
		return category;
	}
	public String getISBN() {
		return ISBN;
	}
	public String getEdition() {
		return edition;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getLanguage() {
		return language;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public String getStatus() {
		return status;
	}
	public String getDescription() {
		return description;
	}
	public Collection<Transaction> getTransactions(){
		return transactions;
		
	}
	
	
	public String toString() {
		return "Title "+this.title+"\n"
				+ "Author "+this.author+"\n"
						+ "ISBN "+this.ISBN;
		
	}

}
