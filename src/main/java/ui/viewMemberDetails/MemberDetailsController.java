package ui.viewMemberDetails;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.conn.DatabaseConn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class MemberDetailsController implements Initializable{
	private Connection conn = DatabaseConn.getConnection();
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
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM member WHERE memberNo = ?");
			stmt.setString(1, id);
			ResultSet rs= stmt.executeQuery();
			//Extract result set
			while(rs.next()) {
				idNum.setText(rs.getString(1));
				name.setText(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
				contact.setText(rs.getString(5));
				email.setText(rs.getString(6));
				address.setText(rs.getString(7));
				//Member image
				 img = new Image(rs.getBlob(8).getBinaryStream());
				 image.setFill(new ImagePattern(img));
				 sex.setText(rs.getString(9));
				 dateOfReg.setText(rs.getString(10));
				 
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
