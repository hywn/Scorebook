package windowgui.controller;

import capstone.scorebook.data.concrete.Meet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import windowgui.Util;

public class SelectMeetController extends BaseController {

	@FXML
	private ComboBox<Meet> selectMeetBox;

	@FXML
	private Button selectMeetButton;

	@FXML
	private void initialize() {

		selectMeetBox.getItems().setAll(Util.getSortedMeets());
		selectMeetBox.getSelectionModel().selectFirst();

	}

	public void enterData() {

		this.<ThrowDataController>openFXML("ThrowData.fxml", controller -> controller.setMeet(selectMeetBox.getValue()));

	}

}
