package main;

import graphics.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class FXLauncher extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		UserInterface ui = new UserInterface();
		primaryStage.setScene(ui.getScene());
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}
