package org.prsquared.normandy.model;

import org.prsquared.normandy.enums.AttackStyle;
import org.prsquared.normandy.enums.DefenceStyle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Team {
    private String name;
    private Integer offence;
    private Integer defence;

    private Float overall;

    private Integer systemCommitment = 0;

    private AttackStyle attackStyle;

    private DefenceStyle defenceStyle;

    private List<Player> players = new ArrayList<>();

    public Team(String name, Integer offence, Integer defence, AttackStyle attackStyle, DefenceStyle defenceStyle, Integer systemCommitment) {
        this.name = name;
        this.offence = offence;
        this.defence = defence;
        this.overall = (Float.valueOf(offence) + Float.valueOf(defence)) / 2;
        this.systemCommitment = systemCommitment;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }
}
