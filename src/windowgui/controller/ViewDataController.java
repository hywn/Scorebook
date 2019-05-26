package windowgui.controller;

import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class ViewDataController extends MeetController {

	@FXML
	TableView<ScoreDiscus> scoreDataTable;

	void onSetMeet() {

		TableColumn<ScoreDiscus, String> athleteCol = new TableColumn("Athlete");
		athleteCol.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAthleteID()));

		TableColumn<ScoreDiscus, Integer> orderCol = new TableColumn("Order");
		orderCol.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getOrder()).asObject()); // why asObject??

		TableColumn<ScoreDiscus, Integer> distanceCol = new TableColumn("Distance");
		distanceCol.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getDistance()).asObject());

		scoreDataTable.getColumns().setAll(athleteCol, orderCol, distanceCol);

		List<ScoreDiscus> scores = ScorebookDatabase.getDB().getDiscusScores(meet.getID());

		scoreDataTable.setItems(FXCollections.observableList(scores));

	}

}
