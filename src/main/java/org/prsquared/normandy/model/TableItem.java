package org.prsquared.normandy.model;

public class TableItem {
    private Team team;
    private int points = 0;
    private int goalsScored = 0;
    private int goalsConceded = 0;

    public TableItem(Team team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void addGoalsScored(int goalsScored) {
        this.goalsScored += goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void addGoalsConceded(int goalsConceded) {
        this.goalsConceded += goalsConceded;
    }
}
