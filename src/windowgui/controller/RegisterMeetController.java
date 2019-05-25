package windowgui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class RegisterMeetController extends SomeStuffController {
	@FXML
	private ComboBox roundOptions;

	//roundOptions = new ComboBox(FXCollections.observableArrayList("1", "2"));

	
	public int getMeet() {
		return (int) roundOptions.getValue();
	}
	
	public void enter() {
		//enter the meet number into database
		roundOptions.setValue("");
	}

}
