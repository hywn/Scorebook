package windowgui.controller;

public class MainMenuController extends BaseController {

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
		this.openFXML("SelectMeet.fxml");
	}

	public void viewData() {
		this.openFXML("SelectMeetToView.fxml");
	}

}
