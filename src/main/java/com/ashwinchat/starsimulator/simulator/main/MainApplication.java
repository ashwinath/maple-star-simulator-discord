package com.ashwinchat.starsimulator.simulator.main;

import com.ashwinchat.starsimulator.simulator.discord.MapleBot;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.apache.log4j.Logger;

import javax.security.auth.login.LoginException;

public class MainApplication {

    private static String API_KEY_ENV = "DISCORD_API";
    private static final Logger logger = Logger.getLogger(MapleBot.class);

    public static void main(String[] args) {
        logger.info("Initialising Bot.");
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(System.getenv(API_KEY_ENV))
                    .buildBlocking();
            jda.addEventListener(new MapleBot());
        } catch (LoginException e) {
            logger.error("Could not log in: " + e, e);
            System.exit(-1);
        } catch (RateLimitedException e) {
            logger.error("Rate was limited: " + e, e);
        } catch (InterruptedException e) {
            logger.error("Was interrupted: " + e, e);
        }
    }

}
