package com.ashwinchat.starsimulator.simulator.pojos;

public class ItemData {
    private int success;
    private int failMaintain;
    private int failDecrease;
    private int failDestroy;

    public ItemData(int success, int failMaintain, int failDecrease, int failDestroy) {
        this.success = success;
        this.failMaintain = failMaintain;
        this.failDecrease = failDecrease;
        this.failDestroy = failDestroy;
    }

    public int getSuccess() {
        return success;
    }

    public int getFailMaintain() {
        return failMaintain;
    }

    public int getFailDecrease() {
        return failDecrease;
    }

    public int getFailDestroy() {
        return failDestroy;
    }
}
