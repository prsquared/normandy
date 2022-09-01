package org.prsquared.normandy.model;

import org.prsquared.normandy.enums.AttackStyle;
import org.prsquared.normandy.enums.DefenceStyle;
import org.springframework.stereotype.Component;

@Component
public class Team {
    private String name;
    private Integer offence;
    private Integer defence;

    private Float overall;

    private Integer systemCommitment = 0;

    private AttackStyle attackStyle;

    private DefenceStyle defenceStyle;

    public Team(String name, Integer offence, Integer defence, AttackStyle attackStyle, DefenceStyle defenceStyle) {
        this.name = name;
        this.offence = offence;
        this.defence = defence;
        this.overall = (Float.valueOf(offence) + Float.valueOf(defence)) / 2;
        this.attackStyle = attackStyle;
        this.defenceStyle = defenceStyle;
    }

    public Integer getSystemCommitment() {
        return systemCommitment;
    }

    public void setSystemCommitment(Integer systemCommitment) {
        this.systemCommitment = systemCommitment;
    }

    public DefenceStyle getDefenceStyle() {
        return defenceStyle;
    }

    public void setDefenceStyle(DefenceStyle defenceStyle) {
        this.defenceStyle = defenceStyle;
    }

    public AttackStyle getAttackStyle() {
        return attackStyle;
    }

    public void setAttackStyle(AttackStyle attackStyle) {
        this.attackStyle = attackStyle;
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
