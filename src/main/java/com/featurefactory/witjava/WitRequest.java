package com.featurefactory.witjava;


import com.featurefactory.witjava.model.ChatContext;
import com.mashape.unirest.http.HttpMethod;

import java.util.Map;

/**
 * Request to API
 */
public interface WitRequest {

    /**
     * Location of api command without http://wit.ai
     * @return api method
     */
    String getEndpoint();

    /**
     * @return HTTP method to execute
     */
    HttpMethod getMethod();

    /**
     * URL params
     * @return
     */
    Map<String, Object > getParamMap();

    /**
     * Context {@link ChatContext}
     * @return
     */
    ChatContext getContext();
}
