package com.ashwinchat.scrollsimulator.impl;

import com.ashwinchat.scrollsimulator.pojos.ScrollResult;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class ScrollSimulatorImpl {

    private static final Logger logger = Logger.getLogger(ScrollSimulatorImpl.class);
    private static ScrollSimulatorImpl instance = new ScrollSimulatorImpl();
    private static final int PERCENT_CHANCE_TO_INNO = 50;
    private static final int PERCENT_CHANCE_TO_CSS = 10;
    private static final int BASE_15_SCROLL_SUCCESS_RATE = 15;
    private static final int TOTAL_RUNS = 1000;

    public static ScrollSimulatorImpl getInstance() {
        return instance;
    }

    private ScrollSimulatorImpl() {
    }

    public Map<Integer, ScrollResult> runSimulation(int numberOfUpgradeSlots, int diligenceLevel) {
        logger.info("Starting scrolling simulation for " + numberOfUpgradeSlots + " slots, diligence level = " + diligenceLevel);
        int successChance = BASE_15_SCROLL_SUCCESS_RATE + (int)(diligenceLevel / 10);
        Map<Integer, ScrollResult> resultMap = new TreeMap();
        for (int innoThresHold = 0; innoThresHold < numberOfUpgradeSlots; ++innoThresHold) {
            logger.info("Starting simulation for inno threshold level of " + innoThresHold);
            ScrollResult resultAtThreshold = runSimulationAtInnoThresHold(innoThresHold, numberOfUpgradeSlots, successChance);
            resultMap.put(innoThresHold, resultAtThreshold);
        }

        return resultMap;
    }

    private ScrollResult runSimulationAtInnoThresHold(int innoThreshold, int numberOfUpgradeSlots, int successChance) {
        double cssUsed = 0;
        double innoUsed = 0;
        double scrollUsed = 0;
        for (int runNumber = 0; runNumber < TOTAL_RUNS; ++runNumber) {
            ScrollResult singleResult = runOneSimulation(innoThreshold, numberOfUpgradeSlots, successChance);
            cssUsed += singleResult.getCssUsed();
            innoUsed += singleResult.getInnoUsed();
            scrollUsed += singleResult.getScrollUsed();
        }
        ScrollResult result = new ScrollResult();
        result.setCssUsed(cssUsed / TOTAL_RUNS);
        result.setInnoUsed(innoUsed / TOTAL_RUNS);
        result.setScrollUsed(scrollUsed / TOTAL_RUNS);
        return result;
    }

    private ScrollResult runOneSimulation(int innoThreshold, int numberOfUpgradeSlots, int successChance) {
        int freeslots = numberOfUpgradeSlots;
        int successCount = 0;
        double innoCount = 0;
        double cssCount = 0;
        double scrollCount = 0;
        while (successCount < numberOfUpgradeSlots) {
            if (freeslots > 0) {
                if (roll() <= successChance) {
                    ++successCount;
                }
                --freeslots;
                ++scrollCount;
            } else if ((freeslots + successCount) < innoThreshold) {
                if (roll() <= PERCENT_CHANCE_TO_INNO) {
                    freeslots = numberOfUpgradeSlots;
                    successCount = 0;
                }
                ++innoCount;
            } else {
                if (roll() <= PERCENT_CHANCE_TO_CSS) {
                    ++freeslots;
                }
                ++cssCount;
            }
        }

        // set results
        ScrollResult result = new ScrollResult();
        result.setInnoUsed(innoCount);
        result.setCssUsed(cssCount);
        result.setScrollUsed(scrollCount);
        return result;
    }

    private int roll() {
        return RandomUtils.nextInt(1, 100);
    }

}
