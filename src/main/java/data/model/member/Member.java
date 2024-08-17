package data.model.member;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import data.model.book.Book;
import data.model.member.hold.Hold;
import data.model.member.transaction.Transaction;
import database.memberOperations.MembersOperations;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
@Entity
public class Member  {
	private static int auto_id;		//To store auto id from the databse
	@Id
	@Column(name ="memberNo")
	private String memberId;	//Member identification number
	private String surName;		//surName
	private String givenName;	//Mid Name
	private String otherName;	//Last Name
	private String contact; 	//telphone contact
	private String email; 		//Email Address
	private String address;		//Address
	private String dateOfReg;	//members date of registeration
	private String gender;		//Member's Gender
	
	private LinkedList<Book>borrowedBooks;	//Stores books being borrowed by the memeber temporary
	@OneToMany
	private LinkedList<Hold>booksOnHold;	//stores the books being put on hold by the member temporary
	@OneToMany
	private LinkedList<Transaction>tranctions; //Stores member's current transtions temporary
	
	//Constructors
	public Member(String sName,String gName,String oName) {
		this.surName = sName;
		this.givenName = gName;
		this.otherName = oName;
		int year = Year.now().getValue();
		auto_id = MembersOperations.getNextTableGeneratorValue();
		if(auto_id<=9) {
			String id = "LM0000"+auto_id+""+year;
			this.memberId = id;	
			
		}

		else if(auto_id>=10) {
			String id = "LM000"+auto_id+""+year;
			this.memberId = id;	
		}
		else if(auto_id >99) {
			String id = "LMOO"+auto_id+""+year;
			this.memberId = id;
		}
		else if(auto_id >999) {
			String id ="LM0"+auto_id+""+year;
			this.memberId = id;
		}
		
		//Get the system date and Time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateT = dtf.format(now);
		this.dateOfReg =dateT;
	}
	public Member(String id,String sName,String gName,String oName) {
		this.memberId = id;
		this.surName = sName;
		this.givenName = gName;
		this.otherName = oName;
	}
	//Setters
	public void setMemberId(String id) {
		this.memberId = id;
	}
	public void setSurName(String sName) {
		this.surName = sName;
	}
	public void setGivenName(String gName) {
		this.givenName =gName;
	}
	public void setOtherName(String oName) {
		this.otherName = oName;
	}
	public void setContact(String tel) {
		this.contact = tel;
	}
	public void setEmail(String mail) {
		this.email = mail;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setDateOfReg(String date) {
		this.dateOfReg = date;
	}
	public void setGender(String sex) {
		this.gender = sex;
	}
	//Getters
	public String getMemberId() {
		return memberId;
	}
	public String getSurName() {
		return surName;
	}
	public String getGivenName() {
		return givenName;
	}
	public String getOtherName() {
		return otherName;
	}
	public String getEmail() {
		return email;
	}
	public String getContact() {
		return contact;
	}
	public String getAddress() {
		return address;
	}
	public String getDateOfReg() {
		return this.dateOfReg;
		
	}
	public String getGender() {
		return gender;	
	}
	/**
	 * Hold utility methods*/
	/**
	 * Places a hold on a book
	 * @param book
	 */
	public  void placeHold(Book book) {
		
	}
	public boolean removeHold(Book book) {
		return false;	
	}
	/**
	 * 
	 * @return List of books being issued to the member
	 */
	public List<Book> getIssuedBooks(){
		return borrowedBooks;
	}
	/**
	 * 
	 * @return List of books on Hold
	 */
	public List<Hold> getBooksOnHold(){
		return booksOnHold;
	}
	/**
	 * 
	 * @return List of current Transations of the member
	 */
	public List<Transaction> getTransactions(){
		return tranctions;
		
	}
	/**
	 * Returns string representation of member 
	 */
	public String toString() {
		return ""+this.surName +" "+this.givenName+" "+this.otherName;
		
	}
	
	

}
