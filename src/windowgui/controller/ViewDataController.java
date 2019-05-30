package windowgui.controller;

import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScoreThrow;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ViewDataController extends MeetController {

	@FXML
	TableView<ScoreThrow> scoreDataTable;

	public void goBack() {
		this.<SelectMeetController>openFXML("SelectMeet.fxml", controller -> controller.setFXMLtoOpen("ViewData.fxml"));
	}

	void onSetMeet() {

		ScorebookDatabase db = ScorebookDatabase.getDB();

		addColumn("Event", score -> new SimpleStringProperty((score instanceof ScoreDiscus) ? "Discus" : "Shotput"));

		addColumn("Athlete", score -> new SimpleStringProperty(db.getAthlete(score.getAthleteID()).getFullName()));

		addColumn("Order", score -> new SimpleIntegerProperty(score.getOrder()));

		addColumn("Feet-Inches", score -> new SimpleStringProperty(
			String.format("%d-%d", score.getDistance() / 12, score.getDistance() % 12)));

		addColumn("Weather", score -> new SimpleStringProperty(score.getWeatherID()));

		addColumn("Temperature", score -> new SimpleIntegerProperty(score.getTemp()));

		scoreDataTable.setItems(FXCollections.observableList(db.getThrowScores(meet.getID())));

		exportToFile(toCSV());

	}

	public <E> void addColumn(String colName, Function<ScoreThrow, ObservableValue<E>> func) {

		TableColumn<ScoreThrow, E> col = new TableColumn(colName);

		col.setCellValueFactory(p -> func.apply(p.getValue()));

		scoreDataTable.getColumns().add(col);

	}

	public String toCSV() {

		StringBuilder b = new StringBuilder();

		b.append(String.join(",", scoreDataTable.getColumns()
			.stream().map(TableColumn::getText)
			.collect(Collectors.toList())));
		b.append('\n');

		for (ScoreThrow v : scoreDataTable.getItems()) {
			b.append(String.join(",", scoreDataTable.getColumns()
				.stream().map(c -> c.getCellObservableValue(v).getValue().toString())
				.collect(Collectors.toList())));
			b.append('\n');
		}

		return b.toString();

	}

	private static void exportToFile(String text) {

		try (PrintWriter out = new PrintWriter("test.csv")) {
			out.println(text);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
