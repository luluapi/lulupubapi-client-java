package com.lulu.publish.model;

/**
 * Descriptor for the context of a file.
 */
public enum FileContext {

    COVER("cover"),
    CONTENTS("contents");

    private String context;

    private FileContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return this.context;
    }
}
