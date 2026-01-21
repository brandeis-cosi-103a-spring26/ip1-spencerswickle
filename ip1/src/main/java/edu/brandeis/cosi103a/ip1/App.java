package edu.brandeis.cosi103a.ip1;

import java.util.Random;
import java.util.Scanner;

/**
 * A two-player dice game where players roll a die, can re-roll up to 2 times,
 * and accumulate scores over 10 turns each.
 */
public class App 
{
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String player1Name = getPlayerName(1);
        String player2Name = getPlayerName(2);
        
        DiceGame game = new DiceGame(player1Name, player2Name);
        
        System.out.println("\n========== DICE GAME START ==========\n");
        
        // Play 10 turns for each player
        for (int turn = 1; turn <= DiceGame.getNumTurns(); turn++) {
            System.out.println("--- TURN " + turn + " ---");
            
            // Player 1's turn
            System.out.println("\n" + player1Name + "'s turn:");
            int points1 = playTurn(game, player1Name);
            game.addPlayer1Score(points1);
            System.out.println(player1Name + " earned " + points1 + " points. Total: " + game.getPlayer1Score());
            
            // Player 2's turn
            System.out.println("\n" + player2Name + "'s turn:");
            int points2 = playTurn(game, player2Name);
            game.addPlayer2Score(points2);
            System.out.println(player2Name + " earned " + points2 + " points. Total: " + game.getPlayer2Score());
            
            System.out.println();
        }
        
        // Determine winner
        System.out.println("========== GAME OVER ==========");
        System.out.println(player1Name + " final score: " + game.getPlayer1Score());
        System.out.println(player2Name + " final score: " + game.getPlayer2Score());
        System.out.println();
        
        int winner = game.determineWinner();
        if (winner == 1) {
            System.out.println("🎉 " + player1Name + " wins!");
        } else if (winner == 2) {
            System.out.println("🎉 " + player2Name + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
        
        scanner.close();
    }
    
    /**
     * Gets the name of a player from command line input
     */
    private static String getPlayerName(int playerNumber) {
        System.out.print("Enter name for Player " + playerNumber + ": ");
        return scanner.nextLine().trim();
    }
    
    /**
     * Simulates one turn for a player
     * Returns the points earned in this turn
     */
    private static int playTurn(DiceGame game, String playerName) {
        int currentRoll = game.rollDie();
        System.out.println("You rolled: " + currentRoll);
        
        int rerollsUsed = 0;
        
        // Allow up to 2 re-rolls
        while (rerollsUsed < DiceGame.getMaxRerolls()) {
            System.out.print("Do you want to re-roll? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes") || response.equals("y")) {
                currentRoll = game.rollDie();
                System.out.println("You rolled: " + currentRoll);
                rerollsUsed++;
                System.out.println("Re-rolls remaining: " + (DiceGame.getMaxRerolls() - rerollsUsed));
            } else if (response.equals("no") || response.equals("n")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        
        if (rerollsUsed == DiceGame.getMaxRerolls()) {
            System.out.println("You've used all your re-rolls.");
        }
        
        System.out.println("Final roll for this turn: " + currentRoll);
        return currentRoll;
    }
}
