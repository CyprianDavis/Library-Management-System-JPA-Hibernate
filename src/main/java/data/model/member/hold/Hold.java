package data.model.member.hold;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Hold {
	private static int auto_id; 	//auto id for the hold
	@Id
	private String holdId;			//contains the id for the hold 
	private Member member;		//memeber who is placing the hold
	private Book book;			//Book on with hold
	private Calendar holdDate;		//when the member wants the book
	private String date; //date in string
	private String status; //Hold Status
	
	
	public Hold(Member member, Book bk,int duration) {
		this.member = member;
		this.book = bk;
		holdDate = new GregorianCalendar();
		holdDate.setTimeInMillis(System.currentTimeMillis());
		holdDate.add(Calendar.DATE, duration);
		date = holdDate.toString();
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
	/**
	 * 
	 * @param id
	 * @param member
	 * @param book
	 */
	public Hold(String id,Member member, Book book) {
		this.member = member;
		this.book = book;
		this.holdId = id;
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
		return (System.currentTimeMillis() < holdDate.getTimeInMillis());
	}
	public String getHoldId() {
		return holdId;
		
	}
	
		}
