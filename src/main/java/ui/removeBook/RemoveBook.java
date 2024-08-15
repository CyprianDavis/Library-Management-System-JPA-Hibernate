package ui.removeBook;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import database.catalog.Catalog;
import database.holdProcesses.HoldProcesses;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
			showAlert(Alert.AlertType.WARNING,((Stage) book.getScene().getWindow()),"Remove Book","Enter Book ID or ISBN");
			return;
		}
		if(!Catalog.searhBook(book.getText())) {
			showAlert(Alert.AlertType.WARNING,((Stage) book.getScene().getWindow()),"Remove Book","Unkown Book Id or ISBN");
			return;
		}
		if(Catalog.isBookCheckout(book.getText())) {
			showAlert(Alert.AlertType.WARNING,((Stage) book.getScene().getWindow()),"Remove Book","Can't Delete a checkedout Book");
			return;	
		}
		if(HoldProcesses.bookHasHold(book.getText())) {
			showAlert(Alert.AlertType.WARNING,((Stage) book.getScene().getWindow()),"Remove Book","Can't Delete a Book with Hold");
			return;	
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	       alert.setTitle("Remove Book");
	       
	      alert.setContentText("Are you sure want to delete Book " + book.getText() + " ?");
	       Optional<ButtonType> answer = alert.showAndWait();
	       if (answer.get() == ButtonType.OK) { 
	   		Catalog.removeBook(book.getText());
	   		showAlert(Alert.AlertType.INFORMATION,((Stage) book.getScene().getWindow()),"Information","Operation successful BOOK ");
		}
		
		
		
		
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
