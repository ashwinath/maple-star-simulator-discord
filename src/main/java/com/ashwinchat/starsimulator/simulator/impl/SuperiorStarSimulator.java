package com.ashwinchat.starsimulator.simulator.impl;

import com.ashwinchat.starsimulator.simulator.ItemData.SuperiorItemData;
import com.ashwinchat.starsimulator.simulator.Utils.StarUtils;
import com.ashwinchat.starsimulator.simulator.enums.StarStatus;
import com.ashwinchat.starsimulator.simulator.interfaces.IStarSimulator;
import com.ashwinchat.starsimulator.simulator.pojos.ItemData;
import com.ashwinchat.starsimulator.simulator.pojos.StarResult;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class SuperiorStarSimulator implements IStarSimulator {
    private static SuperiorStarSimulator instance = new SuperiorStarSimulator();

    private static final BigDecimal COST = new BigDecimal("55832200");
    private static final int TOTAL_RUNS = 1000;
    private static final Logger logger = Logger.getLogger(SuperiorStarSimulator.class);

    public static IStarSimulator getInstance() {
        return instance;
    }

    private SuperiorStarSimulator() {
    }

    @Override
    public StarResult runSimulation(int desiredStarLevel) {
        logger.info("Starting Simulation for star level = " + desiredStarLevel);
        double totalDestroyCount = 0;
        BigDecimal totalCost = new BigDecimal(0);
        for (int i = 0; i < TOTAL_RUNS; ++i) {
            StarResult result = runOneSimulation(desiredStarLevel);
            totalDestroyCount += result.getDestroyCount();
            totalCost = totalCost.add(result.getCost());
        }
        StarResult averageResult = new StarResult();
        double avgDestroyCount = totalDestroyCount / TOTAL_RUNS;
        BigDecimal avgCost = totalCost.divide(new BigDecimal(TOTAL_RUNS), RoundingMode.CEILING);
        averageResult.setDestroyCount(avgDestroyCount);
        averageResult.setCost(avgCost);
        logger.info(StarUtils.formatStarString(avgCost, avgDestroyCount));
        return averageResult;
    }

    private StarResult runOneSimulation(int desiredStarLevel) {
        int starLevel = 0;
        BigDecimal totalCost = new BigDecimal(0);
        StarStatus status = StarStatus.ZERO;
        double destroyCount = 0;
        List<ItemData> dataList = SuperiorItemData.getInstance().getSuperiorItemData();

        while (starLevel < desiredStarLevel) {
            ItemData currData = dataList.get(starLevel);
            int rng = RandomUtils.nextInt(10000);
            switch (status) {
                case ZERO:
                    if (rng <= currData.getSuccess()) {
                        ++starLevel;
                    } else if (rng <= currData.getSuccess()
                            + currData.getFailMaintain()) {
                        // do nothing
                    } else if (rng <= currData.getSuccess()
                            + currData.getFailMaintain()
                            + currData.getFailDecrease()) {
                        --starLevel;
                        status = StarStatus.DROPPED_ONCE;
                    } else {
                        starLevel = 0;
                        ++destroyCount;
                    }
                    break;
                case DROPPED_ONCE:
                    if (rng <= currData.getSuccess()) {
                        ++starLevel;
                        status = StarStatus.ZERO;
                    } else if (rng <= currData.getSuccess()
                            + currData.getFailMaintain()) {
                        // do nothing
                    } else if (rng <= currData.getSuccess()
                            + currData.getFailMaintain()
                            + currData.getFailDecrease()) {
                        --starLevel;
                        status = StarStatus.DROPPED_TWICE;
                    } else {
                        starLevel = 0;
                        status = StarStatus.ZERO;
                        ++destroyCount;
                    }
                    break;
                case DROPPED_TWICE:
                    ++starLevel;
                    status = StarStatus.ZERO;
                    break;
            }
            totalCost = totalCost.add(COST);
        }
        StarResult result = new StarResult();
        result.setCost(totalCost);
        result.setDestroyCount(destroyCount);
        return result;
    }
}
