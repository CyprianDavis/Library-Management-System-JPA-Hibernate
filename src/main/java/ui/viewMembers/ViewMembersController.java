package ui.viewMembers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import data.model.member.Member;
import database.memberOperations.MembersOperations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewMembersController implements Initializable {
	@FXML
	private JFXTextField searchMember;
	@FXML
	private TableView<Member> membersTable;
	@FXML
	private TableColumn<Member,String> idNum;
	@FXML
	private TableColumn<Member,String> sName;
	@FXML
	private TableColumn<Member,String> gName;
	@FXML
	private TableColumn<Member,String> oName;
	@FXML
	private TableColumn<Member,String> gender;
	@FXML
	private TableColumn<Member,String> contact;
	@FXML
	private TableColumn<Member,String> email;
	@FXML
	private TableColumn<Member,String> address;
	@FXML
	private TableColumn<Member,String> joinedOn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colsIntialize();
		
		membersTable.setFocusTraversable(false);
		searchMember.setFocusTraversable(false);
		loadTable();
		search();
		
	}
	/**
	 * Initializes table columns
	 */
	private void colsIntialize(){
		idNum.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		sName.setCellValueFactory(new PropertyValueFactory<>("surName"));
		gName.setCellValueFactory(new PropertyValueFactory<>("givenName"));
		oName.setCellValueFactory(new PropertyValueFactory<>("otherName"));
		contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));	
		joinedOn.setCellValueFactory(new PropertyValueFactory<>("dateOfReg"));	
		
	}
	private void loadTable() {
		 membersTable.getItems().clear();
		 membersTable.getItems().addAll(MembersOperations.viewMembers());
		
	}
	private void search() {
		searchMember.textProperty().addListener((observable, oldValue, newValue) -> {	
			membersTable.getItems().clear();
			membersTable.getItems().addAll(MembersOperations.searchMemberByID(newValue));
			if(searchMember.getText().isEmpty()) {
				loadTable();
				
			}
		

				});		
	}

}
