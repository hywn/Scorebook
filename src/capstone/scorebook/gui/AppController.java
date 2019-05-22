package capstone.scorebook.gui;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import capstone.scorebook.data.concrete.*;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AppController {
	//from previous page
	private String meetID, meetType;	//previous page will say 0 for little meet
	
	@FXML
	private Button registerButton;
	@FXML
	private TextField nameField, throwField, weatherField, windField, eventField, distanceField;
	@FXML
	private ComboBox nameDrop, throwDrop, weatherDrop, windDrop, eventDrop;
	@FXML
	private Button enterButton;
	
	private ScorebookDatabase db;

	public void dataBase() {

		try {
			db = new ScorebookDatabase(Paths.get("test.db"));
		}

		catch (SQLException e) {

			System.err.println("Could not find specified database.");
			e.printStackTrace();
			return;

		}
		ObservableList<String> throwOptions = FXCollections.observableArrayList("1", "2", "3", "4");
		throwDrop = new ComboBox(throwOptions);
		ObservableList<String> weatherOptions = FXCollections.observableArrayList("Sunny", "Cloudy", "Light Rain",
				"Heavy Rain");
		weatherDrop = new ComboBox(weatherOptions);
		ObservableList<String> windOptions = FXCollections.observableArrayList("None", "Low", "Medium", "High");
		windDrop = new ComboBox(windOptions);
		ObservableList<String> eventOptions = FXCollections.observableArrayList("Discus", "Shotput");
		eventDrop = new ComboBox(eventOptions);
	}

	public void autoComplete() {
		dataBase();
		String s = nameField.getText();

		List<String> names = db.getAllAthletes().stream().map(Athlete::getFullName).collect(Collectors.toList());

		ObservableList<String> options = FXCollections.observableArrayList();

		for (String str : names) {
			if (str.contains(s)) {
				options.add(str); // add name to drop down
			}
		}

		nameDrop = new ComboBox(options); // present drop down
	}

	public String getPerson() {
		return nameField.getText();
	}
	
	public String getAthleteID() {
		//go through hashmap of athletes? and get the id of the athlete
		return "a;sdlfkj";
	}
	
	public int getThrow() {
		return Integer.parseInt(throwField.getText());
	}
	
	public String getWeather() {
		return weatherField.getText();
	}
	
	public String getWind() {
		return windField.getText();
	}
	
	public String getEvent() {
		return eventField.getText();
	}
	
	public int getDistance() {
		return Integer.parseInt(distanceField.getText());
	}
	
	public void enter() {
		if(eventField.getText().equals("Discus")) {
			db.insert(new ScoreDiscus(meetID, getAthleteID(), getWeather(), getThrow(), getDistance()));
		}
		else if(eventField.getText().equals("Shotput")) {
			db.insert(new ScoreDiscus(meetID, getAthleteID(), getWeather(), getThrow(), getDistance()));
		}
	}
	
	public void exit() {
		System.exit(0);
	}

}
