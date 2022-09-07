package org.prsquared.normandy.model;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Round {
    private List<Fixture> fixtures;

    public Round(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}
