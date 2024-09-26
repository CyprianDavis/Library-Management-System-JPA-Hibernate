package ui.hold;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewHoldsOnBookController implements Initializable{
	@FXML
	private JFXTextField searchBk;
	@FXML
	private TableView<DisplayHoldsDetails> holdsTable;
	@FXML
	private TableColumn<DisplayHoldsDetails,String>holdId;
	@FXML
	private TableColumn<DisplayHoldsDetails,String>member;
	@FXML
	private TableColumn<DisplayHoldsDetails,String>book;
	@FXML
	private TableColumn<DisplayHoldsDetails,String>reservationDate;
	@FXML
	private TableColumn<DisplayHoldsDetails,String>status;
	@FXML
	private TableColumn<DisplayHoldsDetails,String>comment;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
