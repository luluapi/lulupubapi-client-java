package com.lulu.publish.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum IsbnIntent {
    PROVIDED("provided"),
    ASSIGNED("assigned"),
    NONE("none");

    private static final Map<String, IsbnIntent> LOOKUP = new HashMap<String, IsbnIntent>();

    static {
        for (IsbnIntent type : EnumSet.allOf(IsbnIntent.class)) {
            LOOKUP.put(type.getName(), type);
        }
    }

    public static IsbnIntent lookup(String name) {
        return LOOKUP.get(name);
    }

    private String name;

    IsbnIntent(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
