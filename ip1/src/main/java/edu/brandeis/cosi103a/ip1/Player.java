package edu.brandeis.cosi103a.ip1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class Player {
    private final Deque<Card> drawPile = new ArrayDeque<>();
    private final Deque<Card> discardPile = new ArrayDeque<>();
    private final List<Card> hand = new ArrayList<>();
    private final List<Card> played = new ArrayList<>();

    public Player(Supply supply) {
        for (int i = 0; i < 7; i++) discardPile.add(supply.buy("Bitcoin"));
        for (int i = 0; i < 3; i++) discardPile.add(supply.buy("Method"));
        shuffleDiscardIntoDraw();
        drawHand();
    }

    private void shuffleDiscardIntoDraw() {
        List<Card> temp = new ArrayList<>(discardPile);
        Collections.shuffle(temp);
        drawPile.addAll(temp);
        discardPile.clear();
    }

    public void drawHand() {
        while (hand.size() < 5) {
            if (drawPile.isEmpty()) shuffleDiscardIntoDraw();
            if (drawPile.isEmpty()) break;
            hand.add(drawPile.poll());
        }
    }

    public int playCryptos() {
        int money = 0;
        Iterator<Card> it = hand.iterator();
        while (it.hasNext()) {
            Card c = it.next();
            if (c instanceof CryptoCard crypto) {
                money += crypto.getValue();
                played.add(c);
                it.remove();
            }
        }
        return money;
    }

    public void buyCard(Supply supply, int money) {
        if (money >= 8 && supply.hasCard("Framework")) {
            discardPile.add(supply.buy("Framework"));
        } else if (money >= 5 && supply.hasCard("Module")) {
            discardPile.add(supply.buy("Module"));
        } else if (money >= 3 && supply.hasCard("Ethereum")) {
            discardPile.add(supply.buy("Ethereum"));
        } else if (money >= 2 && supply.hasCard("Method")) {
            discardPile.add(supply.buy("Method"));
        }
    }

    public void cleanup() {
        discardPile.addAll(hand);
        discardPile.addAll(played);
        hand.clear();
        played.clear();
        drawHand();
    }

    public int totalPoints() {
        int points = 0;
        for (Card c : allCards()) {
            if (c instanceof AutomationCard a) {
                points += a.getPoints();
            }
        }
        return points;
    }

    private List<Card> allCards() {
        List<Card> all = new ArrayList<>();
        all.addAll(drawPile);
        all.addAll(discardPile);
        all.addAll(hand);
        all.addAll(played);
        return all;
    }
}
