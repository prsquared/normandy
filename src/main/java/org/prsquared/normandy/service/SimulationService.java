package org.prsquared.normandy.service;

import org.prsquared.normandy.constants.Colors;
import org.prsquared.normandy.enums.AttackStyle;
import org.prsquared.normandy.enums.DefenceStyle;
import org.prsquared.normandy.enums.ResultType;
import org.prsquared.normandy.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimulationService {

    public boolean fastMode = false;
    public boolean isTest = false;

    MatchEngine matchEngine = new MatchEngine();
    public List<Team> populateTeams() {
        List<Team> teamList = new ArrayList<>();
        teamList.add(new Team("Arsenal", 80,76, AttackStyle.POSSESSION, DefenceStyle.NEUTRAL, 100));
        teamList.add(new Team("Manchester City", 86,86, AttackStyle.POSSESSION, DefenceStyle.HIGH_PRESS, 100));
        teamList.add(new Team("Leeds United", 78,70, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Tottenham", 82,82, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Brighton", 78,78, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Newcastle", 76,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Fulham", 74,74, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Brentford", 78,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Crystal Palace", 76,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Nottingham Forest", 80,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Southampton", 80,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Chelsea", 82,77, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Aston Villa", 75,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Manchester United", 82,84, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Bournemouth", 75,75, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Liverpool", 85,85, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Everton", 75,75, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Wolves", 75,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Leicester City", 80,80, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("West Ham", 76,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        Collections.shuffle(teamList);
        return teamList;
    }

    public Table createTable(List<Team> teamList) {
        List<TableItem> tableItemList = new ArrayList<>();
        for(Team team: teamList) {
            TableItem tableItem = new TableItem(team);
            tableItemList.add(tableItem);
        }
        return new Table(tableItemList);
    }
    public Schedule createSchedule(List<Team> teamList) {

        Scheduler scheduler = new Scheduler();
        return scheduler.createSchedule(teamList);
    }

    public Table simulate() throws InterruptedException {
        List<Team> teams = populateTeams();
        Schedule schedule = createSchedule(teams);
        Table table = createTable(teams);
        //Update table based on results
        MatchEngine engine = new MatchEngine();
        engine.fastMode = this.fastMode;
        engine.isTest = this.isTest;
        int i = 0;
        for(Round round: schedule.getRounds()) {
            System.out.println("Round "+ ++i + "Results");
            for(Fixture fixture: round.getFixtures()) {
                engine.resetInstanceVars();
                Result result = engine.playGame(fixture.getHomeTeam(),fixture.getAwayTeam());
                TableItem homeTeamItem = table.getTableItemByTeam(fixture.getHomeTeam());
                TableItem awayTeamItem = table.getTableItemByTeam(fixture.getAwayTeam());
                homeTeamItem.incrementGamesPlayed();
                awayTeamItem.incrementGamesPlayed();
                if(result.getResultType().equals(ResultType.HOMEWIN)) {
                    homeTeamItem.addPoints(3);
                    homeTeamItem.incrementWon();
                    awayTeamItem.incrementLost();
                } else if (result.getResultType().equals(ResultType.AWAYWIN)) {
                    awayTeamItem.addPoints(3);
                    awayTeamItem.incrementWon();
                    homeTeamItem.incrementLost();
                } else {
                    homeTeamItem.addPoints(1);
                    awayTeamItem.addPoints(1);
                    homeTeamItem.incrementDrawn();
                    awayTeamItem.incrementDrawn();
                }
                homeTeamItem.addGoalsScored(result.getHomeGoals());
                homeTeamItem.addGoalsConceded(result.getAwayGoals());
                awayTeamItem.addGoalsScored(result.getAwayGoals());
                awayTeamItem.addGoalsConceded(result.getHomeGoals());
                homeTeamItem.setGoalDifference(homeTeamItem.getGoalsScored() - homeTeamItem.getGoalsConceded());
                awayTeamItem.setGoalDifference(awayTeamItem.getGoalsScored() - awayTeamItem.getGoalsConceded());
                fixture.setResult(result);
                System.out.println(result.getScoreString());
            }
            System.out.println(Colors.ANSI_PURPLE+"Team"+getSpaces(20,4)+"Played  Won  Drawn  Lost  GS     GC     GD    Points");
            System.out.println("------------------------------------------------------------------------"+Colors.ANSI_RESET);
            Comparator<TableItem> compareByPoints = Comparator.comparing(TableItem::getPoints)
                                                                .thenComparingInt(TableItem::getGoalDifference)
                                                                        .thenComparingInt(TableItem::getGoalsScored).reversed();

            table.getTableItemList().stream()
                    .sorted(compareByPoints)
                    .forEach( t -> {
                        System.out.println(t.getTeam().getName() +getSpaces(20, t.getTeam().getName().length())+t.getGamesPlayed()+getSpaces(7,String.valueOf(t.getGamesPlayed()).length())
                                +t.getWon()+getSpaces(6,String.valueOf(t.getWon()).length())
                                +t.getDrawn()+getSpaces(7,String.valueOf(t.getDrawn()).length())
                                +t.getLost()+getSpaces(6,String.valueOf(t.getLost()).length())
                                +t.getGoalsScored()+getSpaces(7,String.valueOf(t.getGoalsScored()).length())
                                +t.getGoalsConceded()+getSpaces(7,String.valueOf(t.getGoalsConceded()).length())
                                +t.getGoalDifference()+getSpaces(7,String.valueOf(t.getGoalDifference()).length())
                                +t.getPoints());
                    });
        }
        return table;
    }

    private String getSpaces(int maxspace, int stringLength) {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<maxspace-stringLength;i++){
            builder.append(" ");
        }
        return builder.toString();
    }
}
