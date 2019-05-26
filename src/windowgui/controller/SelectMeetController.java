package windowgui.controller;

import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class SelectMeetController extends SomeStuffController {

	@FXML
	private ComboBox<Meet> selectMeetBox;

	@FXML
	private Button selectMeetButton;

	@FXML
	protected void initialize() {

		selectMeetBox.getItems().setAll(ScorebookDatabase.getDB().getAllMeets());

	}

	public void enterData() {

		this.<AthleteThrowController>openFXML("ThrowData.fxml", controller -> controller.setMeet(selectMeetBox.getValue()));

	}

}
