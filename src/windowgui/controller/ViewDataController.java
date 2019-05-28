package windowgui.controller;

import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewDataController extends MeetController {

	@FXML
	TableView<ScoreDiscus> scoreDataTable;

	void onSetMeet() {

		ScorebookDatabase db = ScorebookDatabase.getDB();

		TableColumn<ScoreDiscus, String> athleteCol, weatherCol;
		TableColumn<ScoreDiscus, Integer> orderCol, feetCol, inchCol;

		athleteCol = new TableColumn("Athlete");
		orderCol = new TableColumn("Order");
		feetCol = new TableColumn("Feet");
		inchCol = new TableColumn("Inches");
		weatherCol = new TableColumn("Weather");

		athleteCol.setCellValueFactory(p -> new SimpleStringProperty(db.getAthlete(p.getValue().getAthleteID()).getFullName()));
		orderCol.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getOrder()).asObject()); // why asObject??
		feetCol.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getDistance()/12).asObject());
		inchCol.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getDistance()%12).asObject());
		weatherCol.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getWeatherID()));
		scoreDataTable.getColumns().setAll(athleteCol, orderCol, feetCol, inchCol, weatherCol);

		scoreDataTable.setItems(FXCollections.observableList(db.getDiscusScores(meet.getID())));

	}

}
