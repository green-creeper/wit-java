package com.featurefactory.example;


import com.featurefactory.witjava.MessageHandler;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Map;

public class JokeMessage implements MessageHandler{
    private  SampleBot bot;

    public JokeMessage(SampleBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String message, Map<String, Object> chatMetadata) {
        SendMessage req = new SendMessage();
        req.setChatId(chatMetadata.get("chat_id").toString());
        req.setText(message);

        try {
            bot.sendMessage(req);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
