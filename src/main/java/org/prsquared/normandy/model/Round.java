package org.prsquared.normandy.model;

import java.util.List;

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
