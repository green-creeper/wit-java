package com.featurefactory.witjava.impl;

import com.featurefactory.witjava.ActionHandler;
import com.featurefactory.witjava.MessageHandler;
import com.featurefactory.witjava.WitClient;
import com.featurefactory.witjava.WitRequest;
import com.featurefactory.witjava.model.ChatContext;
import com.featurefactory.witjava.model.ConverseResponse;
import com.featurefactory.witjava.model.MessageResponse;
import com.featurefactory.witjava.model.ResponseMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.Map;

public class WitClientImpl implements WitClient {

    final Logger logger = LoggerFactory.getLogger(WitClientImpl.class);
    private final String token;
    private final Map<String, ActionHandler> actionHandlerMap;
    private final MessageHandler messageHandler;
    public static final String HOST_URI = "https://api.wit.ai";

    public WitClientImpl(String token, Map<String, ActionHandler> actionHandlerMap, MessageHandler messageHandler) {
        this.token = token;
        this.actionHandlerMap = actionHandlerMap;
        this.messageHandler = messageHandler;
        Unirest.setDefaultHeader("Authorization", "Bearer "+this.token);
        Unirest.setObjectMapper(new ResponseMapper());
    }

    public <T> T sendRequest(WitRequest request, Class<T> valueType) {
        try {
            return new HttpRequestWithBody(request.getMethod(), HOST_URI+request.getEndpoint())
                    .queryString(request.getParamMap()).body(request.getContext().toString())
                    .asObject(valueType).getBody();
        } catch (UnirestException e) {
            logger.error("Error communicating with wit.ai API", e);
            throw new RuntimeException(e);
        }
    }

    public MessageResponse getMeaning(String message){
        return sendRequest(new MessageRequest(message), MessageResponse.class);
    }

    public ConverseResponse getConverse(String message, String sessionId){
        return getConverse(message, sessionId, null);
    }

    public boolean converse(String message, String sessionId, ChatContext context, Map<String, Object> chatMetadata) {
        logger.debug("start converse, message: {}, session: {}, context {}", message, sessionId, context);
        ChatContext currentContext = context;
        ConverseResponse response = sendRequest(new ConverseRequest(message, sessionId, currentContext), ConverseResponse.class);
        logger.debug("converse response: {}", response);
        if(response.isMessage()) {
            logger.debug("sending message \"{}\" ", response.getMessage());
            messageHandler.sendMessage(response.getMessage(), chatMetadata, response.getQuickreplies());
        } else if(response.isAction() && actionHandlerMap.containsKey(response.getAction())){
            logger.debug("starting action '{}'", response.getAction());
            currentContext = actionHandlerMap.get(response.getAction()).run(response.getEntityMap(), currentContext);
            converse("", sessionId, currentContext, chatMetadata);
        } else if(!response.isStop()){
            return false;
        }
        logger.debug("received 'stop' command");
        return true;
    }

    @Override
    public boolean converse(String message, String sessionId, ChatContext context) {
        return converse(message, sessionId, context, null);
    }

    public ConverseResponse getConverse(String message, String sessionId, ChatContext context){
        return sendRequest(new ConverseRequest(message, sessionId, context), ConverseResponse.class);
    }


}
