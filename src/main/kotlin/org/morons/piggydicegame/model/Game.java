package org.morons.piggydicegame.model;

public class Game extends Thread {

    // constant declaration
    public static final int MAX_SCORE = 100;

    // data fields
    private final Die d;
    private final Player p1;
    private final Player p2;
    private Player currentPlayer;

    // Constructor
    public Game(String p1name, String p2name) {
        d = new Die();
        p1 = new Player(p1name);
        p2 = new Player(p2name);
        currentPlayer = this.p1;
    }

    // Accessor Methods
    public Die getDie() { return this.d; }
    public Player getCurrentPlayer() { return this.currentPlayer; }
    public Player getP1() { return this.p1; }
    public Player getP2() { return this.p2; }

    // Status Methods
    public boolean gameOver() {
        return currentPlayer.getTotalScore() >= MAX_SCORE;
    }

    public boolean p1Turn() {
        return this.currentPlayer == this.p1;
    }

    // Game Play Methods
    public void switchTurn() {
        if (p1Turn())
            this.currentPlayer = this.p2;
        else
            this.currentPlayer = this.p1;
    }

    public void roll() {
        d.rollDie();
        int t = d.getTop();
        this.currentPlayer.updateTurn(t);
        if (t == 1) {
            this.currentPlayer.resetTurnScore();
            this.switchTurn();
        }
    }

    public void hold() {
        this.currentPlayer.saveScore();
        if (!gameOver()) {
            switchTurn();
            d.setTop(0);
        }
    }
}
