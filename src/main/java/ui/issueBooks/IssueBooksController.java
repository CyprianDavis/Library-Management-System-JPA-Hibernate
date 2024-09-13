package ui.issueBooks;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import data.model.book.Book;
import data.model.member.Member;
import database.memberOperations.MembersOperations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private void issueBook() {
		if()
		
	}
	//Handles Alert Messages
	private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
		}

}
