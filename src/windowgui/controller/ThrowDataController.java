package windowgui.controller;

import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScoreShotput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static capstone.scorebook.data.concrete.ScorebookDatabase.getDB;

public class ThrowDataController extends MeetController {

	@FXML
	private Label meetDetails;
	@FXML
	private TextField feetField, inchField;
	@FXML
	private ComboBox<String> eventBox, weatherBox, windBox, autoBox;
	@FXML
	private ComboBox<Integer> throwBox;
	@FXML
	private Button enter;

	@FXML
	private void initialize() {

		eventBox.getItems().setAll(Arrays.asList("Discus", "Shotput"));
		weatherBox.getItems().setAll(Arrays.asList("Sunny", "Rainy"));
		windBox.getItems().setAll(Arrays.asList("None", "Little", "Medium", "High"));
		throwBox.getItems().setAll(Arrays.asList(1, 2, 3, 4));

		for (ComboBox b : Arrays.asList(eventBox, weatherBox, windBox, throwBox))
			b.getSelectionModel().selectFirst();

		autoBox.setEditable(true);
	}

	// enter into database
	public void enter() {

		if (eventBox.getValue().equals("Discus"))
			getDB().insert(new ScoreDiscus(meet.getID(), getAthleteID(), getWeather(), getThrow(), getDistanceInches()));

		else if (eventBox.getValue().equals("Shotput"))
			getDB().insert(new ScoreShotput(meet.getID(), getAthleteID(), getWeather(), getThrow(), getDistanceInches()));

		feetField.clear();
		inchField.clear();
		autoBox.getItems().clear();

	}

	public void autoComplete() {

		List<String> names = getDB().getAllAthletes().stream().map(Athlete::getFullName).collect(Collectors.toList());

		System.out.println(Arrays.toString(names.toArray()));

		ObservableList<String> options = FXCollections.observableArrayList();

		for (String str : names)
			if (str.contains(getName()))
				options.add(str); // add name to drop down

		autoBox = new ComboBox(options); // present drop down
	}

	public String getName() { return autoBox.getValue(); }
	public String getWeather() { return weatherBox.getValue(); }
	public String getWind() { return windBox.getValue(); }
	public int getThrow() { return throwBox.getValue(); }
	public int getDistanceInches() { return Integer.parseInt(feetField.getText()) * 12 + Integer.parseInt(inchField.getText()); }
	public String getAthleteID() {
		List<Athlete> athletes = getDB().getAllAthletes();

		for (Athlete a : athletes)
			if (a.getFullName().equals(getName()))
				return a.getID();

		return null;
	}

	public void onSetMeet() { meetDetails.setText(meet.toString()); }

}
