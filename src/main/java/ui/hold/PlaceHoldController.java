package ui.hold;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import data.model.book.Book;
import data.model.member.Member;
import database.catalog.Catalog;
import database.holdProcesses.HoldProcesses;
import database.memberOperations.MembersOperations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PlaceHoldController  implements Initializable{
	@FXML
	private JFXTextField memberId;
	@FXML
	private JFXTextField bookId;
	@FXML
	private JFXButton placeHold;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	private void placeHold() {
		Member member=null;
		Book book = null;
		
		if(memberId.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Place Hold", "Please Enter Member ID Number ");
			return;
		}
		if(bookId.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Place Hold", "Please Enter Book ID Number ");
			return;
		}
		if(!Catalog.bookExists(bookId.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Place Hold", "UNKONWN BOOK ID ");
			return;
		}
		if(!Catalog.isBookCheckedout(bookId.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Place Hold", "Book is not checked out ");
			return;
		}
		//Extract member and Book Entities
		member = MembersOperations.findMember(memberId.getText());
		book = Catalog.findBook(bookId.getText());
		
		//check if Hold already Exists
		if(HoldProcesses.holdExists(book, member)) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Place Hold", "Hold already Exists ");
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


