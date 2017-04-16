package com.ashwinchat.starsimulator.simulator.enums;

public enum ItemType {
    NORMAL("Normal"),
    SUPERIOR("Superior");

    private final String itemDescription;

    private ItemType(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
