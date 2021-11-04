package org.morons.piggydicegame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class PigGameApplication extends Application implements Runnable {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                PigGameApplication.class.getResource("game.fxml")
        );

        Scene scene = new Scene(
                fxmlLoader.load(),
                500.0,
                300.0
        );

        scene.getStylesheets().addAll(
                Objects.requireNonNull(
                        PigGameApplication.class.getResource("PiggyMenuStyle.css")
                ).toExternalForm(),
                Objects.requireNonNull(
                        PigGameApplication.class.getResource("PiggyApplicationStyle.css")
                ).toExternalForm(),
                Objects.requireNonNull(
                        PigGameApplication.class.getResource("PiggyButtonStyle.css")
                ).toExternalForm()
        );

        stage.setTitle("Piggy Dice Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(
                new Image(
                        Objects.requireNonNull(
                                PigGameApplication.class.getResourceAsStream("PigImage/PigIcon.png")
                        )
                )
        );

        stage.show();
    }

    public static void main(String[] args) {
        new Thread(
                new PigGameApplication(),
                "Piggy Game Thread"
        ).start();
    }

    public static void quitAction() {
        // Exiting JavaFX
        Platform.exit();

        // Exiting JVM
        System.exit(0);
    }

    @Override
    public void run() {
        System.out.println("Currently running thread: " + Thread.currentThread().getName());
        Application.launch(PigGameApplication.class);
    }
}

