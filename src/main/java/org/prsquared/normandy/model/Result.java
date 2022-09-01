package org.prsquared.normandy.model;

import org.prsquared.normandy.enums.ResultType;
import org.springframework.stereotype.Component;

@Component
public class Result {

    private Team homeTeam;
    private Team awayTeam;
    private String scoreString;
    private Integer homeGoals;
    private Integer awayGoals;
    private ResultType resultType;

    public Result(Team homeTeam, Team awayTeam, Integer homeGoals, Integer awayGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.scoreString = this.homeTeam.getName() + " "
                + this.homeGoals.toString()
                +"-"+this.awayGoals.toString()
                + " "
                + this.awayTeam.getName();
        if(this.homeGoals > this.awayGoals) {
            resultType = ResultType.HOMEWIN;
        } else if(this.awayGoals > this.homeGoals) {
            resultType = ResultType.AWAYWIN;
        } else {
            resultType = ResultType.DRAW;
        }
    }

    public String getScoreString() {
        return scoreString;
    }
    public ResultType getResultType() {
        return resultType;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }
}
