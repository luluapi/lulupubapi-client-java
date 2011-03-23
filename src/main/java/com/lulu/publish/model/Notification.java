package com.lulu.publish.model;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class Notification {

    private static final Logger LOG = LoggerFactory.getLogger(Notification.class); // NOPMD

    private String code;
    private String severity;
    private String text;
    private Collection<Map<String, String>> details;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<Map<String, String>> getDetails() {
        return details;
    }

    public void setDetails(Collection<Map<String, String>> details) {
        this.details = details;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
