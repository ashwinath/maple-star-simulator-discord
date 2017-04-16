package com.ashwinchat.starsimulator;

import com.ashwinchat.starsimulator.simulator.enums.ItemType;
import com.ashwinchat.starsimulator.simulator.impl.StarSimulator;
import com.ashwinchat.starsimulator.simulator.interfaces.IStarSimulator;
import com.ashwinchat.starsimulator.simulator.pojos.StarResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSuperiorStar {
    private IStarSimulator simulator;

    @Before
    public void init() {
        this.simulator = StarSimulator.getInstance();
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
