package com.featurefactory.witjava.impl;



import com.featurefactory.witjava.WitRequest;
import com.featurefactory.witjava.model.ChatContext;
import com.mashape.unirest.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class MessageRequest implements WitRequest {

    private final String endpoint = "/message";
    private final HttpMethod method = HttpMethod.GET;
    private final Map<String, Object> paramMap;

    public MessageRequest(String message) {
        paramMap = new HashMap<>();
        paramMap.put("q", message);
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
        return null;
    }
}
