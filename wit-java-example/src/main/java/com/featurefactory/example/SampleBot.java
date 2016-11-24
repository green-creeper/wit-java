package com.featurefactory.example;


import com.featurefactory.witjava.WitClient;
import com.featurefactory.witjava.WitClientBuilder;
import com.featurefactory.witjava.model.ChatContext;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SampleBot extends TelegramLongPollingBot{

    private WitClient client;
    private ChatContext context;

    SampleBot() {

        client = new WitClientBuilder("YOUR_WIT_AI_TOKEN")
                .addActionHandler("getJoke", new JokeAction())
                .setMessageHandler(new JokeMessage(this)).build();
        context = new ChatContext();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(!update.hasMessage()){
            return;
        }
        Message message = update.getMessage();

        if(message.isCommand()){
            SendMessage request = new SendMessage();
            request.setChatId(message.getChatId().toString());
            request.setText("This is test bot");
            sendResponse(request);
        }
        else if(message.hasText()){
            Map<String, Object> chatMetadata = new HashMap<>();
            chatMetadata.put("chat_id", message.getChatId().toString());
            client.converse(message.getText(), UUID.randomUUID().toString(), context, chatMetadata);
        }
    }

    private void sendResponse(SendMessage request){
        try {
            sendMessage(request);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "YOUR_BOT_NAME";
    }

    @Override
    public String getBotToken() {
        return "YOUR_BOT_TOKEN";
    }
}
