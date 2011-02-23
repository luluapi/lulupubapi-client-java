package com.lulu.publish.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ProductType {
    PRINT("print"),
    DOWNLOAD("download");

    private static final Map<String, ProductType> LOOKUP = new HashMap<String, ProductType>();

    static {
        for (ProductType type : EnumSet.allOf(ProductType.class)) {
            LOOKUP.put(type.getName(), type);
        }
    }

    public static ProductType lookup(String name) {
        return LOOKUP.get(name);
    }

    private String name;

    ProductType(String name) {
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
