package org.morons.piggydicegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PigGameApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                PigGameApplication.class.getResource("game.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 500.0, 300.0);

        stage.setTitle("Piggy Dice Game.");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(PigGameApplication.class);
    }
}
