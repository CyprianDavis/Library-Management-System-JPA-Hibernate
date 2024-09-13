package ui.issueBooks;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import data.model.book.Book;
import data.model.member.Member;
import database.memberOperations.MembersOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import utility.icon.IconUntil;

public class VerifyMemberController implements Initializable{
	@FXML
	private JFXTextField memberId;
	@FXML
	private JFXButton issueBtn;
	@FXML
	private TableView<Book>booksIssued;
	@FXML
	private TableColumn<Book,String>bkNumber; 
	@FXML
	private TableColumn<Book,String>title;
	@FXML
	private TableColumn<Book,String>author;
	@FXML
	private TableColumn<Book,String>category;
	private Member member=null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	/**
	 * Initializes table columns
	 */
	private void colsIntialize(){
		bkNumber.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		author.setCellValueFactory(new PropertyValueFactory<>("author"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		
	}
	@FXML
	private void searchMember() {
		if(memberId.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, ((Stage) memberId.getScene().getWindow()), "Verify Member!", "Please Enter Member ID ");
			return;
		}
		//Verify that member exists
		member = MembersOperations.findMember(memberId.getText());
		if(member == null) {
			showAlert(Alert.AlertType.ERROR, ((Stage) memberId.getScene().getWindow()), "Verify Member!", "UNKONWN  MEMBER ID ");
			return;	
	}
		((Stage) memberId.getScene().getWindow()).close();//close Verify stage
		loadIssueBooks();
		
		
	}
	private void loadIssueBooks() {
		
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("/ui/issueBooks/IssueBooks.fxml"));
			Stage stage = new Stage(StageStyle.DECORATED);
			 stage.setTitle("Issue Book");
			IconUntil.setStageIcon(stage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(parent));
			//colsIntialize();
			stage.show();
				           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
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
