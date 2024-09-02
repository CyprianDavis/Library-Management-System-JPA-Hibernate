package data.model.member;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import data.model.book.Book;
import data.model.library.Hold;
import data.model.library.Transaction;
import database.memberOperations.MembersOperations;
import javafx.scene.image.Image;
/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name="Member.members" ,query="SELECT m FROM Member m"),
		@NamedQuery(name="Member.searchById",query="SELECT m FROM Member m WHERE m.memberId LIKE:idNum"),
		@NamedQuery(name="Member.numberOfMembers",query="SELECT COUNT(m) FROM Member m "),
}
		)

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
	private String gender;	
	  @Convert(converter = ImageConverter.class)
	private Image picture;		//Member image
	//Member's Gender
	@OneToMany(targetEntity=Hold.class,mappedBy="member")
	private Collection<Hold>booksOnHold = new LinkedList<>();
	@OneToMany(targetEntity=Transaction.class,mappedBy="member")
	private Collection<Transaction>transactions = new LinkedList<>();
	@OneToMany
	@JoinTable(name="IssuedBooks",
			joinColumns= @JoinColumn(name="member"))
	private Collection<Book>issuedBooks;
	
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
	public Member() {
		
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
	public void setImage(Image img) {
		
		this.picture =img ;
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
	public Image getImage() {
		return picture;
		
	}
	public Collection<Transaction> getTransactions(){
		return this.transactions;
	}
	public Collection<Hold> getHolds(){
		return this.booksOnHold;
	}
	public Collection<Book> getIssuedBooks(){
		return this.issuedBooks;
	}
	
	
	

}
