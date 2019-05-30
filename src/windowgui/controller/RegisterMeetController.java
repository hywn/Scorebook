package windowgui.controller;

import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.text.html.ImageView;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RegisterMeetController extends BaseController {

	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private class StrIntOption {
		String name;
		int num;
		StrIntOption(String name, int num) { this.name = name; this.num = num; }
		public String toString() { return name; }
	}

	@FXML
	private ComboBox<StrIntOption> roundOptions, indoorsOptions;
	@FXML
	private TextField addressField, dateField;
	@FXML
	private Label doneLabel;
	@FXML
	private ImageView background;

	public void initialize() {
		roundOptions.getItems().setAll(new StrIntOption("1-Round Meet", 1),
					       new StrIntOption("2-Round Meet", 2),
					       new StrIntOption("Practice", 0));
		indoorsOptions.getItems().setAll(new StrIntOption("No", 0),
						 new StrIntOption("Yes", 1));

		defaults();

		doneLabel.getParent().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> reset());
	}

	public void defaults() {

		for (ComboBox b : Arrays.asList(roundOptions, indoorsOptions))
			b.getSelectionModel().selectFirst();

		dateField.setText(todayDate());

		addressField.clear();

	}

	// enter meet into db
	//NEED TO REMOVE TEMP FROM MEET, AND ADD TO THE TABLE OF DATA WHEN INPUTTING AN ATHLETE THROW
	public void enter() {

		ScorebookDatabase.getDB().insert(new Meet(getAddress(), getDate(), getRounds(), getSeason(), getIndoors()));

		defaults();

		show();

	}

	public void reset() {
		doneLabel.setVisible(false);
	}

	public void show() {
		doneLabel.setVisible(true);
	}

	public String getAddress() { return addressField.getText(); }

	public int getRounds() { return roundOptions.getValue().num; }

	public int getIndoors() { return indoorsOptions.getValue().num; }

	public String todayDate() {
		return DATE_FORMAT.format(new Date());
	}

	public String getDate() {
		return dateField.getText();
	}

	public String getSeason() {
		int n = new Date().getMonth();
		if (n == 01 || n == 02 || n == 12)
			return "winter";
		else if (n >= 3 && n <= 5)
			return "spring";
		else if (n >= 6 && n < 8)
			return "summer";
		else
			return "fall";
	}

}
