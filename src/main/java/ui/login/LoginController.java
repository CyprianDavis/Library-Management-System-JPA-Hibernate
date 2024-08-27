package ui.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import data.model.user.PasswordUtils;
import data.model.user.User;
import database.library.UserOps;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utility.icon.IconUntil;


public class LoginController implements Initializable{
		//private static Connection conn = DatabaseConn.getConnection();//connection to the database
		@FXML
		private JFXTextField username;
		@FXML
		private JFXPasswordField password;
		@FXML
		private JFXButton loginBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void login() {
		if(username.getText().isEmpty()){
			username.getStyleClass().add("wrong-credentials");
			return;
		}
		if(password.getText().isEmpty()){
			password.getStyleClass().add("wrong-credentials");
			 return;
		}
		if(loginHandler(username.getText(),password.getText())) {
			closeStage();
			loadDashboard();
		}
		else {
			username.getStyleClass().add("wrong-credentials");
			password.getStyleClass().add("wrong-credentials");
			}
		}
		
	/**
	 * 
	 * @param username
	 * @param password
	 * @returns true if username and password exist in the database or false otherwise
	 */
	public static boolean loginHandler(String username,String password) {
		User user = UserOps.getUser(username);
		if(user!=null && PasswordUtils.checkPassword(password, user.getPassword()))
			return true;
		else
		return false;
		
		
		
	}
	//Closses the Login Stage
	private void closeStage() {
			((Stage) username.getScene().getWindow()).close();
	}
		//Loads the Main Interface
		private void loadDashboard()  {
				       
			Parent parent;
			try {
				parent = FXMLLoader.load(getClass().getResource("/ui/dashboard/main/DashBoard.fxml"));
				Stage stage = new Stage(StageStyle.DECORATED);
				stage.setTitle("DashBoard");
				IconUntil.setStageIcon(stage);
				             
				stage.setScene(new Scene(parent));
				stage.setResizable(false);
				stage.show();
				           
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
}