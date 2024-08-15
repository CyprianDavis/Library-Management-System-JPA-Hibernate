package data.model.member;

import data.model.book.Book;

/**
 * Issue Book Interface
 * @author CYPRIAN DAVIS
 *
 */

public interface IssueBook {
	/**
	 * Handles Issuing book to a memeber operations
	 * @param book
	 * @return
	 */
	  public boolean BorrowBook(Book book);
	  /**
	   * Handles return book operations
	   * @param book
	   * @return
	   */
	  public boolean returnBook(Book book);
	  /**
	   * Handles returning book operations
	   * @param book
	   * @return
	   */
	  public boolean renewBook(Book book);

}
