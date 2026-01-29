package edu.brandeis.cosi103a.ip1;

public class CryptoCard implements Card {
    private final String name;
    private final int cost;
    private final int value;

    public CryptoCard(String name, int cost, int value) {
        this.name = name;
        this.cost = cost;
        this.value = value;
    }

    @Override
    public int getCost() {
        return cost;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}

