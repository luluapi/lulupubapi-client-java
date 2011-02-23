package com.lulu.publish.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum AccessType {
    PRIVATE("private"), PUBLIC("public"), DIRECT("direct");

    private static final Map<String, AccessType> LOOKUP = new HashMap<String, AccessType>();

    static {
        for (AccessType type : EnumSet.allOf(AccessType.class)) {
            LOOKUP.put(type.getName(), type);
        }
    }

    public static AccessType lookup(String name) {
        return LOOKUP.get(name);
    }

    private String name;

    AccessType(String name) {
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
