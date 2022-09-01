package org.prsquared.normandy.service;

import org.prsquared.normandy.constants.Colors;
import org.prsquared.normandy.enums.IncidentType;
import org.prsquared.normandy.model.Result;
import org.prsquared.normandy.model.Team;

import java.util.Random;

public class MatchEngine {

    private static final int OFFENSE_PENALTY = 35;
    private static final int WAIT_TIME = 200;
    public boolean isTest = false;

    public boolean fastMode = false;

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
    public  Result playGame(Team home, Team away) throws InterruptedException {
        int homeGoals = 0;
        int awayGoals = 0;
        homeAttackChance = calculateAttackChance(home.getOffence(),away.getDefence());
        awayAttackChance = calculateAttackChance(away.getOffence(),home.getDefence());
        homeTeamModifier = homeAttackChance + IncidentType.HOMEGOALTHREAT.getIncidentChance();
        awayTeamModifier = homeTeamModifier + awayAttackChance + IncidentType.AWAYGOALTHREAT.getIncidentChance();
        System.out.println(Colors.ANSI_BLUE+home.getName()+" v "+away.getName()+Colors.ANSI_RESET);
        for(int minute = 0; minute < 45; minute++) {
            IncidentType incident = getIncident();
            if(IncidentType.HOMEGOALTHREAT.equals(incident)) {
                if(!isTest && !fastMode) {
                    Thread.sleep(WAIT_TIME);
                    System.out.println("Min: " + minute + "- Chance for " + home.getName());
                }
                if(success(home.getOffence(), away.getDefence())) {
                    if(!isTest && !fastMode) {
                        Thread.sleep(WAIT_TIME);
                        System.out.println(Colors.ANSI_RED+"----- " + home.getName() + " has scored"+Colors.ANSI_RESET);
                    }
                    homeGoals++;
                } else {
                    if(!isTest && !fastMode) {
                        Thread.sleep(WAIT_TIME);
                        System.out.println("----- Missed opportunity to score");
                    }
                }
            } else if(IncidentType.AWAYGOALTHREAT.equals(incident)) {
                if(!isTest && !fastMode) {
                    Thread.sleep(WAIT_TIME);
                    System.out.println("Min: " + minute + "- Chance for " + away.getName());
                }
                if(success(away.getOffence(), home.getDefence())) {
                    if(!isTest && !fastMode) {
                        Thread.sleep(WAIT_TIME);
                        System.out.println(Colors.ANSI_RED+"----- " + away.getName() + " has scored"+Colors.ANSI_RESET);
                    }
                    awayGoals++;
                } else {
                    if(!isTest && !fastMode) {
                        Thread.sleep(WAIT_TIME);
                        System.out.println("----- Missed opportunity to score");
                    }
                }
            }
        }
        for(int minute = 46; minute < 90; minute++) {
            IncidentType incident = getIncident();
            if(IncidentType.HOMEGOALTHREAT.equals(incident)) {
                if(!isTest && !fastMode) {
                    Thread.sleep(WAIT_TIME);
                    System.out.println("Min: " + minute + "- Chance for " + home.getName());
                }
                if(success(home.getOffence(), away.getDefence())) {
                    if(!isTest && !fastMode) {
                        Thread.sleep(WAIT_TIME);
                        System.out.println(Colors.ANSI_RED+"------- " + home.getName() + " has scored"+Colors.ANSI_RESET);
                    }
                    homeGoals++;
                } else {
                    if(!isTest && !fastMode) {
                        Thread.sleep(WAIT_TIME);
                        System.out.println("----- Missed opportunity to score");
                    }
                }
            } else if(IncidentType.AWAYGOALTHREAT.equals(incident)) {
                if(!isTest && !fastMode) {
                    Thread.sleep(WAIT_TIME);
                    System.out.println("Min: " + minute + "- Chance for " + away.getName());
                }
                if(success(away.getOffence(), home.getDefence())) {
                    if(!isTest && !fastMode) {
                        Thread.sleep(WAIT_TIME);
                        System.out.println(Colors.ANSI_RED+"------- " + away.getName() + " has scored"+Colors.ANSI_RESET);
                    }
                    awayGoals++;
                } else {
                    if(!isTest && !fastMode) {
                        Thread.sleep(WAIT_TIME);
                        System.out.println("----- Missed opportunity to score");
                    }
                }
            }
        }
        Result result = new Result(home, away, homeGoals,awayGoals);
        if(!isTest ) {
            if(!fastMode) {
                Thread.sleep(WAIT_TIME);
            }
            System.out.println(Colors.ANSI_BLUE+"Game has ended with a score of " + result.getScoreString()+Colors.ANSI_RESET);
        }


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

    private static boolean success(int offence, int defence) {
        Random random = new Random();
        int randNum = random.nextInt(101);
        if(randNum <= offence - (OFFENSE_PENALTY - (offence - defence))) {
            return true;
        }
        return false;
    }

    private static int calculateAttackChance(int attack, int defence) {
        int chance = (attack - defence)/4;
        if(chance < -5) {
            chance = 1;
        } else if (chance < 5) {
            chance += 5;
        } else {
            chance += 6;
        }
        return chance;
    }
}
