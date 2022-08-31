package org.prsquared.normandy.model;

import java.util.List;

public class Schedule {
    private List<Fixture> rounds;

    public Schedule(List<Fixture> rounds) {
        this.rounds = rounds;
    }

    public List<Fixture> getRounds() {
        return rounds;
    }

    public void setRounds(List<Fixture> rounds) {
        this.rounds = rounds;
    }
}
