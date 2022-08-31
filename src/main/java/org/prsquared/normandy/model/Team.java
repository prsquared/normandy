package org.prsquared.normandy.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class Team {
    private String name;
    private Integer offence;
    private Integer defence;

    private Float overall;

    public Team(String name, Integer offence, Integer defence) {
        this.name = name;
        this.offence = offence;
        this.defence = defence;
        this.overall = (Float.valueOf(offence) + Float.valueOf(defence)) / 2;
    }

    public String getName() {
        return name;
    }

    public Integer getOffence() {
        return offence;
    }

    public Integer getDefence() {
        return defence;
    }

    public Float getOverall() {
        return overall;
    }
}
