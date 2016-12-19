package com.featurefactory.witjava.model;

/**
 * Result of converse
 */
public class ConverseResult {

    private boolean isFinished;

    private ChatContext context;

    /**
     * Create result
     * @param isFinished is current conversation is finished
     */
    public ConverseResult(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public ConverseResult(boolean isFinished, ChatContext context) {
        this.isFinished = isFinished;
        this.context = context;
    }

    /**
     * Indicates whether conversation is finished and session could be deleted
     * @return
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Get context object, updated after converse cycle
     * @return
     */
    public ChatContext getContext() {
        return context;
    }
}
