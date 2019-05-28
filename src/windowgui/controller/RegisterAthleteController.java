package windowgui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Arrays;

import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.ScorebookDatabase;

public class RegisterAthleteController extends BaseController {

	@FXML
	private TextField lastNameField, firstNameField, miField, schoolField, gradYearField;

	@FXML
	private CheckBox male, female, other;

	public String getSchoolID() {
		return schoolField.getText();
	}
	
	public String getName() {
		return firstNameField.getText()+" "+lastNameField.getText();
	}
	
	public int getGradYear() {
		return Integer.parseInt(gradYearField.getText());
	}
	
	//enter into database
	public void enter() {

		// clear stuff

		for (TextField f : Arrays.asList(lastNameField, firstNameField, miField, schoolField, gradYearField))
			f.clear();

		for (CheckBox b : Arrays.asList(male, female, other))
			b.setSelected(false);
		System.out.println(getSchoolID()+getGradYear()+ getName());
		ScorebookDatabase.getDB().insert(new Athlete(getSchoolID(), getGradYear(), getName()));

	}
}
