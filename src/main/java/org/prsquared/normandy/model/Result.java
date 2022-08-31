package org.prsquared.normandy.model;

import org.prsquared.normandy.enums.ResultType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class Result {

    private Team homeTeam;
    private Team awaTeam;
    private String scoreString;
    private Integer homeGoals;
    private Integer awayGoals;
    private ResultType resultType;

    public Result(Team homeTeam, Team awaTeam, Integer homeGoals, Integer awayGoals) {
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.scoreString = this.homeGoals.toString()+"-"+this.awayGoals.toString();
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
}
