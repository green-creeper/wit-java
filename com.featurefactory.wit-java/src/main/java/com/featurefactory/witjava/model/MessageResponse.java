package com.featurefactory.witjava.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageResponse {

    @JsonProperty("msg_id")
    private String messageId;
    @JsonProperty("_text")
    private String text;
    @JsonProperty("entities")
    private Map<String, List<Map<String, Object>>> entityMap = new HashMap<>();

    @SuppressWarnings(value = "unused")
    public MessageResponse() {
    }

    /**
     * Original message ID
     * @return
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Original message text from user
     * @return
     */
    public String getText() {
        return text;
    }

    @JsonAnySetter
    private void setValue(String name, List<Map<String, Object>> val) {
        entityMap.put(name, val);
    }

    /**
     * Set of entities extracted from user message
     * @return
     */
    public Map<String, List<Map<String, Object>>> getEntityMap() {
        return entityMap;
    }

    /**
     * get particular entity
     * @param name name of the entity. As set on wit.ai stories
     * @return
     */
    public List<Map<String, Object>> getEntity(String name){
        return entityMap.get(name);
    }
}
