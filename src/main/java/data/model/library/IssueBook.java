package data.model.library;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class IssueBook {
	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(name="member")
	private Member member;
	@ManyToOne
	@JoinColumn(name="book")
	private Book book;
	private String dateOfIssuing;		//date on which book is issued
	private Calendar dueDate;		//due date for returning the book	
	private String dateOfReturn;
		
	
	public IssueBook(Member member,Book book) {
		this.member = member;
		this.book = book;
	}
	public IssueBook() {
		
	}
	
	public void setDateOfIssuing(String issuedOn) {
		this.dateOfIssuing =issuedOn;
	}
	/**
	 * computes the due date for the book
	 * @param duration
	 * @return
	 */
	private Calendar computeDueDate(int duration) {
		Calendar date = new GregorianCalendar();
		date.setTimeInMillis(System.currentTimeMillis());
		date.add(Calendar.DATE, duration);
		return date;
	}
	/**
	 * 
	 * @returns the due date for the book which is 2 weeks from date of issuing the book
	 */
	@SuppressWarnings("deprecation")
	public String getDueDate() {
		this.dueDate = computeDueDate(14);
		return dueDate.getTime().toLocaleString();
		
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
