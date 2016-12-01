package com.featurefactory.witjava.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ResponseMapper implements ObjectMapper {

    final Logger logger = LoggerFactory.getLogger(ResponseMapper.class);

    private com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

    @Override
    public <T> T readValue(String value, Class<T> valueType) {
        try {
            logger.debug("deserialize response {}", value);
            return mapper.readValue(value, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String writeValue(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
