package org.morons.piggydicegame.model;

import kotlin.Pair;

public class Game {

    // constant declaration
    public static final int MAX_SCORE = 100;

    // data fields
    private final Die d;
    private final Player p1;
    private final Player p2;
    private Player currentPlayer;
    private Game game;

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
    public boolean isGameOver() {
        return currentPlayer.getTotalScore() >= MAX_SCORE;
    }

    public boolean isP1Turn() {
        return this.getCurrentPlayer() == this.getP1();
    }

    // Game Play Methods
    public void switchTurn() {
        if (isP1Turn())
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
        if (!isGameOver()) {
            switchTurn();
            d.setTop(0);
        }
    }

    public Pair<
            Pair<Integer, Integer>,
            Pair<Integer, Integer>
            >
    getPlayerScores() {
        // creating pair for players
        Pair<Integer, Integer> player1 = new
                Pair<>(
                        getP1().getTurnScore(),
                        getP1().getTotalScore()
                );
        Pair<Integer, Integer> player2 = new
                Pair<>(
                        getP2().getTurnScore(),
                        getP2().getTotalScore()
                );

        return new Pair<>(player1, player2);
    }
}
