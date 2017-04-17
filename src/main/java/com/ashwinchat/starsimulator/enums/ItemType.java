package com.ashwinchat.starsimulator.enums;

public enum ItemType {
    NORMAL("Normal"),
    SUPERIOR("Superior");

    private final String itemDescription;

    ItemType(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
