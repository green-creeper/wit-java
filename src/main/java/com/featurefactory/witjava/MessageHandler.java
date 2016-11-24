package com.featurefactory.witjava;


import java.util.Map;

/**
 * Handler which should be triggered in case of msg action
 */
public interface MessageHandler {

    /**
     * Sends message
     * @param message text of message to send
     * @param chatMetadata additional data from client side which could be used in process of sending messages
     */
    void sendMessage(String message, Map<String, Object> chatMetadata);
}
