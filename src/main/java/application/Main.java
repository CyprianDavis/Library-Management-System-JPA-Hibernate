package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import utility.icon.IconUntil;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/login/LoginForm.fxml"));
			AnchorPane root = (AnchorPane)fxmlLoader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			IconUntil.setStageIcon(primaryStage);
			primaryStage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
		}
	
	public static void main(String[] args) {	
		Application.launch(args);
		
	}
		}

