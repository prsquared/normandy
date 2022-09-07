package org.prsquared.normandy.model;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Schedule {
    private List<Round> rounds;

    public Schedule(List<Round> rounds) {
        this.rounds = rounds;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}
