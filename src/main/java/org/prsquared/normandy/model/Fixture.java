package org.prsquared.normandy.model;

import org.springframework.stereotype.Component;

@Component
public class Fixture {
    private Team homeTeam;
    private Team awayTeam;
    private Result result;

    public Fixture(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Result getResult() {
        return result;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
