package utility.icon;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class IconUntil {
	public static final String ICON_IMAGE_LOC = "resources/libraries_icon.png";
	
	 public static void setStageIcon(Stage stage) {
		 
	        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
	       
	    }
}
