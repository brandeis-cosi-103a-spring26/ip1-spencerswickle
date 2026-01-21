package edu.brandeis.cosi103a.ip1;

import java.util.Random;

/**
 * Represents a two-player dice game with scoring logic
 */
public class DiceGame {
    private static final int NUM_TURNS = 10;
    private static final int MAX_REROLLS = 2;
    private static final int DIE_SIDES = 6;
    
    private Random random;
    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;
    
    public DiceGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Score = 0;
        this.player2Score = 0;
        this.random = new Random();
    }
    
    public DiceGame(String player1Name, String player2Name, Random random) {
        this(player1Name, player2Name);
        this.random = random;
    }
    
    /**
     * Rolls a 6-sided die
     */
    public int rollDie() {
        return random.nextInt(DIE_SIDES) + 1;
    }
    
    /**
     * Adds points to player 1's score
     */
    public void addPlayer1Score(int points) {
        if (points < 1 || points > DIE_SIDES) {
            throw new IllegalArgumentException("Points must be between 1 and " + DIE_SIDES);
        }
        player1Score += points;
    }
    
    /**
     * Adds points to player 2's score
     */
    public void addPlayer2Score(int points) {
        if (points < 1 || points > DIE_SIDES) {
            throw new IllegalArgumentException("Points must be between 1 and " + DIE_SIDES);
        }
        player2Score += points;
    }
    
    /**
     * Gets player 1's current score
     */
    public int getPlayer1Score() {
        return player1Score;
    }
    
    /**
     * Gets player 2's current score
     */
    public int getPlayer2Score() {
        return player2Score;
    }
    
    /**
     * Determines the winner
     * Returns 1 if player 1 wins, 2 if player 2 wins, 0 for tie
     */
    public int determineWinner() {
        if (player1Score > player2Score) {
            return 1;
        } else if (player2Score > player1Score) {
            return 2;
        } else {
            return 0;
        }
    }
    
    /**
     * Gets the number of turns per player
     */
    public static int getNumTurns() {
        return NUM_TURNS;
    }
    
    /**
     * Gets the maximum number of re-rolls allowed
     */
    public static int getMaxRerolls() {
        return MAX_REROLLS;
    }
    
    /**
     * Gets player 1's name
     */
    public String getPlayer1Name() {
        return player1Name;
    }
    
    /**
     * Gets player 2's name
     */
    public String getPlayer2Name() {
        return player2Name;
    }
}
