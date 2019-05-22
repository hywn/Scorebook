package capstone.scorebook.gui;


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
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("AppLayout.fxml"));
			Scene scene = new Scene(root,600, 600);
			scene.setFill(Color.TRANSPARENT);
			root.setBackground(Background.EMPTY);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.initStyle(StageStyle.TRANSPARENT);
				root.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						xOffset = event.getSceneX();
						yOffset = event.getSceneY();
					}
					
				});
				
				root.setOnMouseDragged(new EventHandler<MouseEvent>() {
					@Override
					public void handle (MouseEvent event) {
						primaryStage.setX(event.getScreenX() - xOffset);
						primaryStage.setY(event.getScreenY() - yOffset);
					}
				});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
