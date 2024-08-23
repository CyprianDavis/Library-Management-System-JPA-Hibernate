package ui.viewMemberDetails;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MemberDetailsController implements Initializable{
	//private Connection conn = DatabaseConn.getConnection();
	@FXML
	private Label idNum;
	@FXML 
	private Circle image;
	@FXML
	private Label name;
	@FXML
	private Label contact;
	@FXML
	private Label email;
	@FXML
	private Label address;
	@FXML
	private Label sex;
	@FXML
	private Label dateOfReg;
	private Image img;
	private static String id;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadMemberDetails();
		image.setEffect(new DropShadow(+40d,0d,+2d,Color.DARKSEAGREEN));
		
	}
	/*sets the id Number of the */
	public static void setIdNum(String idNum) {
		id =idNum;
		
		
	
	}
	
	private  void loadMemberDetails() {
		
		}
	}


