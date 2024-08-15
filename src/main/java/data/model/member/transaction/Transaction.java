package data.model.member.transaction;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import database.transactions.TransactionsOperation;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */
public class Transaction {
	private static int auto_id;	//Auto id retrived from the database
	private String transID; //Transation id number
	private String date; 	//date of the transaction
	private	String book;	//Book title
	private String type; 		//Transaction type
	private String member ;
	
	//Constructor
	public Transaction(String type,String bk,String member) {
		this.type = type;
		this.book = bk;
		this.member = member;
		//Get the system date and Time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateT = dtf.format(now);
		this.date = dateT;
		int year = Year.now().getValue();
		//Retive last id from the databse
		auto_id = TransactionsOperation.getLastTransactionId();
		if(auto_id<=9) {
			String id = "TR0000"+auto_id+""+year;
			this.transID = id;		
		}
		else if(auto_id>=10) {
			String id = "TR000"+auto_id+""+year;
			this.transID = id;	
		}
		else if(auto_id >99) {
			String id = "TR00"+auto_id+""+year;
			this.transID = id;
		}
		else if(auto_id >999) {
			String id ="TR0"+auto_id+""+year;
			this.transID = id;
		}
		else {
			String id = "TR"+auto_id+""+year;
			this.transID = id;
		}
	}
	/**
	 * 
	 * @param trans
	 * @param id
	 * @param book
	 */
	public Transaction(String id,String trans, String member,String book) {
		this.transID = id;
		this.book = book ;
		this.transID = trans;
		this.member = member;
		
	}
	//Setters
	public void setTransationId(String id) {
		this.transID = id;
	}
	
	public void setBookTitle(String title) {
		this.book = title;
	}
	public void setType(String transation) {
		this.type = transation;
	}
	public void setMember(String member) {
		this.member = member;
	}
	//Getters
	public String getTransationId() {
		return this.transID;
	}
	public String getDate() {
		return date;	
	}
	public String getBook() {
		return book;
	}
	public String getTransactionType() {
		return type;
	}
	public String getMember() {
		return member;
		
	}

}
