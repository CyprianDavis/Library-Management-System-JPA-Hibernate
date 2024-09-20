package ui.returnBook;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import database.catalog.Catalog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ReturnBookController implements Initializable{
	@FXML
	private JFXButton returnBtn;
	@FXML
	private JFXTextField book;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	private void returnBook() {
		if(book.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Return Book!", "Please Enter Book Number ");
			return;
		}
		if(!Catalog.bookExists(book.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Issue Book!", "UNKONWN BOOK ID ");
			return;
		}
		if(!Catalog.isBookCheckedout(book.getText())) {
			showAlert(Alert.AlertType.WARNING, ((Stage) book.getScene().getWindow()), "Issue Book!", "Book is not checked out ");
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


