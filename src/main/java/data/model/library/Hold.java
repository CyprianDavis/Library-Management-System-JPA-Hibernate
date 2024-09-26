package data.model.library;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import data.model.book.Book;
import data.model.member.Member;
import database.holdProcesses.HoldProcesses;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
@Entity
@Table(name="Holds")
@NamedQueries(
		{
			@NamedQuery(name="Hold.getHolds",query="SELECT h FROM Hold h WHERE h.book=:book AND h.status='On'"),
			@NamedQuery(name="Hold.bookHasHold",query="SELECT h.book FROM Hold h WHERE h.book=:book AND h.status= 'On'"),
			@NamedQuery(name="Hold.removeHold",query="UPDATE Hold h SET h.status='Removed' WHERE h.member=:member AND h.book=:book "),
			@NamedQuery(name="Hold.holdExist",query="SELECT h FROM Hold h WHERE h.member= :member AND h.book= :book"),
			@NamedQuery(name="Hold.getHoldDetails",query="SELECT new ui.hold.DisplayHoldsDetails(h.holdId,CONCAT(h.member.surName,' ',h.member.givenName,' ',h.member.otherName),h.book.title,h.date,h.status,h.comment)"
					+ "FROM Hold")
		})


public class Hold {
	private static  LocalDate today = LocalDate.now();
	private static int auto_id; 	//auto id for the hold
	@Id
	private String holdId;			//contains the id for the hold 
	@ManyToOne
	@JoinColumn(name="member")
	private Member member;		//memeber who is placing the hold
	@ManyToOne
	@JoinColumn(name="book")
	private Book book;			//Book on with hold
	@Column(name="ReservationDate")
	private String date; //date in string
	private String status; //Hold Status
	private String comment;
	
	
	public Hold(Member member, Book bk,int duration) {
		 
		this.member = member;
		this.book = bk;
		
		
		date = computeHoldDueDate(duration);
		//Auto id generation
		int year = Year.now().getValue();
		auto_id = HoldProcesses.getNextTableGeneratorValue();
		if(auto_id<=9) {
			String id = "HBK00000"+auto_id+""+year;
			this.holdId = id;	
		}
		else if(auto_id>=10) {
			String id = "HBK0000"+auto_id+""+year;
			this.holdId = id;	
		}
		else if(auto_id >99) {
			String id = "HBOO0"+auto_id+""+year;
			this.holdId = id;
		}
		else if(auto_id >999) {
			String id ="HB00"+auto_id+""+year;
			this.holdId = id;
		}
		
	}
	public Hold() {
		
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public 	Member getMember() {
		return member;
	}
	public Book getBook() {
		return book;
	}

	public String getDate() {
		return date;
	}
	public String getStatus() {
		isValid();//check if it's still vaild
		return this.status;
		
	}
	public void isValid() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(this.date, formatter);
     // Update hold status
        if(LocalDate.now().isBefore(date)) {
	        	 this.status =  "Valid";
	         }
        else {
        	this.status ="Invalid";
        }
	}
	        	 
        	  
	public String getHoldId() {
		return holdId;
		
	}
	/**
	 * computes the due date for the book
	 * @param duration
	 * @return
	 */
	private static String computeHoldDueDate(int duration) {
		 // Get the current date
      
        
        // Add 14 days to the current date
        LocalDate futureDate = today.plusDays(duration);
        
        // Define a formatter for the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // You can change the pattern to your preferred format
        
        // Format the date as a string
        String futureDateString = futureDate.format(formatter);
		
		return futureDateString;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
		}
