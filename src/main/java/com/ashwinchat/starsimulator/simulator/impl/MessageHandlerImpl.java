package com.ashwinchat.starsimulator.simulator.impl;

import com.ashwinchat.starsimulator.simulator.Utils.StarUtils;
import com.ashwinchat.starsimulator.simulator.enums.ItemType;
import com.ashwinchat.starsimulator.simulator.pojos.StarResult;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class MessageHandlerImpl {
    private StarSimulatorImpl simulator;
    private static MessageHandlerImpl instance = new MessageHandlerImpl();
    private static final Logger logger = Logger.getLogger(StarSimulatorImpl.class);
    private static final String COMMAND = "/star";
    private static final int NORMAL_MAX_STARS = 20;
    private static final int SUPERIOR_MAX_STARS = 15;
    private static final int REASONABLE_SUPERIOR_MAX_STARS = 13;

    public static MessageHandlerImpl getInstance() {
        return instance;
    }

    private MessageHandlerImpl() {
        this.simulator = StarSimulatorImpl.getInstance();
    }

    public String processAndFormatReply(String[] userInput, String content) {
        String messageToSend = null;
        if (userInput.length > 0 && StringUtils.equals(userInput[0], COMMAND)) {
            logger.info("Message Received: " + content);
            if (userInput.length != 3) {
                messageToSend = "USAGE: /star <normal/superior> <desired stars>";
                logger.warn("Wrong number of argument given. # of arguments given = " + userInput.length);
            } else {
                String userItemTypeInput = StringUtils.upperCase(userInput[1]);
                try {
                    int desiredStarLevel = Integer.parseInt(userInput[2]);
                    if (StringUtils.equals(userItemTypeInput, StringUtils.upperCase(ItemType.NORMAL.getItemDescription()))) {
                        if (desiredStarLevel <= NORMAL_MAX_STARS) {
                            StarResult result = this.simulator.runSimulation(desiredStarLevel, ItemType.NORMAL);
                            messageToSend = StarUtils.formatStarString(result);
                        } else {
                            messageToSend = "Normal items can only be starred to " + NORMAL_MAX_STARS + " stars.";
                            logger.warn("Person sent number greater than " + NORMAL_MAX_STARS + ". Number = " + desiredStarLevel);
                        }
                    } else if (StringUtils.equals(userItemTypeInput, StringUtils.upperCase(ItemType.SUPERIOR.getItemDescription()))) {
                        if (desiredStarLevel <= SUPERIOR_MAX_STARS) {
                            if (desiredStarLevel <= REASONABLE_SUPERIOR_MAX_STARS) {
                                StarResult result = this.simulator.runSimulation(desiredStarLevel, ItemType.SUPERIOR);
                                messageToSend = StarUtils.formatStarString(result);
                            } else {
                                messageToSend = "Simulations for superior items over " + REASONABLE_SUPERIOR_MAX_STARS + " stars take too long.";
                                logger.warn("Person tried for starring simulation greater than " + REASONABLE_SUPERIOR_MAX_STARS + " stars.");
                            }
                        } else {
                            messageToSend = "Superior items can only be starred to " + SUPERIOR_MAX_STARS + " stars.";
                            logger.warn("Person sent number greater than " + SUPERIOR_MAX_STARS + ". Number = " + desiredStarLevel);
                        }
                    } else {
                        messageToSend = "I can only simulate normal or superior items.";
                        logger.warn("Person named an unknown item the 2nd field. Item = " + userItemTypeInput);
                    }
                } catch (NumberFormatException e) {
                    messageToSend = "Number of stars must be an integer.";
                    logger.warn("Person did not send an integer in the 3rd field.");
                }
            }
        }
        return messageToSend;
    }
}
