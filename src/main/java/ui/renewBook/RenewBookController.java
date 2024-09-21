package ui.renewBook;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Window;

public class RenewBookController implements Initializable {
	@FXML
	private JFXTextField memberId;
	@FXML
	private JFXTextField book;
	@FXML
	private JFXButton renewBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	private void renewBook() {
		
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

