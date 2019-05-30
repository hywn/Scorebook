package windowgui.controller;

import capstone.scorebook.data.concrete.Meet;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import windowgui.Util;

public class SelectMeetToViewController extends BaseController {

	@FXML
	private ListView<Meet> listMeets;

	@FXML
	private void initialize() {

		listMeets.getItems().setAll(Util.getSortedMeets());
		listMeets.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		listMeets.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) enterData();
		});

	}

	public void enterData() {
		this.<ViewDataController>openFXML("ViewData.fxml", controller -> controller.setMeets(listMeets.getSelectionModel().getSelectedItems()));
	}

}
