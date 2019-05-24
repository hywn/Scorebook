package windowgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SomeStuffController {

	@FXML
	private AnchorPane rootPane;

	public void goHome() throws IOException {
		openFXML("MainPage.fxml");
	}

	protected void openFXML(String file) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader
					.load(getClass().getClassLoader().getResource("windowgui/" + file));
			rootPane.getChildren().setAll(pane);
		}

		catch (Exception e) {
			System.err.println("Cannot open page " + file);
			e.printStackTrace();
		}

	}

}
