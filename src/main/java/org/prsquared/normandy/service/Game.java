package org.prsquared.normandy.service;

import org.prsquared.normandy.model.Result;
import org.prsquared.normandy.model.Team;
import org.springframework.stereotype.Service;

@Service
public class Game {

    private Result result;

    public Game() {
    }

    public Result play() {
        MatchEngine engine = new MatchEngine();
        Team homeTeam = new Team("Manchester United",85,85);
        Team awayTeam = new Team("Manchester City",86,86);

        try {
            return engine.playGame(homeTeam,awayTeam);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
