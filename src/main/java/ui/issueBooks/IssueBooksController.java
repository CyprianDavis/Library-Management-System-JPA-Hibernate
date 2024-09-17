package ui.issueBooks;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import data.model.book.Book;
import data.model.member.Member;
import database.catalog.Catalog;
import database.library.LibraryOperations;
import database.memberOperations.MembersOperations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class IssueBooksController implements Initializable {
	@FXML
	private JFXTextField bookId;
	@FXML
	private TableView<Book>booksIssued;
	@FXML
	private TableColumn<Book,String>bkNumber; 
	@FXML
	private TableColumn<Book,String>title;
	@FXML
	private TableColumn<Book,String>author;
	@FXML
	private TableColumn<Book,String>category;
	@FXML
	private TableColumn<Book,String>date;
	@FXML
	private TableColumn<Book,String>dueDate;
	private static  Member member;
	private Book book =null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colsIntialize();
		loadTable();
		
	}
	/**
	 * Initializes table columns
	 */
	private void colsIntialize(){
		bkNumber.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		author.setCellValueFactory(new PropertyValueFactory<>("author"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		
	}
	public static void  setMember(String  memberId) {
		member = MembersOperations.findMember(memberId);
	}
	/**
	 * Issues book to a member
	 */
	@FXML
	private void issueBook() {
		if(bookId.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, ((Stage) bookId.getScene().getWindow()), "Issue Book!", "Please Enter Book Number ");
			return;
		}
		if(!Catalog.bookExists(bookId.getText())) {
			showAlert(Alert.AlertType.ERROR, ((Stage) bookId.getScene().getWindow()), "Issue Book!", "UNKOWN Book Number ");
			return;	
		}
		if(Catalog.isBookCheckedout(bookId.getText())) {
			showAlert(Alert.AlertType.ERROR, ((Stage) bookId.getScene().getWindow()), "Issue Book!", "Book is already Checked-Out ");
			return;	
		}
		if(LibraryOperations.getIssuedBooks(member)>=5) {
			showAlert(Alert.AlertType.ERROR, ((Stage) bookId.getScene().getWindow()), "Issue Book!", "Exceeded Limit of Five Books ");
			return;
		}
		book = Catalog.findBook(bookId.getText());//Extract book from database
		//issue book to member and get due date
	    String dueDate =LibraryOperations.issueBook(member, book);
		showAlert(Alert.AlertType.INFORMATION,((Stage) bookId.getScene().getWindow()),"Information","Operation successful Due Date "+dueDate);
		loadTable(); //load table data
		
	}
	private void loadTable() {
		booksIssued.getItems().clear();
		booksIssued.getItems().addAll(member.getIssuedBooks());
		
	}
	//Handles Alert Messages
	private  void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
		}

}
