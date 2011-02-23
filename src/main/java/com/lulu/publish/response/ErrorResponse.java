package com.lulu.publish.response;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 */
public class ErrorResponse {

    @JsonProperty("error_type")
    private String errorType;

    @JsonProperty("error_value")
    private String errorValue;

    @JsonProperty("additional")
    private String additional;

    @JsonProperty("traceback")
    private String traceback;

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(String errorValue) {
        this.errorValue = errorValue;
    }

    public String getTraceback() {
        return traceback;
    }

    public void setTraceback(String traceback) {
        this.traceback = traceback;
    }

    @Override
    public String toString() {
        return "ErrorResponse{"
                + "additional='" + additional + '\''
                + ", errorType='" + errorType + '\''
                + ", errorValue='" + errorValue + '\''
                + ", traceback='" + traceback + '\''
                + '}';
    }
}
