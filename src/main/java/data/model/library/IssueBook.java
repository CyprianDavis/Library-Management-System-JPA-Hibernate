package data.model.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import data.model.book.Book;
import data.model.member.Member;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
@Entity
@Table(name="IssuedBooks")

  @NamedQueries({
  
  @NamedQuery(name="IssueBook.returnBook", query="UPDATE IssueBook i SET i.dateOfReturn= :date WHERE i.book= :book"),
  @NamedQuery(name="IssueBook.renewBook",query="UPDATE IssueBook i SET i.dueDate=:date WHERE i.book=:book"),
  @NamedQuery(name="IssueBook.findBook",query="SELECT i FROM IssueBook i WHERE i.book=:book AND i.dateOfReturn= null")
  
  
  } )
 
public class IssueBook {
	@Id 
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="member")
	private Member member;
	@ManyToOne
	@JoinColumn(name="book")
	private Book book;
	private String dateOfIssuing;		//date on which book is issued
	private String dueDate;		//due date for returning the book	
	private String dateOfReturn;
		
	
	public IssueBook(Member member,Book book) {
		this.member = member;
		this.book = book;
		
	}
	public IssueBook() {
		
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
		
	}
	
	public void setDateOfIssuing(String issuedOn) {
		this.dateOfIssuing =issuedOn;
	}
	
	/**
	 * 
	 * @returns the due date for the book which is 2 weeks from date of issuing the book
	 */
	public String getDueDate() {
		
		return dueDate;
		
	}
	public String getDateOfIssuing() {
		return this.dateOfIssuing;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	public Member getMember() {
		return member;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	public Book getBook() {
		return book;
	}
	
	public void setDateOfReturn(String dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	public String getDateOfReturn() {
		return dateOfReturn;
	}

}
