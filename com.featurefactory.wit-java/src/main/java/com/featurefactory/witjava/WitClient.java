package com.featurefactory.witjava;


import com.featurefactory.witjava.model.ChatContext;
import com.featurefactory.witjava.model.ConverseResponse;
import com.featurefactory.witjava.model.MessageResponse;

import java.util.Map;

/**
 * Wit.ai client. Could be initialized with {@link WitClientBuilder}
 */
public interface WitClient {

    /***
     * Send request to wit.ai API
     * @param request request to send
     * @param valueType expected return type of response object
     * @return response object
     */
    <T> T sendRequest(WitRequest request, Class<T> valueType);

    /**
     * Get meaning of the message. sends <a href="https://wit.ai/docs/http/20160526#get--message-link">/message request</a>
     * @param message text of user message
     * @return explained message
     */
    MessageResponse getMeaning(String message);

    /**
     * Get next step to perform by bot see <a href="https://wit.ai/docs/http/20160526#post--converse-link">/converse</a>
     * @param message user message
     * @param sessionId unique session ID <a href="https://wit.ai/docs/recipes#integrate-link">see official documentation</a>
     * @return
     */
    ConverseResponse getConverse(String message, String sessionId);

    /**
     * Get next step to perform by bot see <a href="https://wit.ai/docs/http/20160526#post--converse-link">/converse</a>
     * @param message user message
     * @param sessionId unique session ID <a href="https://wit.ai/docs/recipes#integrate-link">see official documentation</a>
     * @param context context object {@link ChatContext}
     * @return Object with explained message and next step
     */
    ConverseResponse getConverse(String message, String sessionId, ChatContext context);


    boolean converse(String message, String sessionId, ChatContext context);

    /**
     * Execute converse and performs next step, invoking {@link MessageHandler} or {@link ActionHandler}
     * Execution stops only awaiting user input of when stop command is received
     * @param message text message from user
     * @param sessionId unique session ID <a href="https://wit.ai/docs/recipes#integrate-link">see official documentation</a>
     * @param context context object {@link ChatContext}
     * @param chatMetadata Object for MessageHandler to pass parameters
     * @return returns true if current chat is continues (e.g. waiting for user input) or ends and the session should expire
     */
    boolean converse(String message, String sessionId, ChatContext context, Map<String, Object> chatMetadata);
}
