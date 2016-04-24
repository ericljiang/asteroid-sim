package graphics;

import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import utility.FileIO;

public class UserInterface {
	private Pane myRoot;
	private Scene myScene;
	private FXView myView;
	
	public UserInterface() {
		myRoot = new Pane();
		myRoot.setPrefSize(400, 400);
		myScene = new Scene(myRoot);
		
		myView = new FXView();
		myView.relocate(200, 200);
		myRoot.getChildren().add(myView);
		
		load();
		myView.start();
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	public void load() {
		try {
			myView.setSystem(FileIO.readCSV("configs/solarsystem.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
