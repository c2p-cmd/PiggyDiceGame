package org.morons.piggydicegame.model;

public class Player {

    // data fields
    private final String name;
    private int turnScore;
    private int totalScore;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.turnScore = 0;
        this.totalScore = 0;
    }

    // Accessor Methods
    public String getName() { return name; }
    public int getTurnScore() { return turnScore; }
    public int getTotalScore() { return totalScore; }

    // Game Play Methods
    public void resetTurnScore() {
        this.turnScore = 0;
    }

    public void updateTurn(int roll) {
        this.turnScore += roll;
    }

    public void saveScore() {
        this.totalScore += this.turnScore;
        resetTurnScore();
    }
}
