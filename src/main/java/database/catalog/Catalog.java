package database.catalog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import data.model.book.Book;
import database.conn.DatabaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * 
 * @author CYPRIAN DAVIS
 *
 */

public class Catalog {
	//Returns database connection and stores it in conn
	static Connection conn = DatabaseConn.getConnection();
/***
 * 
 * @returns the last saved id for the book that was created when a new book was added into the system
 */
	public static int getLastBookID() {
		String query = "SELECT * FROM bookId ORDER BY id DESC OFFSET 0 ROW FETCH FIRST 1 ROW ONLY";
		int id =0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			//Checks if the table is empty
			if(rs.next() == false) {
				id =99;
			}
			else {
				 id =rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		id++; //increment id by 1
		return id;
	}
	/**
	 * Saves the last id in the database
	 * @param id
	 */
	public static void saveLastId( int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO bookId (id) VALUES(?)");
			stmt.setInt(1, id);
			//execute
			stmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	//Catalog operations
	/**
	 * Inserts a new book in the Library Catalog
	 * @param book
	 * @return
	 */
	public static boolean insertBook(Book book) {
		boolean value = true;
				
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Catalog (bookId,Title,Author,CoAuthor,category,ISBN,publicationYear,publisher,Edition,language,description,DateOfEntry,Status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, book.getBookId());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getCoAuthor());
			stmt.setString(5, book.getCategory());
			stmt.setString(6, book.getISBN());
			stmt.setInt(7, book.getPublicationYear());
			stmt.setString(8, book.getPublisher());
			stmt.setString(9, book.getEdition());
			stmt.setString(10, book.getLanguage());
			stmt.setString(11, book.getDescription());
			stmt.setString(12, book.getEntryDate());
			stmt.setString(13, book.getStatus());
			
			stmt.execute(); // execute statement
		}
		catch(SQLException e) {
			e.printStackTrace();
			return value = false;
		}
		return value;
	}
	/** 
	 * 
	 * @param isbn
	 * @returns return if the ISBN number exists
	 */
	public static boolean checkISBN(String isbn) {
		try {
			 PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Catalog WHERE ISBN=?");
	            stmt.setString(1, isbn);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                return (count > 0);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return false;
		}
	
	/**
	 * Search and returns the book from catalog if it exists 
	 * @param bookId
	 * @returns Book or null
	 */
	public static Book searchBook(String bookId) {
		Book book = null;
		//return null if book id doesn't exits
		if(!searhBook(bookId)) {
			return null;
		}
		else {
			try {
				PreparedStatement stmt = conn.prepareStatement("SELECT FROM Catalog (bookId,Title,Author,CoAuthor,category,DateOfEntry) WHERE bookId=?");
				stmt.setString(1, bookId);
				//Results set
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					book = new Book(rs.getString(2),rs.getString(3),rs.getString(1));
					book.setCategory(rs.getString(5));
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return book;
	}
	/**
	 * 
	 * @param id or ISBN
	 * @returns true if the book  exists in the database or false otherwise
	 */
	public static boolean searhBook(String id) {

        try {
            String checkstmt = "SELECT COUNT(*) FROM Catalog WHERE bookId=? OR ISBN=?";
            PreparedStatement stmt = conn.prepareStatement(checkstmt);
            stmt.setString(1, id);
            stmt.setString(2, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count > 0);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
	}
	/**
	 * Removes book from the database catalog
	 * @param bookId
	 * @returns true if the book is removable
	 */
	public static boolean removeBook(String bookId) {
		boolean condition = true;
		
			try {
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM Catalog WHERE bookId=? OR ISBN =?");
				stmt.setString(1, bookId);
				stmt.setString(2, bookId);
				//Execute statement
				stmt.execute();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return condition;
		}
	/**
	 * 
	 * @param bookId
	 * @returns true if the book is checked out or false otherwise
	 */
	public static boolean isBookCheckout(String bookId) {
		try {
            String checkstmt = "SELECT COUNT(*) FROM IssuedBooks WHERE book=? AND DateOfReturn=null";
            PreparedStatement stmt = conn.prepareStatement(checkstmt);
            stmt.setString(1, bookId);
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
	 * @returns list of the books from the database
	 */
	public static ObservableList<Book> getCatalogBooks(){
		ObservableList<Book> catalog = FXCollections.observableArrayList(); //List of books from the database
		catalog.clear();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Catalog");
			//ResultSet
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Book book = new Book(rs.getString(2),rs.getString(3),rs.getString(1),rs.getString(12));
				book.setCoAuthor(rs.getString(4));
				book.setISNB(rs.getString(6));
				book.setPublisher(rs.getString(8));
				book.setEdition(rs.getString(9));
				
				//Add book to the catalog list
				catalog.add(book);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return catalog;
	}
	/**
	 * 
	 * @param search 
	 * @returns books from data using text search 
	 */
	public static ObservableList<Book> getBooks(String search){
		ObservableList<Book> catalog = FXCollections.observableArrayList(); //List of books from the database
		catalog.clear();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Catalog WHERE Title LIKE ? OR Author LIKE ?");
			stmt.setString(1, "%" +search+ "%");
			stmt.setString(2,  "%"+search+"%");
			//ResultSets
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Book book = new Book(rs.getString(2),rs.getString(3),rs.getString(1),rs.getString(12));
				book.setCoAuthor(rs.getString(4));
				book.setCategory(rs.getString(5));
				book.setISNB(rs.getString(6));
				book.setPublicationYear(rs.getInt(7));
				book.setPublisher(rs.getString(8));
				book.setEdition(rs.getString(9));
				book.setLanguage(rs.getString(10));
				book.setEntryDate(rs.getString(11));
				book.setDescription(rs.getString(13));
				
				//Add book to the catalog list
				catalog.add(book);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return catalog;
	}
	/**
	 * 
	 * @param searchs book based on book id or ISNB
	 * @returns a Book 
	 */
	public static Book getBook(String search) {
		Book book = new Book();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Catalog WHERE bookId LIKE ? OR ISBN LIKE ?");
			stmt.setString(1, "%" +search+ "%");
			stmt.setString(2,  "%"+search+"%");
			//ResultSets
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				book.setBookId(rs.getString(1));
				book.setTitle(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setCoAuthor(rs.getString(4));
				book.setCategory(rs.getString(5));
				book.setISNB(rs.getString(6));
				book.setPublicationYear(rs.getInt(7));
				book.setPublisher(rs.getString(8));
				book.setEdition(rs.getString(9));
				book.setLanguage(rs.getString(10));
				book.setEntryDate(rs.getString(11));
				book.setStatus(rs.getString(12));
				book.setDescription(rs.getString(13));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	/**
	 * 
	 * @returns the total number of books the library owns
	 */
	public static int totalNumberOfBooks() {
		int count=0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Catalog");
			ResultSet rs = stmt.executeQuery();
			
            if (rs.next()) {
                 count = rs.getInt(1);
            }
		}catch(SQLException e) {
			e.printStackTrace();
	}
		return count;
		
	}
	/**
	 * 
	 * @param status
	 * @returns number of books based on their status
	 */
	public static int getBooksByStatus(String status) {
		int count =0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Catalog WHERE status=?");
			stmt.setString(1, status);
			ResultSet rs = stmt.executeQuery();
			
            if (rs.next()) {
                 count = rs.getInt(1);
            }
		}catch(SQLException e) {
			e.printStackTrace();
	}
		return count;
		
	}
}
