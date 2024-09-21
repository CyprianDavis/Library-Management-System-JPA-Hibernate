package ui.dashboard.main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import database.catalog.Catalog;
import database.memberOperations.MembersOperations;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utility.icon.IconUntil;

public class DashBoardController implements Initializable{
	@FXML
	private Button addBookBtn;
	@FXML
	private Button addMemberBtn;
	@FXML
	private Button issueBkBtn;
	@FXML
	private Button returnBkBtn;
	@FXML
	private Button placeHoldBtn;
	@FXML
	private  Label totalBks;
	@FXML
	private Label availablebks;
	@FXML
	private Label numIssuedBks;
	@FXML
	private Label numMembers;
	@FXML
	private  Label dateTime;
	@FXML
	private MenuButton menuBtn;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadData();
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO,e->
		dateTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
		//dateTime1.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
		),
			new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
		
	}
	/**
	 * loads data for dashboard
	 */
	public void loadData() {
		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO,e->{
				totalBks.setText(String.valueOf(Catalog.totalNumberOfBooks()));//loads the number of books the libraryowns
				availablebks.setText(String.valueOf(Catalog.getBooksByStatus("Available")));//loads the number of books availale in the system
				numIssuedBks.setText(String.valueOf(Catalog.getBooksByStatus("Issued")));
				numMembers.setText(String.valueOf(MembersOperations.totalNumberOfMembers()));
				
		}),new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();
		
	}
	
	/**
	 * sets the number of available books
	 * @param num
	 */
	public void setNumOfVailablebks(int num) {
		availablebks.setText(String.valueOf(num));
	}
	/**
	 * Sets the number of issued books
	 * @param num
	 */
	public void setNumIssuedBks(int num) {
		numIssuedBks.setText(String.valueOf(num));
	}
	@FXML
	/**
	 * Loads the add book dashboard
	 */
	private void loadAddBook() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/addBook/AddBookDashboard.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("Add Book");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	@FXML
	private void loadViewCatalog() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/viewCatalog/ViewCatalog.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("View Catalog");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void loadViewBook() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/viewBook/ViewBook.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("View Book");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void deleteBook() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/removeBook/RemoveBook.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("Remove Book");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void addMember() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/addMember/AddMember.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("Add Member");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		}
	@FXML
	private void viewMembers() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/viewMembers/ViewMembers.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("View Members");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		}
	@FXML
	private void viewMemberDetails() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/viewMemberDetails/ViewMemberDetails.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("view Member Info");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}	
		}
	@FXML
	private void IssueBook() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/issueBooks/SearchMember.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("Verify Member");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
		}
	@FXML
	private void returnBook() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/returnBook/ReturnBook.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("Return Book");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		}
	@FXML
	private void renewBook() {
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/renewBook/RenewBook.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("Book Renewal");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
	}
}


