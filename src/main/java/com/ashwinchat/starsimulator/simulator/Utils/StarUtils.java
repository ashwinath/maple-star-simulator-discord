package com.ashwinchat.starsimulator.simulator.Utils;

import com.ashwinchat.starsimulator.simulator.enums.ItemType;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

public class StarUtils {
    public static String formatStarString(BigDecimal cost, double destroyCount) {
        String costString = cost.toPlainString();
        return String.format("Cost: %s Million, Destroy Count: %s", StringUtils.substring(costString, 0, costString.length() - 6), destroyCount);
    }

    public static String formatStartSimulation(ItemType itemType, int desiredStars) {
        return String.format("Starting simulation for %s item, desired star level is %s", itemType.getItemDescription(), desiredStars);
    }
}
