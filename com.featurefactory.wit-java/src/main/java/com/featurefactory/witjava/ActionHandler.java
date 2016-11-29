package com.featurefactory.witjava;

import com.featurefactory.witjava.model.ChatContext;

import java.util.List;
import java.util.Map;

/**
 * Handler for action execution.
 */
public interface ActionHandler {

    /**
     * Execution of action
     * @param entities entities extracted from user message
     * @param context context
     * @return modified context with action result
     */
    ChatContext run(Map<String, List<Map<String, Object>>> entities, ChatContext context);
}
