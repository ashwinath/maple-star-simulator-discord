package com.ashwinchat.scrollsimulator.utils;

import com.ashwinchat.scrollsimulator.pojos.ScrollResult;

import java.util.Map;

public class ScrollUtils {
    public static String formatScrollString(Map<Integer, ScrollResult> result) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, ScrollResult> entry : result.entrySet()) {
            double cssUsed = entry.getValue().getCssUsed();
            double innoUsed = entry.getValue().getInnoUsed();
            double scrollUsed = entry.getValue().getScrollUsed();
            String formattedString = String.format("Innocence threshold: %s, Innocence: %s, CSS: %s, Scroll: %s", entry.getKey(), innoUsed, cssUsed, scrollUsed);
            sb.append(formattedString)
                    .append("\n");
        }
        return sb.toString();
    }

    public static String formatStartSimulation(int upgradeCount, int diligenceLevel) {
        return String.format("Starting scrolling simulation for upgrade count = %s, diligenceLevel = %s", upgradeCount, diligenceLevel);
    }
}
