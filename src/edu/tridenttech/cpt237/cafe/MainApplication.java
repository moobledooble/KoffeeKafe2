package edu.tridenttech.cpt237.cafe;

import edu.tridenttech.cpt237.cafe.model.Cafe;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Cafe cafe = new Cafe("cafeMenu.txt");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
