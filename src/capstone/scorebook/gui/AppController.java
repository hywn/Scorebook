package capstone.scorebook.gui;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScoreShotput;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AppController {
	@FXML
	private Button registerButton, enterButton;
	@FXML
	private TextField nameField, throwField, weatherField, windField, eventField, distanceField, roundField, meetField;
	@FXML
	private ComboBox nameDrop, throwDrop, weatherDrop, windDrop, eventDrop, roundDrop, meetDrop;
	@FXML
	private Label meetLabel;
	
	@FXML
	private AnchorPane mainMenuPane, AthleteRegistrationPane, aboutPane, inputDataPane, viewDataPane, optionPane;
	
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
		ObservableList<String> roundOptions = FXCollections.observableArrayList("1", "2");
		roundDrop = new ComboBox(roundOptions);
		ObservableList<String> meetOptions = FXCollections.observableArrayList("1", "2");
		meetDrop = new ComboBox<String>(meetOptions);
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

		nameDrop = new ComboBox<String>(options); // present drop down
	}

	public String getPerson() {
		return nameField.getText();
	}

	public String getAthleteID() {
		List<Athlete> athletes = db.getAllAthletes();

		for (Athlete a : athletes)
			if (a.getFullName().equals(getPerson()))
				return a.getID();

		return null;
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

	public int getRound() {
		return Integer.parseInt(roundField.getText());
	}
	
	public String getMeetID() {
		return meetField.getText();
	}
	
	public void enterThrowData() {
		if (eventField.getText().equals("Discus")) {
			db.insert(new ScoreDiscus(getMeetID(), getAthleteID(), getWeather(), getThrow(), getDistance()));
		} else if (eventField.getText().equals("Shotput")) {
			db.insert(new ScoreShotput(getMeetID(), getAthleteID(), getWeather(), getThrow(), getDistance()));
		}
	}
	
	// MAIN MENU METHODS

	public void exit(ActionEvent e) {
		System.exit(0);
	}
	
	public void openAthleteRegistration (ActionEvent e) {
		AthleteRegistrationPane.setVisible(true);
	}
	
	public void openAbout(ActionEvent e) {
		aboutPane.setVisible(true);
	}
	
	public void openInputData(ActionEvent e) {
		inputDataPane.setVisible(true);
	}
	
	public void openViewData(ActionEvent e) {
		viewDataPane.setVisible(true);
	}
	
	public void openOptionPane(ActionEvent e) {
		optionPane.setVisible(true);
	}
	
	
	// Home Methods
	public void goHomeFromAR(MouseEvent e) {
		AthleteRegistrationPane.setVisible(false);
	}
	
	public void goHomeFromAbout(MouseEvent e) {
		aboutPane.setVisible(false);
	}
	
	public void goHomeFromInputData(MouseEvent e) {
		inputDataPane.setVisible(false);
	}
	
	public void goHomeFromViewData(MouseEvent e) {
		viewDataPane.setVisible(false);
	}
	
	public void goHomeFromOption(MouseEvent e) {
		optionPane.setVisible(false);
	}

}