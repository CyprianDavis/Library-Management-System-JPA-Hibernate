package ui.renewBook;

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

public class RenewBookController implements Initializable {
	@FXML
	private JFXTextField memberId;
	@FXML
	private JFXTextField bookId;
	@FXML
	private JFXButton renewBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	private void renewBook() {
		Member member=null;
		Book book = null;
		
		if(memberId.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Renew Book", "Please Enter Member ID Number ");
			return;
		}
		if(!MembersOperations.memberExists(memberId.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Renew Book", "INVALID Member ID Number ");
			return;
		}
		if(bookId.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Return Book", "Please Enter Book Number ");
			return;
		}
		if(!Catalog.bookExists(bookId.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Renew Book", "UNKONWN BOOK ID ");
			return;
		}
		if(!Catalog.isBookCheckedout(bookId.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Renew Book", "Book is not checked out ");
			return;
		}
		//Extract book entity
		book = Catalog.findBook(bookId.getText());
		//Extract Member 
		member = MembersOperations.findMember(memberId.getText());
		//check if book has hold
		if(HoldProcesses.bookHasHold(book)) {
			showAlert(Alert.AlertType.WARNING, ((Stage) bookId.getScene().getWindow()), "Renew Book", "Book is not renewable it has Hold ");
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

