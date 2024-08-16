package data.model.member.transaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import data.model.book.Book;
import data.model.member.Member;

@Entity
@Table(name="IssuedBooks")
public class IssueBook {
	private Book issuedBk;
	private Member member;
	private Calendar due_Date;		//due date for returning the book
	private String dueDate;
	private String DateOfReturn;
	
	public IssueBook(Book bk,Member member) {
		this.issuedBk = bk;
		this.member = member;
	}
	public IssueBook() {
		
	}
	public void setBook(Book book) {
		this.issuedBk =book;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public void setDueDate(String date) {
		this.dueDate =date;
	}
	public void setDateOfReturn(String DateOfReturn) {
		this.DateOfReturn = DateOfReturn;
	}
	public Book getBook() {
		return issuedBk;
	}
	public Member getMember() {
		return member;	
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
		
		return this.due_Date=date;
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

}
