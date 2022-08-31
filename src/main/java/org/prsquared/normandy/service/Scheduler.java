package org.prsquared.normandy.service;

import org.prsquared.normandy.model.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scheduler {
    public static void main(String[] args) {

        // Find out how many teams we want fixtures for.

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

        int teams = teamList.size();
        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                rounds[round][match] = (home + 1) + " v " + (away + 1);
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        String[][] interleaved = new String[totalRounds][matchesPerRound];

        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++];
            } else {
                interleaved[i] = rounds[odd++];
            }
        }

        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }

        // Display the fixtures
        for (int i = 0; i < rounds.length; i++) {
            System.out.println("Round " + (i + 1));
            System.out.println(Arrays.asList(rounds[i]));
            System.out.println();
        }

        System.out.println();

        System.out.println("Use mirror image of these rounds for "
                + "return fixtures.");
    }

    private static String flip(String match) {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }
}
