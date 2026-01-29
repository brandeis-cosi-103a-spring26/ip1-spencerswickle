package edu.brandeis.cosi103a.ip1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Supply {
    private final Map<String, Deque<Card>> piles = new HashMap<>();

    public Supply() {
        addPile("Bitcoin", 60, new CryptoCard("Bitcoin", 0, 1));
        addPile("Ethereum", 40, new CryptoCard("Ethereum", 3, 2));
        addPile("Dogecoin", 30, new CryptoCard("Dogecoin", 6, 3));

        addPile("Method", 14, new AutomationCard("Method", 2, 1));
        addPile("Module", 8, new AutomationCard("Module", 5, 3));
        addPile("Framework", 8, new AutomationCard("Framework", 8, 6));
    }

    private void addPile(String name, int count, Card card) {
        Deque<Card> pile = new ArrayDeque<>();
        for (int i = 0; i < count; i++) {
            pile.add(card);
        }
        piles.put(name, pile);
    }

    public boolean hasCard(String name) {
        return piles.containsKey(name) && !piles.get(name).isEmpty();
    }

    public Card buy(String name) {
        return piles.get(name).poll();
    }

    public int remaining(String name) {
        return piles.get(name).size();
    }
}
