package com.ashwinchat.starsimulator;

import com.ashwinchat.starsimulator.enums.ItemType;
import com.ashwinchat.starsimulator.impl.StarSimulatorImpl;
import com.ashwinchat.starsimulator.pojos.StarResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSuperiorStar {
    private StarSimulatorImpl simulator;

    @Before
    public void init() {
        this.simulator = StarSimulatorImpl.getInstance();
    }
    @Test
    public void testStarringDestroy() {
        StarResult result = this.simulator.runSimulation(6, ItemType.SUPERIOR);
        Assert.assertTrue(result.getDestroyCount() > 0);
    }

    @Test
    public void testNoDestroy() {
        StarResult result = this.simulator.runSimulation(5, ItemType.SUPERIOR);
        Assert.assertTrue(result.getDestroyCount() == 0);
    }
}
