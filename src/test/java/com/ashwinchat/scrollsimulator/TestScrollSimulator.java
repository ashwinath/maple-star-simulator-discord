package com.ashwinchat.scrollsimulator;

import com.ashwinchat.scrollsimulator.impl.ScrollSimulatorImpl;
import com.ashwinchat.scrollsimulator.pojos.ScrollResult;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class TestScrollSimulator {
    private ScrollSimulatorImpl simulator;
    private static final Logger logger = Logger.getLogger(TestScrollSimulator.class);

    @Before
    public void init() {
        this.simulator = ScrollSimulatorImpl.getInstance();
    }

    @Test
    public void runSimulation() {
        Map<Integer, ScrollResult> resultMap = this.simulator.runSimulation(9, 92);
        for (Map.Entry<Integer, ScrollResult> entry : resultMap.entrySet()) {
            int threshhold = entry.getKey();
            double innoCount = entry.getValue().getInnoUsed();
            double cssCount = entry.getValue().getCssUsed();
            double scrollCount = entry.getValue().getScrollUsed();
            logger.info("Inno Threshhold: " + threshhold + ", innoCount: " + innoCount + ", cssCount: " + cssCount + ", scrollCount: " + scrollCount);
        }
        Assert.assertNotNull(resultMap);
    }
}
