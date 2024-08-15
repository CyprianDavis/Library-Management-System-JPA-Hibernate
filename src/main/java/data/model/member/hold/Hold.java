package data.model.member.hold;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import database.holdProcesses.HoldProcesses;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */

public class Hold {
	private static int auto_id; 	//auto id for the hold
	private String holdId;			//contains the id for the hold 
	private String member;		//memeber who is placing the hold
	private String book;			//Book on with hold
	private Calendar holdDate;		//when the member wants the book
	private String date; //date in string
	
	
	public Hold(String member, String bk,int duration) {
		this.member = member;
		this.book = bk;
		holdDate = new GregorianCalendar();
		holdDate.setTimeInMillis(System.currentTimeMillis());
		holdDate.add(Calendar.DATE, duration);
		date = holdDate.toString();
		//Auto id generation
		int year = Year.now().getValue();
		auto_id = HoldProcesses.getHoldId(); //Extracts last id and assigns it to auto_id

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
		//Save id in the database for future using
		HoldProcesses.saveHoldId(auto_id);
	}
	/**
	 * 
	 * @param id
	 * @param member
	 * @param book
	 */
	public Hold(String id,String member, String book) {
		this.member = member;
		this.book = book;
		this.holdId = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public 	String getMember() {
		return member;
	}
	public String getBook() {
		return book;
	}

	public String getDate() {
		return date;
	
	}
	public boolean isValid() {
		return (System.currentTimeMillis() < holdDate.getTimeInMillis());
	}
	public String getHoldId() {
		return holdId;
		
	}
	
		}
