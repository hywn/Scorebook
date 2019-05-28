package windowgui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.Arrays;

import capstone.scorebook.data.concrete.Address;
import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.School;
import capstone.scorebook.data.concrete.ScorebookDatabase;

public class RegisterAthleteController extends BaseController {

	@FXML
	private TextField lastNameField, firstNameField, schoolField, gradYearField;

//	@FXML
//	private CheckBox male, female, other;

	public String getSchoolID() {
		Address schoolAddress = new Address("36811 Allder School Rd", "Purcellville", "VA", 20132);
		School school = new School(schoolAddress.getID(), "Woodgrove", "5a", "Dulles", "division"); // I don't know how districts, regions, or divisions work
		return school.getID();
		//return schoolField.getText();
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
		System.out.println(getSchoolID()+getGradYear()+ getName());
		
		ScorebookDatabase.getDB().insert(new Athlete(getSchoolID(), getGradYear(), getName()));



		for (TextField f : Arrays.asList(lastNameField, firstNameField, schoolField, gradYearField))
			f.clear();


	}
}
