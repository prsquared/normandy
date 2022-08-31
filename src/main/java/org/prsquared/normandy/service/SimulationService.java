package org.prsquared.normandy.service;

import org.prsquared.normandy.model.*;

import java.util.ArrayList;
import java.util.List;

public class SimulationService {
    public List<Team> populateTeams() {
        List<Team> teamList = new ArrayList<>();
        teamList.add(new Team("Arsenal", 80,76));
        teamList.add(new Team("Manchester City", 86,86));
        teamList.add(new Team("Leeds United", 78,70));
        teamList.add(new Team("Tottenham", 82,82));
        teamList.add(new Team("Brighton", 78,78));
        teamList.add(new Team("Newcastle", 76,76));
        teamList.add(new Team("Fulham", 74,74));
        teamList.add(new Team("Brentford", 78,76));
        teamList.add(new Team("Crystal Palace", 76,76));
        teamList.add(new Team("Nottingham Forest", 80,76));
        teamList.add(new Team("Southampton", 80,76));
        teamList.add(new Team("Chelsea", 82,73));
        teamList.add(new Team("Aston Villa", 75,76));
        teamList.add(new Team("Manchester United", 80,78));
        teamList.add(new Team("Bournemouth", 75,75));
        teamList.add(new Team("Liverpool", 85,85));
        teamList.add(new Team("Everton", 75,75));
        teamList.add(new Team("Wolves", 75,76));
        teamList.add(new Team("Leicester City", 77,77));
        teamList.add(new Team("West Ham", 76,76));
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

    public void simulate() throws InterruptedException {
        List<Team> teams = populateTeams();
        Schedule schedule = createSchedule(teams);
        Table table = createTable(teams);
        //Update table based on results
        MatchEngine engine = new MatchEngine();
        int i = 0;
        for(Round round: schedule.getRounds()) {
            System.out.println("Round "+ ++i + "Results");
            for(Fixture fixture: round.getFixtures()) {
                engine.resetInstanceVars();
                Result result = engine.playGame(fixture.getHomeTeam(),fixture.getAwayTeam());
                fixture.setResult(result);
                System.out.println(result.getScoreString());
            }
        }
    }
}
