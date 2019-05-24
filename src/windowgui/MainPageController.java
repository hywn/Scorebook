package windowgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPageController extends SomeStuffController {

	@FXML
	private Button registerMeetButton, registerAthleteButton, optionsButtion, inputDataButton, exitButton, dataButton,
			aboutButton;

	public void exit() {
		System.exit(0);
	}

	public void registerMeet() {
		// do these you lazy idiot
	}

	public void inputData() {

	}

	public void registerAthlete() {
		openFXML("RegisterAthlete.fxml");
	}

	public void about() {
		openFXML("About.fxml");
	}

}
