package windowgui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Collections;

public class RegisterAthleteController extends SomeStuffController {

	@FXML
	private TextField lastNameField, firstNameField, miField, schoolField, gradYearField;

	@FXML
	private CheckBox male, female, other;

	//enter into database
	public void enter() {

		// clear stuff

		for (TextField f : Arrays.asList(lastNameField, firstNameField, miField, schoolField, gradYearField))
			f.clear();

		for (CheckBox b : Arrays.asList(male, female, other))
			b.setSelected(false);

	}
}
