package ui.returnBook;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import data.model.book.Book;
import data.model.member.Member;
import database.catalog.Catalog;
import database.library.LibraryOperations;
import database.memberOperations.MembersOperations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ReturnBookController implements Initializable{
	
	@FXML
	private JFXButton returnBtn;
	@FXML
	private JFXTextField memberId;
	@FXML
	private JFXTextField book;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	private void returnBook() {
		if(memberId.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Return Book", "Please Enter Member ID Number ");
			return;
		}
		if(!MembersOperations.memberExists(memberId.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Return Book", "INVALID Member ID ");
			return;
		}
		if(book.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Return Book", "Please Enter Book Number ");
			return;
		}
		if(!Catalog.bookExists(book.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Retrun Book", "UNKONWN BOOK ID ");
			return;
		}
		if(!Catalog.isBookCheckedout(book.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Return Book", "Book is not checked out ");
			return;
		}
		//Extract member entity from database
		Member member = MembersOperations.findMember(memberId.getText());
		//Extract Book entity from database
		Book bk = Catalog.findBook(book.getText());
		
		//return book 
		if(LibraryOperations.returnBook(bk, member) >0) {
			showAlert(Alert.AlertType.INFORMATION, ((Stage) book.getScene().getWindow()), "Return Book", "Operation Successfull ");
			return;
		}
		else{
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Retrun Book", "Operation Failed ");
			return;
			
		}
			
		
		
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


