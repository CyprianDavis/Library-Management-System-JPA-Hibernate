package ui.issueBooks;
/**
 * 
 * @author Cyprian Davis
 *
 */
public class IssueBookDetails {
	private String bookNum;
	private String title;
	private String author;
	private String category;
	private String issuedOn;
	private String dueDate;
	
	public IssueBookDetails(String bookNum, String title, String author, String category, String issuedOn,
			String dueDate) {
		this.bookNum = bookNum;
		this.title = title;
		this.author = author;
		this.category = category;
		this.issuedOn = issuedOn;
		this.dueDate = dueDate;
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIssuedOn() {
		return issuedOn;
	}
	public void setIssuedOn(String issuedOn) {
		this.issuedOn = issuedOn;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	

}
