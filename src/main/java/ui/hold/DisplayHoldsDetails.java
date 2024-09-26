package ui.hold;
/**
 * 
 * @author CYPRIAN DAVIS
 *
 */

public class DisplayHoldsDetails {
	private String holdId;
	private String member;
	private String book;
	private String reservationDate;
	private String status;
	private String comment;
	/**
	 * Constructor
	 * @param holdId
	 * @param member
	 * @param book
	 * @param reservationDate
	 * @param status
	 * @param comment
	 */
	public DisplayHoldsDetails(String holdId, String member, String book, String reservationDate, String status,
			String comment) {
		super();
		this.holdId = holdId;
		this.member = member;
		this.book = book;
		this.reservationDate = reservationDate;
		this.status = status;
		this.comment = comment;
	}
	public String getHoldId() {
		return holdId;
	}
	public void setHoldId(String holdId) {
		this.holdId = holdId;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

}
