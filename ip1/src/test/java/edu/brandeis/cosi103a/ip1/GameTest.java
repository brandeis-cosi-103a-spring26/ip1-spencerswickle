package edu.brandeis.cosi103a.ip1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.brandeis.cosi103a.ip1.Game;
import edu.brandeis.cosi103a.ip1.Player;

public class GameTest {

    @Test
    public void gameProducesWinner() {
        Game game = new Game();
        Player winner = game.play();
        assertNotNull(winner);
    }

    @Test
    public void winnerHasPoints() {
        Game game = new Game();
        Player winner = game.play();
        assertTrue(winner.totalPoints() > 0);
    }
}
