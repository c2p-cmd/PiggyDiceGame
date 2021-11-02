package org.morons.piggydicegame.about;

import javafx.scene.control.Alert;

public class AboutGame implements Runnable{
    @Override
    public void run() {
        System.out.println("Currently running thread: " + Thread.currentThread().getName());

        Alert aboutGame = new Alert(Alert.AlertType.INFORMATION);

        aboutGame.setHeight(275.0);
        aboutGame.setTitle("About Piggy Dice Game.");
        aboutGame.setHeaderText("This is the Piggy Dice Game!");
        aboutGame.setContentText("Piggy Dice Game is 2 player dice game\n" +
                "The aim of the game is to 'roll' a die and 'hold' points to your score\n" +
                "but, beware of the number '1' as rolling it will set your current score to 0."
        );

        aboutGame.show();
    }
}
