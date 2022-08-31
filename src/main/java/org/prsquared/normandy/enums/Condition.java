package org.prsquared.normandy.enums;

public enum Condition {
    EXCELLENT(3), GOOD(2), FAIR(1), AVERAGE(0), BAD(-1), TERRIBLE(-2);
    public final int value;
    private Condition(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
}
