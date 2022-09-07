package org.prsquared.normandy.service;

import org.ajbrown.namemachine.Gender;
import org.ajbrown.namemachine.Name;
import org.ajbrown.namemachine.NameGenerator;
import org.prsquared.normandy.enums.AttackStyle;
import org.prsquared.normandy.enums.DefenceStyle;
import org.prsquared.normandy.enums.Position;
import org.prsquared.normandy.model.Player;
import org.prsquared.normandy.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class PlayerGenerator {

    public static Player generate(int overall, Position position, AttackStyle attackStyle, DefenceStyle style) {
        NameGenerator generator = new NameGenerator();
        Name name = generator.generateName(Gender.MALE);
        Player player = new Player(name.getFirstName(),name.getLastName(),overall,position,null);
        return player;
    }

    public static List<Player> generatePlayers(Team team) {
        List<Player> playerList = new ArrayList<>();
        playerList.add(generate(team.getOverall().intValue(),Position.GK,team.getAttackStyle(),team.getDefenceStyle()));
        for(int i=0;i<4;i++)
            playerList.add(generate(generatePlayerRating(team.getOverall().intValue()),Position.DEF,team.getAttackStyle(),team.getDefenceStyle()));
        for(int i=0;i<3;i++)
            playerList.add(generate(generatePlayerRating(team.getOverall().intValue()),Position.MID,team.getAttackStyle(),team.getDefenceStyle()));
        for(int i=0;i<3;i++)
            playerList.add(generate(generatePlayerRating(team.getOverall().intValue()),Position.ATT,team.getAttackStyle(),team.getDefenceStyle()));
        return playerList;
    }

    public static int generatePlayerRating(int overall){
        Random random = new Random();
        int randomVal = random.nextInt(10) - 20;
        System.out.println("Random factor:"+randomVal);
        return overall + randomVal;
    }
}
