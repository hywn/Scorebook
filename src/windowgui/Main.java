package windowgui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	private double xOffset = 0, yOffset = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getClassLoader().getResource("windowgui/fxml/MainMenu.fxml"));
			Scene scene = new Scene(root, 600, 600);
			scene.setFill(Color.TRANSPARENT);
			root.setBackground(Background.EMPTY);
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					xOffset = e.getSceneX();
					yOffset = e.getSceneY();
				}
			});
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					primaryStage.setX(e.getScreenX() - xOffset);
					primaryStage.setY(e.getScreenY() - yOffset);
				}
			});
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
