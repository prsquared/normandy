package org.prsquared.normandy.service;

import org.junit.jupiter.api.Test;
import org.prsquared.normandy.enums.AttackStyle;
import org.prsquared.normandy.enums.DefenceStyle;
import org.prsquared.normandy.enums.Position;
import org.prsquared.normandy.model.Player;
import org.prsquared.normandy.model.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerGeneratorTest {
    @Test
    void TestGenerate() {
        Player player = PlayerGenerator.generate(80, Position.ATT, AttackStyle.POSSESSION, DefenceStyle.HIGH_PRESS);
        System.out.println("Player Name:"+player.getFirstName()+" "+player.getSurname());
    }

    @Test
    void TestGeneratePlayers() {
        Team team = new Team("Manchester United", 82,84, AttackStyle.POSSESSION, DefenceStyle.HIGH_PRESS, 100);
        List<Player> players = PlayerGenerator.generatePlayers(team);
        for(Player player: players) {
            System.out.println("Player Name: "+player.getFirstName()+" "+player.getSurname());
            System.out.println("Rating: "+player.getOverall());
            System.out.println("Position: "+player.getPosition().toString());
        }
    }
}