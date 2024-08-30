package ui.issueBooks;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import data.model.book.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class IssueBookController implements Initializable{
	@FXML
	private JFXTextField memberId;
	@FXML
	private JFXTextField issueBtn;
	@FXML
	private JFXTextField book;
	@FXML
	private JFXButton issueBk;
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colsIntialize();
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

}
