package com.ashwinchat.starsimulator.simulator.ItemData;

import com.ashwinchat.starsimulator.simulator.pojos.ItemData;

import java.util.ArrayList;
import java.util.List;

public class SuperiorItemData {
    private static SuperiorItemData instance = new SuperiorItemData();
    private static final int MAX_ROLL = 100000;

    private List<ItemData> superiorItemData;

    public static SuperiorItemData getInstance() {
        return instance;
    }

    private SuperiorItemData() {
        this.superiorItemData = this.createItemData();
    }

    private List<ItemData> createItemData() {
        superiorItemData = new ArrayList<>(16);
        superiorItemData.add(new ItemData(5000, 5000, 0, 0));
        superiorItemData.add(new ItemData(5000, 0, 5000, 0));
        superiorItemData.add(new ItemData(4500, 0, 5500, 0));
        superiorItemData.add(new ItemData(4000, 0, 6000, 0));
        superiorItemData.add(new ItemData(4000, 0, 6000, 0));
        superiorItemData.add(new ItemData(4000, 0, 5820, 180));
        superiorItemData.add(new ItemData(4000, 0, 5700, 300));
        superiorItemData.add(new ItemData(4000, 0, 5580, 420));
        superiorItemData.add(new ItemData(4000, 0, 5400, 600));
        superiorItemData.add(new ItemData(3700, 0, 5355, 945));
        superiorItemData.add(new ItemData(3500, 0, 5200, 1300));
        superiorItemData.add(new ItemData(3500, 0, 4875, 1625));
        superiorItemData.add(new ItemData(0300, 0, 485, 4850));
        superiorItemData.add(new ItemData(0200, 0, 4900, 4900));
        superiorItemData.add(new ItemData(0100, 0, 4950, 4950));
        return superiorItemData;
    }

    /**
     * Chances will be rolled from 0 to 9999
     * This is to avoid floating point precision which will cause a lot of problems
     * Defined in MAX_INT
     */
    public List<ItemData> getSuperiorItemData() {
        return superiorItemData;
    }
}
