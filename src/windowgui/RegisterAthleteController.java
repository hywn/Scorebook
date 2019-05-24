package windowgui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class RegisterAthleteController extends SomeStuffController {
	@FXML
	private TextField lastNameField, firstNameField, miField, schoolField, gradYearField;
	@FXML
	private CheckBox male, female, other;
	
	private CheckBox[] boxes=new CheckBox[]{ male, female, other};

	
	public void enter() {
		//enter into database
		TextField[] fields=new TextField[] {
				lastNameField, firstNameField, miField, schoolField, gradYearField
		};
		for(TextField f: fields) {
			f.setText("");
		}
		//CANT GET THE BOXES TO CLEAR
		for(CheckBox b: boxes) {
			b.setSelected(false);
		}
	}
}
