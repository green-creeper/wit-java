package com.featurefactory.witjava.impl;


import com.featurefactory.witjava.WitRequest;
import com.featurefactory.witjava.model.ChatContext;
import com.mashape.unirest.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;


public class ConverseRequest implements WitRequest {

    private final String endpoint = "/converse";
    private final HttpMethod method = HttpMethod.POST;
    private final Map<String, Object> paramMap;
    private final ChatContext context;

    public ConverseRequest(String message, String sessionId, ChatContext context) {
        this.context = context;
        this.paramMap = new HashMap<>();
        paramMap.put("q", message);
        if(sessionId!=null) {
            paramMap.put("session_id", sessionId);
        }
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public HttpMethod getMethod() {
        return method;
    }

    @Override
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    @Override
    public ChatContext getContext() {
        return context;
    }
}
