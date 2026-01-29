package edu.brandeis.cosi103a.ip1;

public class AutomationCard implements Card {
    private final String name;
    private final int cost;
    private final int points;

    public AutomationCard(String name, int cost, int points) {
        this.name = name;
        this.cost = cost;
        this.points = points;
    }

    @Override
    public int getCost() {
        return cost;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }
}
