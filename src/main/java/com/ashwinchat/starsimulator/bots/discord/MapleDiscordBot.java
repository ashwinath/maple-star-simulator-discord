package com.ashwinchat.starsimulator.bots.discord;

import com.ashwinchat.starsimulator.impl.MessageHandlerImpl;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.commons.lang.StringUtils;


public class MapleDiscordBot extends ListenerAdapter {

    private MessageHandlerImpl messageHandler;

    public MapleDiscordBot() {
        this.messageHandler = MessageHandlerImpl.getInstance();
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
        String messageToSend = messageHandler.processAndFormatReply(userInput, content);
        if (messageToSend != null) {
            channel.sendMessage(messageToSend).queue();
        }
    }
}
