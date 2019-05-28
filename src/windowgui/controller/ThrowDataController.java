package windowgui.controller;

import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScoreShotput;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

import java.util.ArrayList;
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
	private ComboBox<Integer> roundBox, throwBox;
	@FXML
	private Button enter;

	// basically initialize
	public void onSetMeet() {

		meetDetails.setText(meet.toString());

		roundBox.getItems().setAll(1);
		throwBox.getItems().setAll(1, 2, 3);

		if (meet.getRounds() == 2) roundBox.getItems().add(2);
		else throwBox.getItems().add(4);

		if (meet.getRounds() == 0) {
			throwBox.setEditable(true);
			roundBox.setEditable(true);
			throwBox.setConverter(new StringConverter<Integer>() {
				@Override
				public String toString(Integer i) {
					return i.toString();
				}
				@Override
				public Integer fromString(String s) {
					return Integer.parseInt(s);
				}
			});
		}

		eventBox.getItems().setAll(Arrays.asList("Discus", "Shotput"));
		weatherBox.getItems().setAll(Arrays.asList("Sunny", "Cloudy", "Rainy"));
		windBox.getItems().setAll(Arrays.asList("None", "Little", "Medium", "High"));

		for (ComboBox b : Arrays.asList(eventBox, weatherBox, windBox, roundBox, throwBox))
			b.getSelectionModel().selectFirst();

		autoBox.setEditable(true);
		autoBox.getEditor().textProperty().addListener(value -> autoComplete(((StringProperty) value).getValue()));

	}

	// enter into database
	public void enter() {

		if (eventBox.getValue().equals("Discus")) {
			System.out.println(meet.getID() + " " + getAthleteID() + getWeather() + getThrow() + getDistanceInches());
			getDB().insert(new ScoreDiscus(meet.getID(), getAthleteID(), getWeather(), getRound(), getThrow(), getDistanceInches()));
		} else if (eventBox.getValue().equals("Shotput"))
			getDB().insert(new ScoreShotput(meet.getID(), getAthleteID(), getWeather(), getRound(), getThrow(), getDistanceInches()));

		feetField.clear();
		inchField.clear();
		autoBox.getItems().clear();

	}

	public void autoComplete(String text) {

		System.out.println(text);

		List<String> names = getDB().getAllAthletes().stream().map(Athlete::getFullName).collect(Collectors.toList());

		if (text.isEmpty()) {
			Platform.runLater(() -> autoBox.getItems().setAll(names));
			Platform.runLater(() -> autoBox.hide());
			return;
		}

		ArrayList<String> matchedNames = new ArrayList();

		for (String str : names)
			if (str.toLowerCase().contains(text.toLowerCase())) {
				if (str.equals(text)) return;
				Platform.runLater(() -> matchedNames.add(str));
			}

		Platform.runLater(() -> autoBox.getItems().setAll(matchedNames));
		Platform.runLater(() -> autoBox.show());

		System.out.println(Arrays.toString(autoBox.getItems().toArray()));

	}

	public String getName() {
		return autoBox.getValue();
	}

	public String getWeather() {
		return weatherBox.getValue();
	}

	public String getWind() {
		return windBox.getValue();
	}

	public int getRound() {
		return roundBox.getValue();
	}

	public int getThrow() {
		return throwBox.getValue();
	}

	public int getDistanceInches() {
		int inch, feet;

		inch = inchField.getText().isEmpty() ? 0 : Integer.parseInt(inchField.getText());
		feet = feetField.getText().isEmpty() ? 0 : Integer.parseInt(feetField.getText());

		return inch + feet * 12;
	}

	public String getAthleteID() {
		List<Athlete> athletes = getDB().getAllAthletes();

		for (Athlete a : athletes)
			if (a.getFullName().equals(getName()))
				return a.getID();

		return null;
	}

}
