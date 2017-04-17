package com.ashwinchat.starsimulator;

import com.ashwinchat.starsimulator.enums.ItemType;
import com.ashwinchat.starsimulator.impl.StarSimulatorImpl;
import com.ashwinchat.starsimulator.pojos.StarResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNormalStar {
    private StarSimulatorImpl simulator;

    @Before
    public void init() {
        this.simulator = StarSimulatorImpl.getInstance();
    }

    @Test
    public void testStarringDestroy() {
        StarResult result = this.simulator.runSimulation(18, ItemType.NORMAL);
        Assert.assertTrue(result.getDestroyCount() > 0);
    }

    @Test
    public void testNoDestroy() {
        StarResult result = this.simulator.runSimulation(17, ItemType.NORMAL);
        Assert.assertTrue(result.getDestroyCount() == 0);
    }
}
