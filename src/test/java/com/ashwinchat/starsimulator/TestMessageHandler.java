package com.ashwinchat.starsimulator;

import com.ashwinchat.starsimulator.impl.MessageHandlerImpl;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMessageHandler {
    MessageHandlerImpl messageHandler;

    @Before
    public void init() {
        this.messageHandler = MessageHandlerImpl.getInstance();
    }

    @Test
    public void testNormalItemRun() {
        String   content = "/star normal 10";
        String[] input   = StringUtils.split(content);
        Assert.assertNotNull(messageHandler.processAndFormatReply(input, content));
    }

    @Test
    public void testSuperiorItemRun() {
        String   content = "/star superior 10";
        String[] input   = StringUtils.split(content);
        Assert.assertNotNull(messageHandler.processAndFormatReply(input, content));
    }

    @Test
    public void testFailMapleCommand() {
        String   content = "/star Tyrant 10";
        String[] input   = StringUtils.split(content);
        Assert.assertNotNull(messageHandler.processAndFormatReply(input, content));
    }

    @Test
    public void testNotMapleCommand() {
        String   content = "/HelloWorld Tyrant 10";
        String[] input   = StringUtils.split(content);
        Assert.assertNull(messageHandler.processAndFormatReply(input, content));
    }
}
