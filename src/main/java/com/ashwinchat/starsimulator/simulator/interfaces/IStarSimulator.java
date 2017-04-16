package com.ashwinchat.starsimulator.simulator.interfaces;

import com.ashwinchat.starsimulator.simulator.enums.ItemType;
import com.ashwinchat.starsimulator.simulator.pojos.StarResult;

public interface IStarSimulator {
    StarResult runSimulation(int desiredStarLevel, ItemType itemType);
}
