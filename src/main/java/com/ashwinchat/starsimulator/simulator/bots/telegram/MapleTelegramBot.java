package com.ashwinchat.starsimulator.simulator.bots.telegram;

import com.ashwinchat.starsimulator.simulator.impl.MessageHandlerImpl;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MapleTelegramBot extends TelegramLongPollingBot {

    private final String BOT_USER_NAME = "PAHelperBot";
    private static String API_KEY_ENV = "TELEGRAM_API";
    private final String TELEGRAM_TOKEN;
    private final MessageHandlerImpl messageHandler;

    public MapleTelegramBot() {
        this.TELEGRAM_TOKEN = System.getenv(API_KEY_ENV);
        this.messageHandler = MessageHandlerImpl.getInstance();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String content = update.getMessage().getText();
            String[] userInput = StringUtils.split(content);
            String messageToSend = messageHandler.processAndFormatReply(userInput, content);

            if (messageToSend != null) {
                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        .setText(messageToSend);
                try {
                    sendMessage(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return this.TELEGRAM_TOKEN;
    }
}
