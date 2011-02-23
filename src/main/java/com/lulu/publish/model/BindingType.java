package com.lulu.publish.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BindingType {
    COIL("coil"),
    PERFECT("perfect"),
    SADDLE_STITCH("saddle-stitch"),
    CASEWRAP_HARDCOVER("casewrap-hardcover"),
    JACKET_HARDCOVER("jacket-hardcover");

    private static final Map<String, BindingType> LOOKUP = new HashMap<String, BindingType>();

    static {
        for (BindingType type : EnumSet.allOf(BindingType.class)) {
            LOOKUP.put(type.getName(), type);
        }
    }

    public static BindingType lookup(String name) {
        return LOOKUP.get(name);
    }

    private String name;

    BindingType(String name) {
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
