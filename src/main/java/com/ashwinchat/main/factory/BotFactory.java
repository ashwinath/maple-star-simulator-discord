package com.ashwinchat.main.factory;

import com.ashwinchat.starsimulator.bots.discord.MapleDiscordBot;
import com.ashwinchat.starsimulator.bots.telegram.MapleTelegramBot;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.security.auth.login.LoginException;

public class BotFactory {

    private static String API_KEY_ENV = "DISCORD_API";
    private static final Logger logger = Logger.getLogger(MapleDiscordBot.class);

    public void buildAsyncTelegramBot() {
        new Thread(() -> {
            logger.info("Building Telegram Bot.");
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
            try {
                botsApi.registerBot(new MapleTelegramBot());
            } catch (TelegramApiException e) {
                logger.error("Could not build bot. " + e, e);
            }

        }).start();
    }

    public void buildAsyncDiscordBot() {
        logger.info("Building Discord Bot.");
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(System.getenv(API_KEY_ENV))
                    .buildAsync();
            jda.addEventListener(new MapleDiscordBot());
        } catch (LoginException e) {
            logger.error("Could not log in: " + e, e);
        } catch (RateLimitedException e) {
            logger.error("Rate was limited: " + e, e);
        }
    }
}
