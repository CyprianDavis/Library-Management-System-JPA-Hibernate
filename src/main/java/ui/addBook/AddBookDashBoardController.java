package ui.addBook;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import data.model.book.Book;
import database.catalog.Catalog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;
import validateInput.ValidateUserInput;

/**
 * 
 * @author CYPRIAN DAVIS
 * Add BookDashBoardController
 *
 */

public class AddBookDashBoardController implements Initializable{
	@FXML
	private JFXTextField bkTitle; //JFXTextField for bk title
	@FXML
	private JFXTextField bkAuthor;//JFXTextField for bk Author
	@FXML
	private JFXTextField coAuthor;//JFXTextField for co Author
	@FXML
	private JFXTextField ISBN;//JFXTextField for bk ISBN
	@FXML
	private JFXTextField publicationYr;//JFXTextField for bk publication yr
	@FXML
	private JFXTextField edition; //JFXTextField for bk edition
	@FXML
	private JFXTextField language;//JFXTextField for bk language
	@FXML
	private JFXTextField publisher;//JFXTextField for bk publisher
	@FXML
	private JFXButton clearBtn; //JFXButton clear
	@FXML
	private JFXButton saveBtn; //JFXButton clear
	@FXML
	private JFXTextField category; 
	@FXML
	private JFXTextField bkDescription;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//Input validation
		
	}
	@FXML
	//sets the text content of the fields to empty
	private void clear() {
		bkTitle.setText("");
		bkAuthor.setText("");
		coAuthor.setText("");
		ISBN.setText("");
		publicationYr.setText("");
		edition.setText("");
		language.setText("");
		publisher.setText("");
		category.setText("");
		bkDescription.setText("");
	}
	@FXML
	private void saveBook () {
	if(bkTitle.getText().isEmpty()) {
		 showAlert(Alert.AlertType.WARNING, ((Stage) bkTitle.getScene().getWindow()), "Add Book Error!", "Please enter Book Title ");
		 return;
		}
	if(bkAuthor.getText().isEmpty()) {
		showAlert(Alert.AlertType.WARNING, ((Stage) bkAuthor.getScene().getWindow()), "Add Book Error!", "Please enter Book Author ");
		return;
	}
	if(ISBN.getText().isEmpty()) {
		showAlert(Alert.AlertType.WARNING, ((Stage) ISBN.getScene().getWindow()), "Add Book Error!", "Please enter ISBN Number ");
		return;
		}
	if(publicationYr.getText().isEmpty()) {
		 showAlert(Alert.AlertType.WARNING, ((Stage) publicationYr.getScene().getWindow()), "Add Book Error!", "Please enter Publication Year ");
			 return;
		}
	if(edition.getText().isEmpty()) {
		 showAlert(Alert.AlertType.WARNING, ((Stage) edition.getScene().getWindow()), "Add Book Error!", "Please enter Book Edition ");
			 return;
		}
	if(language.getText().isEmpty()) {
		 showAlert(Alert.AlertType.WARNING, ((Stage) language.getScene().getWindow()), "Add Book Error!", "Please enter language ");
			 return;
		} 
	if(publisher.getText().isEmpty()) {
		 showAlert(Alert.AlertType.WARNING, ((Stage) publisher.getScene().getWindow()), "Add Book Error!", "Please enter Book Publisher");
			 return;
		}
	if(category.getText().isEmpty()) {
		 showAlert(Alert.AlertType.WARNING, ((Stage) category.getScene().getWindow()), "Add Book Error!", "Please enter Book Category");
			 return;
		}
	if(bkDescription.getText().isEmpty()) {
		 showAlert(Alert.AlertType.WARNING, ((Stage) bkDescription.getScene().getWindow()), "Add Book Error!", "Please enter Book Description");
			 return;
		}
	if(Catalog.checkISBN(ISBN.getText())) {
		showAlert(Alert.AlertType.WARNING,((Stage) ISBN.getScene().getWindow()),"Warning","Duplicate ISBN Number");
		return;
	}
	
	//validate user input
	if(!ValidateUserInput.validateOnlyCharacterInput(bkAuthor.getText())) {
		showAlert(Alert.AlertType.WARNING,((Stage) bkAuthor.getScene().getWindow()),"Book Author","Invalid Input for Author");
		return;
	}
	if(!ValidateUserInput.validateOnlyCharacterInput(coAuthor.getText())) {
		showAlert(Alert.AlertType.WARNING,((Stage) coAuthor.getScene().getWindow()),"Co Author","Invalid Input for Co Author");
		return;
	}

	if(!ValidateUserInput.validateOnlyIntegerInput(publicationYr.getText())) {
		showAlert(Alert.AlertType.WARNING,((Stage) publicationYr.getScene().getWindow()),"Publication year","Invalid Input for year of publication");
		return;
	}
	if(!ValidateUserInput.validateOnlyCharacterInput(edition.getText())) {
		showAlert(Alert.AlertType.WARNING,((Stage) edition.getScene().getWindow()),"Edition","Invalid Input for Book Edition");
		return;
	}
	if(!ValidateUserInput.validateOnlyCharacterInput(language.getText())) {
		showAlert(Alert.AlertType.WARNING,((Stage) language.getScene().getWindow()),"Language","Invalid Input for language");
		return;
	}
	if(!ValidateUserInput.validateOnlyCharacterInput(publisher.getText())) {
		showAlert(Alert.AlertType.WARNING,((Stage) publisher.getScene().getWindow()),"Publisher","Invalid  input for Book Publisher");
		return;
	}
	if(!ValidateUserInput.validateOnlyCharacterInput(category.getText())){
		showAlert(Alert.AlertType.WARNING,((Stage) category.getScene().getWindow()),"Category","Invalid Input for Book Category");
		return;
	}
	
	
	
	//create book object
	Book book = new Book(bkTitle.getText(),bkAuthor.getText(),"Available");
	book.setCoAuthor(coAuthor.getText());
	book.setISNB(ISBN.getText());
	book.setPublicationYear(Integer.parseInt(publicationYr.getText()));
	book.setPublisher(publisher.getText());
	book.setCategory(category.getText());
	book.setEdition(edition.getText());
	book.setLanguage(language.getText());
	book.setDescription(bkDescription.getText());
	
	//Insert book in database
	Catalog.insertBook(book);
	showAlert(Alert.AlertType.INFORMATION,((Stage) category.getScene().getWindow()),"Information","Operation successful BOOK ID "+book.getBookId());
	
	
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
