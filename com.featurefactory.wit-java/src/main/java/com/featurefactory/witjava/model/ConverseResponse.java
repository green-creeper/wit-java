package com.featurefactory.witjava.model;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverseResponse {

    private String type;
    @JsonProperty("entities")
    private Map<String, List<Map<String, Object>>> entityMap = new HashMap<>();
    private Double confidence;
    private String action;
    @JsonProperty("msg")
    private String message;
    private String[] quickreplies;

    @JsonAnySetter
    private void setValue(String name, List<Map<String, Object>> val) {
        entityMap.put(name, val);
    }

    /**
     * Type of response
     * @return could be action, msg, merge of stop
     */
    public String getType() {
        return type;
    }

    /**
     * @return Entities extracted from user message
     */
    public Map<String, List<Map<String, Object>>> getEntityMap() {
        return entityMap;
    }

    /**
     * @return level of confidence that message parsed in appropriate way
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * @return name of the action in case of action type
     */
    public String getAction() {
        return action;
    }

    /**
     * Message of text to send in case of msg type
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Quick replies
     * @return
     */
    public String[] getQuickreplies() {
        return quickreplies;
    }

    /**
     * is message type
     * @return
     */
    public boolean isMessage() {
        return type.equalsIgnoreCase("msg");
    }

    /**
     * is action type
     * @return
     */
    public boolean isAction(){
        return type.equalsIgnoreCase("action");
    }

    /**
     * is chat ended
     * @return
     */
    public boolean isStop() {
        return type.equalsIgnoreCase("stop");
    }
    @Override
    public String toString() {
        return "ConverseResponse{" +
                "type='" + type + '\'' +
                ", entityMap=" + entityMap +
                ", confidence=" + confidence +
                ", action='" + action + '\'' +
                ", message='" + message + '\'' +
                ", quickreplies=" + Arrays.toString(quickreplies) +
                '}';
    }
}
