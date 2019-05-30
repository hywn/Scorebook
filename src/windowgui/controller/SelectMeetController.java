package windowgui.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class SelectMeetController extends BaseController {

	private String fxmlToOpen;

	@FXML
	private ComboBox<Meet> selectMeetBox;

	@FXML
	private Button selectMeetButton;

	@FXML
	private void initialize() {

		ArrayList<Meet> meets = ScorebookDatabase.getDB().getAllMeets();

		meets.sort((m2, m1) -> {
			try {
				Date d1 = RegisterMeetController.DATE_FORMAT.parse(m1.getDate());
				Date d2 = RegisterMeetController.DATE_FORMAT.parse(m2.getDate());

				return d1.compareTo(d2);
			}
			catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
		});

		selectMeetBox.getItems().setAll(meets);
		selectMeetBox.getSelectionModel().selectFirst();

	}

	public void enterData() {

		System.out.println(fxmlToOpen);

		this.<MeetController>openFXML(fxmlToOpen, controller -> controller.setMeet(selectMeetBox.getValue()));

	}

	public void setFXMLtoOpen(String fxml) { this.fxmlToOpen = fxml; }

}
