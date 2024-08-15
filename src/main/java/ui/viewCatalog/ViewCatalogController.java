package ui.viewCatalog;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import data.model.book.Book;
import database.catalog.Catalog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewCatalogController implements Initializable{
	@FXML
	private TableView<Book> catalogTable;
	@FXML
	private TableColumn<Book,String> bookId;
	@FXML
	private TableColumn<Book,String> title;
	@FXML
	private TableColumn<Book,String> author;
	@FXML
	private TableColumn<Book,String> coAuthor;
	@FXML
	private TableColumn<Book,String> ISBN;
	@FXML
	private TableColumn<Book,String> publisher;
	@FXML
	private TableColumn<Book,String> edition;
	@FXML
	private TableColumn<Book,String> status;
	@FXML
	private JFXTextField searchBk;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colsIntialize();//Initialize table columns
		catalogTable.setFocusTraversable(false);
		searchBk.setFocusTraversable(false);
		search();
	
		
		loadTable();
		
	}
	/**
	 * Initializes table columns
	 */
	private void colsIntialize(){
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		author.setCellValueFactory(new PropertyValueFactory<>("author"));
		coAuthor.setCellValueFactory(new PropertyValueFactory<>("coAuthor"));
		ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
		publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		edition.setCellValueFactory(new PropertyValueFactory<>("edition"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));	
		
		
	}
	private void loadTable() {
		catalogTable.getItems().clear();
		catalogTable.getItems().addAll(Catalog.getCatalogBooks());
	}
	private void search() {
		searchBk.textProperty().addListener((observable, oldValue, newValue) -> {	
			catalogTable.getItems().clear();
			catalogTable.getItems().addAll(Catalog.getBooks(newValue));
			if(searchBk.getText().isEmpty()) {
				loadTable();
				
			}
		

				});		
	}
	
	

}
