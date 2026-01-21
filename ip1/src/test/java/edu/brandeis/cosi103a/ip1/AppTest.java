package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;
import org.junit.Test;

/**
 * Unit tests for the DiceGame
 */
public class AppTest 
{
    /**
     * Test that die roll is within valid range (1-6)
     */
    @Test
    public void testDieRollRange()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        for (int i = 0; i < 100; i++) {
            int roll = game.rollDie();
            assertTrue("Roll should be between 1 and 6", roll >= 1 && roll <= 6);
        }
    }
    
    /**
     * Test that die roll produces different values
     */
    @Test
    public void testDieRollVariety()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        boolean hasOne = false;
        boolean hasSix = false;
        
        for (int i = 0; i < 100; i++) {
            int roll = game.rollDie();
            if (roll == 1) hasOne = true;
            if (roll == 6) hasSix = true;
        }
        
        assertTrue("Die should produce variety of rolls", hasOne && hasSix);
    }
    
    /**
     * Test that player 1 score is initially zero
     */
    @Test
    public void testPlayer1InitialScore()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        assertEquals("Initial score should be 0", 0, game.getPlayer1Score());
    }
    
    /**
     * Test that player 2 score is initially zero
     */
    @Test
    public void testPlayer2InitialScore()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        assertEquals("Initial score should be 0", 0, game.getPlayer2Score());
    }
    
    /**
     * Test adding points to player 1's score
     */
    @Test
    public void testAddPlayer1Score()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        game.addPlayer1Score(4);
        assertEquals("Score should be 4", 4, game.getPlayer1Score());
        
        game.addPlayer1Score(5);
        assertEquals("Score should be 9", 9, game.getPlayer1Score());
    }
    
    /**
     * Test adding points to player 2's score
     */
    @Test
    public void testAddPlayer2Score()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        game.addPlayer2Score(3);
        assertEquals("Score should be 3", 3, game.getPlayer2Score());
        
        game.addPlayer2Score(6);
        assertEquals("Score should be 9", 9, game.getPlayer2Score());
    }
    
    /**
     * Test that invalid scores (less than 1) are rejected
     */
    @Test
    public void testInvalidScoreBelowOne()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        try {
            game.addPlayer1Score(0);
            fail("Should throw IllegalArgumentException for score 0");
        } catch (IllegalArgumentException e) {
            assertTrue("Error message should mention valid range", 
                e.getMessage().contains("between 1 and"));
        }
    }
    
    /**
     * Test that invalid scores (greater than 6) are rejected
     */
    @Test
    public void testInvalidScoreAboveSix()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        try {
            game.addPlayer2Score(7);
            fail("Should throw IllegalArgumentException for score 7");
        } catch (IllegalArgumentException e) {
            assertTrue("Error message should mention valid range", 
                e.getMessage().contains("between 1 and"));
        }
    }
    
    /**
     * Test that player 1 wins when score is higher
     */
    @Test
    public void testPlayer1Wins()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        game.addPlayer1Score(5);
        game.addPlayer1Score(4);
        game.addPlayer2Score(3);
        game.addPlayer2Score(2);
        
        assertEquals("Player 1 should win", 1, game.determineWinner());
    }
    
    /**
     * Test that player 2 wins when score is higher
     */
    @Test
    public void testPlayer2Wins()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        game.addPlayer1Score(2);
        game.addPlayer2Score(3);
        game.addPlayer2Score(4);
        
        assertEquals("Player 2 should win", 2, game.determineWinner());
    }
    
    /**
     * Test that tie is detected when scores are equal
     */
    @Test
    public void testTie()
    {
        DiceGame game = new DiceGame("Player1", "Player2");
        game.addPlayer1Score(5);
        game.addPlayer1Score(3);
        game.addPlayer2Score(4);
        game.addPlayer2Score(4);
        
        assertEquals("Scores are equal, should be a tie", 0, game.determineWinner());
    }
    
    /**
     * Test that player names are stored correctly
     */
    @Test
    public void testPlayerNames()
    {
        DiceGame game = new DiceGame("Alice", "Bob");
        assertEquals("Player 1 name should be Alice", "Alice", game.getPlayer1Name());
        assertEquals("Player 2 name should be Bob", "Bob", game.getPlayer2Name());
    }
    
    /**
     * Test that constants are correct
     */
    @Test
    public void testGameConstants()
    {
        assertEquals("Number of turns should be 10", 10, DiceGame.getNumTurns());
        assertEquals("Max rerolls should be 2", 2, DiceGame.getMaxRerolls());
    }
    
    /**
     * Test game with mocked random for deterministic results
     */
    @Test
    public void testGameWithMockedRandom()
    {
        // Create a mock random that always returns 3
        Random mockRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return 2; // nextInt(6) with 2 means die value is 3 (2+1)
            }
        };
        
        DiceGame game = new DiceGame("Player1", "Player2", mockRandom);
        assertEquals("Die should always roll 3", 3, game.rollDie());
        assertEquals("Die should always roll 3", 3, game.rollDie());
    }
}

