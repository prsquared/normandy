package org.prsquared.normandy.model;

import org.prsquared.normandy.enums.AttackStyle;
import org.prsquared.normandy.enums.Position;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Player {
 private String firstName;
 private String surname;
 private Integer overall;
 private Position position;
 private List<AttackStyle> styles;
}
