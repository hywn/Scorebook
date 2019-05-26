package windowgui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BaseController {

	@FXML
	private AnchorPane rootPane;

	public void goHome() { openFXML("MainMenu.fxml"); }

	protected void openFXML(String file) {

		try { rootPane.getChildren().setAll((Parent) FXMLLoader.load(getClass().getClassLoader().getResource("windowgui/fxml/" + file))); }

		catch (IOException e) {
			System.err.println("Cannot open page " + file);
			e.printStackTrace();
		}

	}

	protected <E> void openFXML(String file, ControllerModifier<E> cm) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("windowgui/fxml/" + file));

			Parent p = loader.load();

			cm.modify(loader.getController());

			rootPane.getChildren().setAll(p);
		}

		catch (IOException e) {
			System.err.println("Cannot open page " + file);
			e.printStackTrace();
		}

	}

	interface ControllerModifier<E> {
		public void modify(E controller);
	}

}
