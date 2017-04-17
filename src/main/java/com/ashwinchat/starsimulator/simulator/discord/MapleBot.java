package com.ashwinchat.starsimulator.simulator.discord;

import com.ashwinchat.starsimulator.simulator.Utils.StarUtils;
import com.ashwinchat.starsimulator.simulator.enums.ItemType;
import com.ashwinchat.starsimulator.simulator.impl.StarSimulator;
import com.ashwinchat.starsimulator.simulator.pojos.StarResult;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class MapleBot extends ListenerAdapter {

    private static final Logger logger = Logger.getLogger(MapleBot.class);

    private static final String COMMAND = "/star";
    private static final int NORMAL_MAX_STARS = 20;
    private static final int SUPERIOR_MAX_STARS = 15;
    private static final int REASONABLE_SUPERIOR_MAX_STARS = 13;

    private StarSimulator simulator;

    public MapleBot() {
        this.simulator = StarSimulator.getInstance();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event == null) {
            return;
        }

        User author = event.getAuthor();
        if (author == null) {
            return;
        }
        MessageChannel channel = event.getChannel();
        if (channel == null) {
            return;
        }

        Message message = event.getMessage();
        if (message == null) {
            return;
        }

        String content = message.getContent();
        if (content == null) {
            return;
        }

        String[] userInput = StringUtils.split(content);
        if (userInput.length > 0 && StringUtils.equals(userInput[0], COMMAND)) {
            logger.info("Message Received: " + content);
            if (userInput.length != 3) {
                channel.sendMessage("USAGE: /star <normal/superior> <desired stars>").queue();
                logger.warn("Wrong number of argument given. # of arguments given = " + userInput.length);
            } else {
                String userItemTypeInput = StringUtils.upperCase(userInput[1]);
                try {
                    int desiredStarLevel = Integer.parseInt(userInput[2]);
                    if (StringUtils.equals(userItemTypeInput, StringUtils.upperCase(ItemType.NORMAL.getItemDescription()))) {
                        if (desiredStarLevel <= NORMAL_MAX_STARS) {
                            StarResult result = this.simulator.runSimulation(desiredStarLevel, ItemType.NORMAL);
                            channel.sendMessage(StarUtils.formatStarString(result)).queue();
                        } else {
                            channel.sendMessage("Normal items can only be starred to " + NORMAL_MAX_STARS + " stars.").queue();
                            logger.warn("Person sent number greater than " + NORMAL_MAX_STARS + ". Number = " + desiredStarLevel);
                        }
                    } else if (StringUtils.equals(userItemTypeInput, StringUtils.upperCase(ItemType.SUPERIOR.getItemDescription()))) {
                        if (desiredStarLevel <= SUPERIOR_MAX_STARS) {
                            if (desiredStarLevel <= REASONABLE_SUPERIOR_MAX_STARS) {
                                StarResult result = this.simulator.runSimulation(desiredStarLevel, ItemType.SUPERIOR);
                                channel.sendMessage(StarUtils.formatStarString(result)).queue();
                            } else {
                                channel.sendMessage("Simulations for superior items over " + REASONABLE_SUPERIOR_MAX_STARS + " stars take too long.").queue();
                                logger.warn("Person tried for starring simulation greater than " + REASONABLE_SUPERIOR_MAX_STARS + " stars.");
                            }
                        } else {
                            channel.sendMessage("Superior items can only be starred to " + SUPERIOR_MAX_STARS + " stars.").queue();
                            logger.warn("Person sent number greater than " + SUPERIOR_MAX_STARS + ". Number = " + desiredStarLevel);
                        }
                    } else {
                        channel.sendMessage("I can only simulate normal or superior items.").queue();
                        logger.warn("Person named an unknown item the 2nd field. Item = " + userItemTypeInput);
                    }
                } catch (NumberFormatException e) {
                    channel.sendMessage("Number of stars must be an integer.").queue();
                    logger.warn("Person did not send an integer in the 3rd field.");
                }
            }
        }
    }
}
