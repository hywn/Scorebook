package windowgui.controller;

import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScoreThrow;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ViewDataController extends BaseController {

	@FXML
	TableView<ScoreThrow> scoreDataTable;

	List<Meet> meets;

	public void goBack() {
		this.openFXML("SelectMeetToView.fxml");
	}

	void setMeets(List<Meet> meets) {

		this.meets = meets;

		ScorebookDatabase db = ScorebookDatabase.getDB();

		addColumn("Meet", score -> new SimpleStringProperty(score.getMeetID()));

		addColumn("Event", score -> new SimpleStringProperty((score instanceof ScoreDiscus) ? "Discus" : "Shotput"));

		addColumn("Athlete", score -> new SimpleStringProperty(db.getAthlete(score.getAthleteID()).getFullName()));

		addColumn("Order", score -> new SimpleIntegerProperty(score.getOrder()));

		addColumn("Feet-Inches", score -> new SimpleStringProperty(
			String.format("%d-%d", score.getDistance() / 12, score.getDistance() % 12)));

		addColumn("Weather", score -> new SimpleStringProperty(score.getWeatherID()));

		addColumn("Temperature", score -> new SimpleIntegerProperty(score.getTemp()));

		scoreDataTable.setItems(FXCollections.observableList(db.getThrowScores(meets.stream().map(m -> m.getID()).collect(Collectors.toList()).toArray(new String[]{}))));

	}

	public <E> void addColumn(String colName, Function<ScoreThrow, ObservableValue<E>> func) {

		TableColumn<ScoreThrow, E> col = new TableColumn(colName);

		col.setCellValueFactory(p -> func.apply(p.getValue()));

		scoreDataTable.getColumns().add(col);

	}

	public void exportMeet() {
		exportCSV(String.join(", ", meets.stream().map(Meet::toString).collect(Collectors.toList())) + ".csv", toCSV());
	}

	public String toCSV() {

		StringBuilder b = new StringBuilder();

		b.append(String.join(",", scoreDataTable.getColumns()
			.stream().map(TableColumn::getText)
			.collect(Collectors.toList())));
		b.append('\n');

		for (ScoreThrow v : scoreDataTable.getItems()) {
			b.append(String.join(",", scoreDataTable.getColumns()
				.stream().map(c -> '\t' + c.getCellObservableValue(v).getValue().toString()) // prepend tab so that feet-inches doesn't get imported as a date
				.collect(Collectors.toList())));
			b.append('\n');
		}

		return b.toString();

	}

	private static void exportCSV(String defaultFileName, String text) {

		FileChooser fc = new FileChooser();

		fc.setTitle("Choose where to export CSV");
		fc.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Comma Separated Values (*.csv)", "*.csv"));
		fc.setInitialFileName(defaultFileName);

		File file = fc.showSaveDialog(null);
		if (file == null) return;

		try (PrintWriter out = new PrintWriter(file.getPath())) { out.println(text); }
		catch (FileNotFoundException e) { e.printStackTrace(); return; }

		Alert success = new Alert(Alert.AlertType.INFORMATION, "Do you wish to open it now?", ButtonType.YES, ButtonType.NO);
		success.setHeaderText(file.getName() + " was saved successfully.");

		if (success.showAndWait().get() != ButtonType.YES) return;

		try { Desktop.getDesktop().open(file); }
		catch (IOException e) { e.printStackTrace(); }

	}

}
