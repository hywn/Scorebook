package windowgui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import windowgui.controller.SomeStuffController;

public class MainPageController extends SomeStuffController {

	@FXML
	private Button registerMeetButton, registerAthleteButton, optionsButtion, inputDataButton, exitButton, dataButton,
		aboutButton;

	public void exit() {
		System.exit(0);
	}

	public void registerMeet() {
		openFXML("RegisterMeet.fxml");
	}

	public void registerAthlete() {
		openFXML("RegisterAthlete.fxml");
	}

	public void aboutPage() {
		openFXML("About.fxml");
	}

	public void inputData() {
		openFXML("ThrowData.fxml");
	}

	public void optionsPage() {

	}

	public void viewData() {

	}

}
