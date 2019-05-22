package capstone.scorebook.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class AppController {
		@FXML
		private Button registerButton, exitButton;
		@FXML
		private AnchorPane athleteRegisterPane, mainMenuPane;
		
		
		public void exit (ActionEvent e) {
			System.exit(0);
		}
		
		public void registerAthletes(ActionEvent e) {
			// Change scene
			
		}

		
		
		
	}
