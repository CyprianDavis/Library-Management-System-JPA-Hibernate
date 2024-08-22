package ui.viewMemberDetails;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import database.memberOperations.MembersOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import utility.icon.IconUntil;

public class ViewMemberDetails implements Initializable{
	@FXML
	private JFXTextField memberID;
	@FXML
	private JFXButton viewBtn;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
	}
	@FXML
	private void loadMemberDetails() {
		if(memberID.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING,((Stage) memberID.getScene().getWindow()),"View Member Details","Enter Member ID Number");
			return;
		}
		if(!MembersOperations.memberExists(memberID.getText())) {
			showAlert(Alert.AlertType.WARNING,((Stage) memberID.getScene().getWindow()),"View Member Details","Unkown Member ID Number");
			return;
		}
		else {
			//load member details
			MemberDetailsController.setIdNum(memberID.getText());
			MemberDetails();
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
	
	
	private void MemberDetails() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/viewMemberDetails/MemberDetails.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("Member Details");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			closeStage();
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
				
	}
	//Closses the Login Stage
	private void closeStage() {
			((Stage) memberID.getScene().getWindow()).close();
	}
}
