package com.lulu.publish.model;

import java.io.Serializable;

/**
 * Enumeration of status a Conversion may have.
 */
public enum ConversionStatus implements Serializable {

    CONVERTING("Converting", false),
    FINALIZING("Finalizing", false),
    COMPLETED("Completed", true),
    CANCELLED("Cancelled", true),
    CANCELLED_DUE_TO_TIMEOUT("Cancelled Due To Time Out", true),
    ERROR("Error", true);

    private String defaultStatusMessage;
    private boolean isTerminalState;

    ConversionStatus(String defaultStatusMessage, boolean isTerminalState) {
        this.defaultStatusMessage = defaultStatusMessage;
        this.isTerminalState = isTerminalState;
    }

    public String getDefaultStatusMessage() {
        return defaultStatusMessage;
    }

    public Boolean isTerminalState() {
        return isTerminalState;
    }
}

