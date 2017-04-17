package com.ashwinchat.starsimulator.Utils;

import com.ashwinchat.starsimulator.enums.ItemType;
import com.ashwinchat.starsimulator.pojos.StarResult;
import org.apache.commons.lang.StringUtils;

public class StarUtils {
    public static String formatStarString(StarResult result) {
        String costString = result.getCost().toPlainString();
        return String.format("Cost: %s Million, Destroy Count: %s", StringUtils.substring(costString, 0, costString.length() - 6), result.getDestroyCount());
    }

    public static String formatStartSimulation(ItemType itemType, int desiredStars) {
        return String.format("Starting simulation for %s item, desired star level is %s", itemType.getItemDescription(), desiredStars);
    }
}
