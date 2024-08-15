package database.holdProcesses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.model.book.Book;
import data.model.member.Member;
import data.model.member.hold.Hold;
import database.conn.DatabaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author CYPRIAN DAVIS
 *
 */

public class HoldProcesses {
	private static Connection conn =DatabaseConn.getConnection();
	/***
	/**
	 * 
	 * @returns the last hold id saved in the database 
	 */
	public static int getHoldId() {
		String query = "SELECT * FROM HoldIds ORDER BY id DESC OFFSET 0 ROW FETCH FIRST 1 ROW ONLY";
		int id =0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			//Checks if the table is empty
			if(rs.next() == false) {
				id =1;
			}
			else {
				 id =rs.getInt(1);
				 id++; //increment id by 1
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * Saves the last hold id for future use
	 * @param id
	 */
	public static void saveHoldId(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO HoldIds (id) VALUES(?)");
			stmt.setInt(1, id);
			//execute
			stmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 
	 * @param bookId
	 * @returns true if the book has a hold or false otherwise
	 */
	public static boolean bookHasHold(String bookId) {
		try {
            PreparedStatement stmt = conn.prepareStatement( "SELECT COUNT(*) FROM Holds WHERE book=? AND status=? ");
            stmt.setString(1, bookId);
            stmt.setString(2, "");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count > 0);
            }
		}
            catch(SQLException e) {
            	e.printStackTrace();
            }
		return false;
	}
	/**
	 * 
	 * @param book
	 * @param member
	 * @returns true if the hold already exists in the database and false otherwise
	 */
	public static boolean holdExsists(Book book, Member member) {
		try {
            String checkstmt = "SELECT COUNT(*) FROM Holds WHERE book=? AND member=? status=null";
            PreparedStatement stmt = conn.prepareStatement(checkstmt);
            stmt.setString(1, book.getBookId());
            stmt.setString(2, member.getMemberId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count > 0);
            }
		}
            catch(SQLException e) {
            	e.printStackTrace();
	}
		return false;
		}
/**
 * Places hold on the book 
 * @param hold
 */
	public static void placeHold(Hold hold) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Holds (holdId,book,member,Date) VALUES(?,?,?,?)");
			stmt.setString(1, hold.getHoldId());
			stmt.setString(2, hold.getBook());
			stmt.setString(3, hold.getMember());
			stmt.setString(4, hold.getDate());
			//execute
			stmt.execute();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Removes hold from a book placed on by given member
	 * @param member
	 * @param book
	 * @param status
	 * @returns true if the hold is removed successfully or false otherwise
	 */
	public static boolean removeHold(Member member, Book book,String status) {
		if(!holdExsists(book,member)) {
			return false;
		}
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE Holds SET status= ? WHERE member =? AND book=?");
			stmt.setString(1, status);
			stmt.setString(2,member.getMemberId());
			stmt.setString(3, book.getBookId());
			//Execute statement
			stmt.execute();
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 
	 * @param book
	 * @returns the list of holds of the book from the database
	 */
	public static ObservableList<Hold> getHolds(Book book){
		ObservableList<Hold> holds = FXCollections.observableArrayList(); //List of Holds on a book from the database
		holds.clear();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM holdsView WHERE title=? AND Status =null");
			stmt.setString(1, book.getTitle());
			
			//Results Set
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Hold hold = new Hold(rs.getString(1),rs.getString(2),rs.getString(3));
				hold.setDate(rs.getString(4));
				holds.add(hold);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return holds;
}
	}