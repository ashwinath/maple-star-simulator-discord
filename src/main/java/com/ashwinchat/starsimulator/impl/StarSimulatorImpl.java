package com.ashwinchat.starsimulator.impl;

import com.ashwinchat.starsimulator.ItemData.NormalItemData;
import com.ashwinchat.starsimulator.ItemData.SuperiorItemData;
import com.ashwinchat.starsimulator.Utils.StarUtils;
import com.ashwinchat.starsimulator.enums.ItemType;
import com.ashwinchat.starsimulator.enums.StarStatus;
import com.ashwinchat.starsimulator.interfaces.IItemData;
import com.ashwinchat.starsimulator.pojos.ItemData;
import com.ashwinchat.starsimulator.pojos.StarResult;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class StarSimulatorImpl {
    private static StarSimulatorImpl instance = new StarSimulatorImpl();

    private static final int TOTAL_RUNS = 1000;
    private static final int NORMAL_ITEM_DESTROY_LEVEL = 12;
    private static final Logger logger = Logger.getLogger(StarSimulatorImpl.class);

    public static StarSimulatorImpl getInstance() {
        return instance;
    }

    private StarSimulatorImpl() {
    }

    public StarResult runSimulation(int desiredStarLevel, ItemType itemType) {
        logger.info(StarUtils.formatStartSimulation(itemType, desiredStarLevel));

        double totalDestroyCount = 0;
        BigDecimal totalCost = new BigDecimal(0);
        for (int i = 0; i < TOTAL_RUNS; ++i) {
            StarResult result = runOneSimulation(desiredStarLevel, itemType);
            totalDestroyCount += result.getDestroyCount();
            totalCost = totalCost.add(result.getCost());
        }

        StarResult averageResult = new StarResult();
        double avgDestroyCount = totalDestroyCount / TOTAL_RUNS;
        BigDecimal avgCost = totalCost.divide(new BigDecimal(TOTAL_RUNS), RoundingMode.CEILING);
        averageResult.setDestroyCount(avgDestroyCount);
        averageResult.setCost(avgCost);
        logger.info(StarUtils.formatStarString(averageResult));

        return averageResult;
    }

    private StarResult runOneSimulation(int desiredStarLevel, ItemType itemType) {
        int starLevel = 0;
        BigDecimal totalCost = new BigDecimal(0);
        StarStatus status = StarStatus.ZERO;
        double destroyCount = 0;
        IItemData data;
        if (itemType == ItemType.NORMAL) {
            data = NormalItemData.getInstance();
        } else {
            data = SuperiorItemData.getInstance();
        }
        List<ItemData> dataList = data.getItemData();

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
                        if (itemType == ItemType.NORMAL) {
                            starLevel = NORMAL_ITEM_DESTROY_LEVEL;
                        } else {
                            starLevel = 0;
                        }
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
                        if (itemType == ItemType.NORMAL) {
                            starLevel = NORMAL_ITEM_DESTROY_LEVEL;
                        } else {
                            starLevel = 0;
                        }
                        status = StarStatus.ZERO;
                        ++destroyCount;
                    }
                    break;
                case DROPPED_TWICE:
                    ++starLevel;
                    status = StarStatus.ZERO;
                    break;
            }
            totalCost = totalCost.add(currData.getCost());
        }
        StarResult result = new StarResult();
        result.setCost(totalCost);
        result.setDestroyCount(destroyCount);
        return result;
    }
}
