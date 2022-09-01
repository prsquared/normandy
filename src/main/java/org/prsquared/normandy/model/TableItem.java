package org.prsquared.normandy.model;

public class TableItem {
    private Team team;

    private Integer gamesPlayed=0;

    private Integer won=0;

    private Integer drawn=0;
    private Integer lost=0;
    private Integer points = 0;
    private Integer goalsScored = 0;
    private Integer goalsConceded = 0;

    private Integer goalDifference = 0;

    public TableItem(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public Integer getWon() {
        return won;
    }

    public void incrementWon() {
        this.won++;
    }

    public Integer getDrawn() {
        return drawn;
    }

    public void incrementDrawn() {
        this.drawn++;
    }

    public Integer getLost() {
        return lost;
    }

    public void incrementLost() {
        this.lost++;
    }

    public Integer getPoints() {
        return points;
    }

    public void addPoints(Integer points) {
        this.points += points;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public void addGoalsScored(Integer goalsScored) {
        this.goalsScored += goalsScored;
    }

    public Integer getGoalsConceded() {
        return goalsConceded;
    }

    public void addGoalsConceded(Integer goalsConceded) {
        this.goalsConceded += goalsConceded;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }
}
