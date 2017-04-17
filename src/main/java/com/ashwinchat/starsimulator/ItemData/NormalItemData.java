package com.ashwinchat.starsimulator.ItemData;

import com.ashwinchat.starsimulator.interfaces.IItemData;
import com.ashwinchat.starsimulator.pojos.ItemData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NormalItemData implements IItemData {
    private static NormalItemData instance = new NormalItemData();
    private List<ItemData> normalItemData;

    public static NormalItemData getInstance() {
        return instance;
    }

    private NormalItemData() {
        normalItemData = createItemData();
    }

    private List<ItemData> createItemData() {
        normalItemData = new ArrayList<>(21);
        normalItemData.add(new ItemData(9500, 500, 0, 0, this.generateCost(0)));
        normalItemData.add(new ItemData(9000, 1000, 0, 0, this.generateCost(1)));
        normalItemData.add(new ItemData(8500, 1500, 0, 0, this.generateCost(2)));
        normalItemData.add(new ItemData(8500, 1500, 0, 0, this.generateCost(3)));
        normalItemData.add(new ItemData(8000, 2000, 0, 0, this.generateCost(4)));
        normalItemData.add(new ItemData(7500, 2500, 0, 0, this.generateCost(5)));
        normalItemData.add(new ItemData(7000, 0, 3000, 0, this.generateCost(6)));
        normalItemData.add(new ItemData(6500, 0, 3500, 0, this.generateCost(7)));
        normalItemData.add(new ItemData(6000, 0, 4000, 0, this.generateCost(8)));
        normalItemData.add(new ItemData(5500, 0, 4500, 0, this.generateCost(9)));
        normalItemData.add(new ItemData(4500, 5500, 0, 0, this.generateCost(10)));
        normalItemData.add(new ItemData(3500, 0, 6500, 0, this.generateCost(11)));
        normalItemData.add(new ItemData(3000, 0, 7000, 0, this.generateCost(12)));
        normalItemData.add(new ItemData(3000, 0, 7000, 0, this.generateCost(13)));
        normalItemData.add(new ItemData(2500, 0, 7500, 0, this.generateCost(14)));
        normalItemData.add(new ItemData(2500, 7500, 0, 0, this.generateCost(15)));
        normalItemData.add(new ItemData(2000, 0, 8000, 0, this.generateCost(16)));
        normalItemData.add(new ItemData(1500, 0, 5950, 2550, this.generateCost(17)));
        normalItemData.add(new ItemData(1000, 0, 5850, 3150, this.generateCost(18)));
        normalItemData.add(new ItemData(500, 0, 5700, 3800, this.generateCost(19)));
        return normalItemData;
    }

    private BigDecimal generateCost(int starLevel) {
        final int ITEM_LEVEL = 150;
        int cost = (int) (1000 + Math.pow(ITEM_LEVEL, 3) * (starLevel + 1) / 25);
        if (12 <= starLevel && starLevel < 17) {
            cost *= 2;
        }
        return new BigDecimal(cost);
    }

    /**
     * Chances will be rolled from 0 to 9999
     * This is to avoid floating point precision which will cause a lot of problems
     * Defined in MAX_INT
     */
    @Override
    public List<ItemData> getItemData() {
        return normalItemData;
    }
}
