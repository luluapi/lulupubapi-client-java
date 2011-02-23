package com.lulu.publish.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PaperType {
    REGULAR("regular"),
    PUBLISHER_GRADE("publisher-grade"),
    PREMIUM("premium");

    private static final Map<String, PaperType> LOOKUP = new HashMap<String, PaperType>();

    static {
        for (PaperType type : EnumSet.allOf(PaperType.class)) {
            LOOKUP.put(type.getName(), type);
        }
    }

    public static PaperType lookup(String name) {
        return LOOKUP.get(name);
    }

    private String name;

    PaperType(String name) {
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
