package com.featurefactory.witjava.model;

/**
 * Result of converse
 */
public class ConverseResult {

    private boolean isFinished;

    /**
     * Create result
     * @param isFinished is current conversation is finished
     */
    public ConverseResult(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * Indicates whether conversation is finished and session could be deleted
     * @return
     */
    public boolean isFinished() {
        return isFinished;
    }
}
