package com.ashwinchat.starsimulator.simulator.pojos;

import java.math.BigDecimal;

public class ItemData {
    private int success;
    private int failMaintain;
    private int failDecrease;
    private int failDestroy;
    private BigDecimal cost;

    public ItemData(int success, int failMaintain, int failDecrease, int failDestroy, BigDecimal cost) {
        this.success = success;
        this.failMaintain = failMaintain;
        this.failDecrease = failDecrease;
        this.failDestroy = failDestroy;
        this.cost = cost;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailMaintain() {
        return failMaintain;
    }

    public int getFailDecrease() {
        return failDecrease;
    }

    public int getFailDestroy() {
        return failDestroy;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
