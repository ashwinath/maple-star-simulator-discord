package com.ashwinchat.starsimulator;

import com.ashwinchat.starsimulator.simulator.enums.ItemType;
import com.ashwinchat.starsimulator.simulator.impl.StarSimulator;
import com.ashwinchat.starsimulator.simulator.pojos.StarResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNormalStar {
    private StarSimulator simulator;

    @Before
    public void init() {
        this.simulator = StarSimulator.getInstance();
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
