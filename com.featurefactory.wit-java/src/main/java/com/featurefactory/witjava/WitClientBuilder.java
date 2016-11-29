package com.featurefactory.witjava;

import com.featurefactory.witjava.impl.WitClientImpl;
import com.featurefactory.witjava.model.ChatContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder to build {@link WitClient}
 */
public class WitClientBuilder {
    private String token;
    private Map<String, ActionHandler> actionHandlerMap;
    private MessageHandler messageHandler;

    /**
     * set wit.ai token
     * @param witToken token which could be found on wit.ai website
     */
    public WitClientBuilder(String witToken) {
        this.token = witToken;
        actionHandlerMap = new HashMap<>();
    }

    /**
     * Adds action handler which will be executed from {@link WitClient#converse(String, String, ChatContext)} when action command is received
     * @param actionName method ID as set in wit.ai story
     * @param actionHandler User implementation of {@link ActionHandler}
     */
    public WitClientBuilder addActionHandler(String actionName, ActionHandler actionHandler){
        actionHandlerMap.put(actionName, actionHandler);
        return this;
    }

    /**
     * Sets handler which will be executed from {@link WitClient#converse(String, String, ChatContext)} when msg command is received
     * @param messageHandler user implementation of {@link MessageHandler}
     */
    public WitClientBuilder setMessageHandler(MessageHandler messageHandler){
        this.messageHandler = messageHandler;
        return this;
    }

    /**
     * Build client
     * @return instance of {@link WitClient}
     */
    public WitClient build(){
        return new WitClientImpl(token, actionHandlerMap, messageHandler);
    }
}
