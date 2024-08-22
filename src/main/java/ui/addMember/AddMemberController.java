package ui.addMember;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import data.model.member.Member;
import database.memberOperations.MembersOperations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AddMemberController implements Initializable{
	@FXML
	private Circle image;
	@FXML
	private JFXButton imgBtn;
	@FXML
	private JFXTextField sName;
	@FXML
	private JFXTextField gName;
	@FXML
	private JFXTextField oName;
	@FXML
	private JFXTextField address;
	@FXML
	private JFXTextField contact;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField gender;
	@FXML
	private JFXButton saveBtn;
	@FXML
	private JFXButton clearBtn;
	private File file;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Image im = new Image("\\resources\\student.png");
		image.setFill(new ImagePattern(im));
		image.setEffect(new DropShadow(+40d,0d,+2d,Color.DARKSEAGREEN));
		
		
	}

	@FXML
	private File loadImage() {
		FileChooser fileChooser = new FileChooser();
		
		//Set extension files
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files(*.PNG)","*.PNG");
	
		fileChooser.getExtensionFilters().addAll(extFilterJPG,extFilterPNG);
		
		//show open file dialog
		file = fileChooser.showOpenDialog(((Stage) image.getScene().getWindow()));
		if(file != null) {
			Image img = new Image(file.toURI().toString());
			//studentImage.setRotate(-90);
			
			image.setFill(new ImagePattern(img));
			
		}
		return file;
		
	}
	@FXML
	private void clear() {
		Image im = new Image("\\resources\\student.png");
		image.setFill(new ImagePattern(im));
		sName.setText("");
		gName.setText("");
		oName.setText("");
		address.setText("");
		email.setText("");
		gender.setText("");
		contact.setText("");
		
	}
	@FXML
	private void save() {
		if(file == null) {
			showAlert(Alert.AlertType.ERROR, ((Stage) image.getScene().getWindow()), "Add Image Error!", "Please upload Image ");
			return;
		}
		if(sName.getText().isEmpty()) {
			 showAlert(Alert.AlertType.ERROR, ((Stage) sName.getScene().getWindow()), "Add Member!", "Please enter SurName ");
			 return;
			}
		if(gName.getText().isEmpty()) {
			 showAlert(Alert.AlertType.ERROR, ((Stage) sName.getScene().getWindow()), "Add Member!", "Please enter GivenName ");
			 return;
			}
		if(address.getText().isEmpty()) {
			 showAlert(Alert.AlertType.ERROR, ((Stage) sName.getScene().getWindow()), "Add Member!", "Please enter Address ");
			 return;
			}
		if(email.getText().isEmpty()) {
			 showAlert(Alert.AlertType.ERROR, ((Stage) sName.getScene().getWindow()), "Add Member!", "Please enter Email ");
			 return;
			}
		if(gender.getText().isEmpty()) {
			 showAlert(Alert.AlertType.ERROR, ((Stage) sName.getScene().getWindow()), "Add Member!", "Please enter Gender ");
			 return;
			}
		if(contact.getText().isEmpty()) {
			 showAlert(Alert.AlertType.ERROR, ((Stage) sName.getScene().getWindow()), "Add Member!", "Please enter Contact ");
			 return;
			}
		//create member object
		Member member = new Member(sName.getText(),gName.getText(),oName.getText());
		member.setAddress(address.getText());
		member.setEmail(email.getText());
		member.setGender(gender.getText());
		member.setContact(contact.getText());
		//save member and picture in the database
		try {
			MembersOperations.insertMember(member);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showAlert(Alert.AlertType.INFORMATION,((Stage) contact.getScene().getWindow()),"Information","Operation successful, Member ID  "+member.getMemberId());
		
		
		
		
		
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
