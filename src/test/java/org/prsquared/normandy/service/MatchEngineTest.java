package org.prsquared.normandy.service;

import org.junit.jupiter.api.Test;
import org.prsquared.normandy.enums.ResultType;
import org.prsquared.normandy.model.Result;
import org.prsquared.normandy.model.Team;

import static org.junit.jupiter.api.Assertions.*;


class MatchEngineTest {

    @Test
    void TestPlayGame() {
        MatchEngine engine =  new MatchEngine();
        engine.isTest = true;
        Team homeTeam = new Team("A",86,86);
        Team awayTeam = new Team("B",86,86);
        int homeWinCount = 0;
        int awayWinCount = 0;
        int drawCount = 0;
        for (int i = 0; i < 100; i ++) {
            engine.resetInstanceVars();
            Result result = engine.playGame(homeTeam,awayTeam);
            System.out.println("Test "+i+", result :" + result.getScoreString());
            if(ResultType.HOMEWIN.equals(result.getResultType())) {
                homeWinCount++;
            } else if (ResultType.AWAYWIN.equals(result.getResultType())) {
                awayWinCount++;
            } else {
                drawCount++;
            }
        }
        System.out.println("Home wins:" + homeWinCount);
        System.out.println("Away wins:" + awayWinCount);
        System.out.println("Draws:" + drawCount);
    }
}