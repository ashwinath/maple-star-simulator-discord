package com.ashwinchat.starsimulator.pojos;

import java.math.BigDecimal;

public class StarResult {
    private BigDecimal cost;
    private Double destroyCount;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Double getDestroyCount() {
        return destroyCount;
    }

    public void setDestroyCount(Double destroyCount) {
        this.destroyCount = destroyCount;
    }

}
