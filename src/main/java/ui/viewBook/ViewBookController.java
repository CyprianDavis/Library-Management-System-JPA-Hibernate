package ui.viewBook;

import java.net.URL;
import java.util.ResourceBundle;

import data.model.book.Book;
import database.catalog.Catalog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ViewBookController implements Initializable{
	@FXML
	private TextField bookSearch;
	@FXML
	private Button btnSearch;
	@FXML
	private Label bookTitle;
	@FXML
	private Label bookId;
	@FXML
	private Label ISBN;
	@FXML
	private Label author;
	@FXML
	private Label coAuthor;
	@FXML
	private Label edition;
	@FXML
	private Label language;
	@FXML
	private Label publisher;
	@FXML
	private Label category;
	@FXML
	private Label description;
	@FXML
	private Label year;
	@FXML
	private Label dateOfEntry;
	@FXML
	private Label status;
	@FXML
	private AnchorPane displayDetails;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		clear();
		
		bookSearch.textProperty().addListener((observable, oldValue, newValue) -> {
		clear();
		
			
		});
	
	}
	private void clear() {
		displayDetails.setVisible(false);
		
		
		 
	}
	private void showDetails() {
		displayDetails.setVisible(true);
		
		
	}
	@FXML
	private void displayBookDetails() {
		
		if(bookSearch.getText().isEmpty()) {
			showAlert(Alert.AlertType.WARNING,((Stage) category.getScene().getWindow()),"","Invalid Input for Book ID or ISBN");
			return;
		}
	if(!Catalog.bookExists(bookSearch.getText())) {
			showAlert(Alert.AlertType.WARNING,((Stage) category.getScene().getWindow()),"","Unkown Book Id");
			return;
		}
			showDetails();
			Book book = Catalog.findBook(bookSearch.getText());
			bookId.setText(book.getBookId());
			ISBN.setText(book.getISBN());
			bookTitle.setText(book.getTitle());
			author.setText(book.getAuthor());
			coAuthor.setText(book.getCoAuthor());
			edition.setText(book.getEdition());
			language.setText(book.getLanguage());
			publisher.setText(book.getPublisher());
			category.setText(book.getCategory());
			description.setText(book.getDescription());
			year.setText(String.valueOf(book.getPublicationYear()));
			dateOfEntry.setText(book.getEntryDate());
			status.setText(book.getStatus());
			
			
			
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
