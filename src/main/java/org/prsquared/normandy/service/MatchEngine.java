package org.prsquared.normandy.service;

import org.prsquared.normandy.enums.IncidentType;
import org.prsquared.normandy.model.Result;
import org.prsquared.normandy.model.Team;

import java.util.Random;

public class MatchEngine {

    public boolean isTest = false;

    private int homeAttackChance;
    private int awayAttackChance;
    private int homeTeamModifier;
    private int awayTeamModifier;
    public void resetInstanceVars() {
        homeAttackChance = 0;
        awayAttackChance = 0;
        homeTeamModifier = 0;
        awayTeamModifier = 0;
    }
    public  Result playGame(Team home, Team away) {
        int homeGoals = 0;
        int awayGoals = 0;
        homeAttackChance = (home.getOffence() - away.getDefence())/4;
        homeAttackChance = homeAttackChance <= 0 ? 4 : homeAttackChance + 4;
        awayAttackChance = (away.getOffence() - home.getDefence())/4;
        awayAttackChance = awayAttackChance <= 0 ? 4 : awayAttackChance + 4;
        homeTeamModifier = homeAttackChance + IncidentType.HOMEGOALTHREAT.getIncidentChance() + 1;
        awayTeamModifier = homeTeamModifier + awayAttackChance + IncidentType.AWAYGOALTHREAT.getIncidentChance();
        System.out.println("Game has started");
        for(int minute = 0; minute < 45; minute++) {
            IncidentType incident = getIncident();
            if(IncidentType.HOMEGOALTHREAT.equals(incident)) {
                if(!isTest)
                System.out.println("Min: "+minute+"- Chance for "+home.getName());
                if(success(home.getOffence())) {
                    if(!isTest)
                    System.out.println("Min: "+minute+"- "+home.getName()+" has scored");
                    homeGoals++;
                }
            } else if(IncidentType.AWAYGOALTHREAT.equals(incident)) {
                if(!isTest)
                System.out.println("Min: "+minute+"- Chance for "+away.getName());
                if(success(away.getOffence())) {
                    if(!isTest)
                    System.out.println("Min: "+minute+"- "+away.getName()+" has scored");
                    awayGoals++;
                }
            }
        }
        for(int minute = 46; minute < 90; minute++) {
            IncidentType incident = getIncident();
            if(IncidentType.HOMEGOALTHREAT.equals(incident)) {
                if(!isTest)
                System.out.println("Min: "+minute+"- Chance for "+home.getName());
                if(success(home.getOffence())) {
                    if(!isTest)
                    System.out.println("Min: "+minute+"- "+home.getName()+" has scored");
                    homeGoals++;
                }
            } else if(IncidentType.AWAYGOALTHREAT.equals(incident)) {
                if(!isTest)
                System.out.println("Min: "+minute+"- Chance for "+away.getName());
                if(success(away.getOffence())) {
                    if(!isTest)
                    System.out.println("Min: "+minute+"- "+away.getName()+" has scored");
                    awayGoals++;
                }
            }
        }
        Result result = new Result(home, away, homeGoals,awayGoals);
        if(!isTest)
        System.out.println("Game has ended with a score of "+ result.getScoreString());


        return result;
    }



    private IncidentType getIncident() {
        Random random = new Random();
        int randNum = random.nextInt(201);

        if(randNum < homeTeamModifier) {
            return IncidentType.HOMEGOALTHREAT;
        } else if(randNum < awayTeamModifier) {
            return IncidentType.AWAYGOALTHREAT;
        }else {
            return IncidentType.NOTHING;
        }
    }

    private static boolean success(int probability) {
        Random random = new Random();
        int randNum = random.nextInt(101);
        if(randNum <= probability - 20) {
            return true;
        }
        return false;
    }
}
