package org.prsquared.normandy.enums;

public enum IncidentType {
    HOMEGOALTHREAT(1), AWAYGOALTHREAT(1), FOUL(4), REDCARD(1), YELLOWCARD(2), NOTHING(191);

    private  int value;
    IncidentType(int v) {
        this.value = v;
    }
    public int getIncidentChance(){
        return this.value;
    }
}
