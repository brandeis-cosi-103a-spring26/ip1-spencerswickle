package edu.brandeis.cosi103a.ip1;

import java.util.Random;

public class Game {
    private final Supply supply = new Supply();
    private final Player p1 = new Player(supply);
    private final Player p2 = new Player(supply);
    private Player current;

    public Game() {
        current = new Random().nextBoolean() ? p1 : p2;
    }

    public Player play() {
        while (supply.remaining("Framework") > 0) {
            takeTurn(current);
            current = (current == p1) ? p2 : p1;
        }
        return p1.totalPoints() >= p2.totalPoints() ? p1 : p2;
    }

    private void takeTurn(Player p) {
        int money = p.playCryptos();
        p.buyCard(supply, money);
        p.cleanup();
    }
}
