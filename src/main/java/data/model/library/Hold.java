package data.model.library;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
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
			@NamedQuery(name="Hold.getHolds",query="SELECT h.member FROM Hold h WHERE h.book= :book AND h.status=On "),
			@NamedQuery(name="Hold.bookHasHold",query="SELECT h.b FROM H h WHERE h.b= :book AND h.status=On"),
			@NamedQuery(name="Hold.removeHold",query="UPDATE Hold h SET h.status=Removed WHERE h.member=:member AND h.book=:book "),
			@NamedQuery(name="Hold.holdExist",query="SELECT h FROM Hold h WHERE h.member= :member AND h.book= :book")
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
	
	private String date; //date in string
	private String status; //Hold Status
	
	
	public Hold(Member member, Book bk,int duration) {
		 
		this.member = member;
		this.book = bk;
		
		date = computeHoldDueDate(duration);
		//Auto id generation
		int year = Year.now().getValue();
		auto_id = HoldProcesses.getNextTableGeneratorValue();
		if(auto_id<=9) {
			String id = "HBK0000"+auto_id+""+year;
			this.holdId = id;	
		}
		else if(auto_id>=10) {
			String id = "HBK000"+auto_id+""+year;
			this.holdId = id;	
		}
		else if(auto_id >99) {
			String id = "HBOO"+auto_id+""+year;
			this.holdId = id;
		}
		else if(auto_id >999) {
			String id ="HB0"+auto_id+""+year;
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
		return this.status;
		
	}
	public boolean isValid() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		  // Parse the date string into a LocalDate object
         LocalDate date = LocalDate.parse(this.date, formatter);
		return (today.isAfter(date));
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
	
		}
