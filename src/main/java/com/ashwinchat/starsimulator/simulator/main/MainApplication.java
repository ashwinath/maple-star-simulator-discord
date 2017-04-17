package com.ashwinchat.starsimulator.simulator.main;

import com.ashwinchat.starsimulator.simulator.bots.factory.BotFactory;
import org.apache.log4j.Logger;

public class MainApplication {

    private static final Logger logger = Logger.getLogger(MainApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Telegram/Discord bot application.");
        BotFactory botFactory = new BotFactory();
        botFactory.buildAsyncDiscordBot();
        botFactory.buildAsyncTelegramBot();
    }

}
