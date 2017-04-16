package com.ashwinchat.com.starsimulator;

import com.ashwinchat.starsimulator.simulator.impl.SuperiorStarSimulator;
import com.ashwinchat.starsimulator.simulator.interfaces.IStarSimulator;
import com.ashwinchat.starsimulator.simulator.pojos.StarResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSuperiorStar {
    IStarSimulator simulator;

    @Before
    public void init() {
        this.simulator = SuperiorStarSimulator.getInstance();
    }
    @Test
    public void testStarringDestroy() {
        StarResult result = this.simulator.runSimulation(6);
        Assert.assertTrue(result.getDestroyCount() > 0);
    }

    @Test
    public void testNoDestroy() {
        StarResult result = this.simulator.runSimulation(5);
        Assert.assertTrue(result.getDestroyCount() == 0);
    }
}
