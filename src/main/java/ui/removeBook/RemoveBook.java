package ui.removeBook;

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

public class RemoveBook implements Initializable {
	
	@FXML
	private JFXButton  deleteBtn;
	@FXML
	private JFXTextField book;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}
	@FXML
	private void deleteBook() {
		if(book.getText().isEmpty()) {
			 showAlert(Alert.AlertType.ERROR, ((Stage) book.getScene().getWindow()), "Remove Book", "Please enter Book ID");
			 return;
		}
		if(!Catalog.bookExists(book.getText())) {
			 showAlert(Alert.AlertType.ERROR, ((Stage) book.getScene().getWindow()), "Remove Book", "Unkonwn  Book ID");
			 return;
		}
		Catalog.removeBook(book.getText());
		showAlert(Alert.AlertType.INFORMATION,((Stage) book.getScene().getWindow()),"Information","Operation successful");
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
