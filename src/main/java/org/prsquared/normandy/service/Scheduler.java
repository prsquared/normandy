package org.prsquared.normandy.service;

import org.prsquared.normandy.enums.AttackStyle;
import org.prsquared.normandy.enums.DefenceStyle;
import org.prsquared.normandy.model.Fixture;
import org.prsquared.normandy.model.Round;
import org.prsquared.normandy.model.Schedule;
import org.prsquared.normandy.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    public static void main(String[] args) {

        // Find out how many teams we want fixtures for.

        List<Team> teamList = new ArrayList<>();
        teamList.add(new Team("Arsenal", 80,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Manchester City", 86,86, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Leeds United", 78,70, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Tottenham", 82,82, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Brighton", 78,78, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Newcastle", 76,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Fulham", 74,74, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Brentford", 78,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Crystal Palace", 76,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Nottingham Forest", 80,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Southampton", 80,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Chelsea", 82,73, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Aston Villa", 75,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Manchester United", 80,78, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Bournemouth", 75,75, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Liverpool", 85,85, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Everton", 75,75, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Wolves", 75,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("Leicester City", 77,77, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));
        teamList.add(new Team("West Ham", 76,76, AttackStyle.COUNTER_ATTACK, DefenceStyle.LOW_BLOCK, 100));

        int teams = teamList.size();
        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];
        List<Round> roundList = new ArrayList<>();
        for (int round = 0; round < totalRounds; round++) {
            List<Fixture> fixtureList = new ArrayList<>();
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                Fixture fixture = new Fixture(teamList.get(home),teamList.get(away));
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                fixtureList.add(fixture);
            }
            roundList.add(new Round(fixtureList));
        }
        Schedule schedule = new Schedule(roundList);
        // Interleave so that home and away games are fairly evenly dispersed.
        String[][] interleaved = new String[totalRounds][ ];
        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                Collections.swap(roundList,i,evn++);
            } else {
                Collections.swap(roundList,i,odd++);
            }
        }

        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {

                Round rnd = roundList.get(round);
                flip(rnd.getFixtures().get(0));
                //rounds[round][0] = flip(rounds[round][0]);
            }
        }

        // Display the fixtures
        int i =0;
        for (Round round: roundList) {
            System.out.println("Round " + (++i));
            System.out.println("--------");
            for(Fixture fixture: round.getFixtures()) {
                System.out.println(fixture.getHomeTeam().getName() + " v " + fixture.getAwayTeam().getName());
            }
            System.out.println();
            System.out.println();
        }

        System.out.println();

        System.out.println("Use mirror image of these rounds for "
                + "return fixtures.");
    }

    public Schedule createSchedule(List<Team> teamList) {
        int teams = teamList.size();
        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];
        List<Round> roundList = new ArrayList<>();
        for (int round = 0; round < totalRounds; round++) {
            List<Fixture> fixtureList = new ArrayList<>();
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                Fixture fixture = new Fixture(teamList.get(home),teamList.get(away));
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                fixtureList.add(fixture);
            }
            roundList.add(new Round(fixtureList));
        }
        Schedule schedule = new Schedule(roundList);
        // Interleave so that home and away games are fairly evenly dispersed.
        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                Collections.swap(roundList,i,evn++);
            } else {
                Collections.swap(roundList,i,odd++);
            }
        }
        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {

                Round rnd = roundList.get(round);
                flip(rnd.getFixtures().get(0));
            }
        }
        List<Round>  returnRounds = new ArrayList<>();
        for(Round round: schedule.getRounds()) {
            List<Fixture> reverseList = new ArrayList<>();
            for(Fixture fixture: round.getFixtures()) {
                reverseList.add(reverse(fixture));
            }
            Round reverse = new Round(reverseList);
            returnRounds.add(reverse);
        }
        schedule.getRounds().addAll(returnRounds);
        return schedule;
    }

    private static void flip(Fixture fixture) {
        Team temp = fixture.getHomeTeam();
        fixture.setHomeTeam(fixture.getAwayTeam()) ;
        fixture.setAwayTeam(temp);
    }

    private Fixture reverse(Fixture fixture) {
        return new Fixture(fixture.getAwayTeam(),fixture.getHomeTeam());
    }
}
