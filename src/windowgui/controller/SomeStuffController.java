package windowgui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SomeStuffController {

	@FXML
	private AnchorPane rootPane;

	public void goHome() { openFXML("MainPage.fxml"); }

	protected void openFXML(String file) {

		try { rootPane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("windowgui/fxml/" + file))); }

		catch (Exception e) {
			System.err.println("Cannot open page " + file);
			e.printStackTrace();
		}

	}

}
