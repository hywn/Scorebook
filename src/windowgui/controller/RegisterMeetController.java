package windowgui.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegisterMeetController extends BaseController {
	@FXML
	private ComboBox roundOptions, indoorsOptions;
	@FXML
	private TextField meetNumField, addressField, tempField;
	
	

	//roundOptions = new ComboBox(FXCollections.observableArrayList("1", "2"));
	
	public String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).split(" ")[0];
	}
	
	public String getSeason() {
		int n= Integer.parseInt(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()).substring(5, 7));
		if(n==01||n==02||n==12) return "winter";
		else if(n>=3&&n<=5) return "spring";
		else if(n>=6&&n<8) return "summer";
		else return "fall";
	}
	
	
	public void initialize() {
		roundOptions.getItems().setAll(Arrays.asList(1, 2));
		indoorsOptions.getItems().setAll(Arrays.asList("Yes", "No"));

		for (ComboBox b : Arrays.asList(roundOptions, indoorsOptions))
			b.getSelectionModel().selectFirst();
	}
	
	public int getMeet() {
		return (int) roundOptions.getValue();
	}
	
	public int getMeetNum() {
		return Integer.parseInt(meetNumField.getText());
	}
	
	public String getAddress() {
		return addressField.getText();
	}
	
	//MAKE THIS A COMBOBOX YES/NO
	public int getIndoors() {
		if(indoorsOptions.getValue().equals("Yes")) return 0;
		return 1;
	}
	
	public int getTemp() {
		return Integer.parseInt(tempField.getText());
	}
	
	public void enter() {
		//enter the meet number into database
		ScorebookDatabase.getDB().insert(new Meet(getAddress(), getDate(), getSeason(), getIndoors(), getTemp()));
		roundOptions.setValue("");
		meetNumField.setText("");
		indoorsOptions.setValue("");
		addressField.setText("");
		tempField.setText("");
	}

}
