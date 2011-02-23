package com.lulu.publish.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TrimSize {
    US_LETTER("US_LETTER"), US_TRADE("US_TRADE"), COMIC("COMIC"), POCKET("POCKET"), LANDSCAPE("LANDSCAPE"),
    SQUARE("SQUARE"), SIZE_825x1075("SIZE_825x1075"), ROYAL("ROYAL"), CROWN_QUARTO("CROWN_QUARTO"), A4("A4"),
    LARGE_SQUARE("LARGE_SQUARE"), A5("A5"), DIGEST("DIGEST"), SIZE_1275x1075("SIZE_1275x1075"), SIZE_12x12("SIZE_12x12");

    private static final Map<String, TrimSize> LOOKUP = new HashMap<String, TrimSize>();

    static {
        for (TrimSize type : EnumSet.allOf(TrimSize.class)) {
            LOOKUP.put(type.getName(), type);
        }
    }

    public static TrimSize lookup(String name) {
        return LOOKUP.get(name);
    }

    private String name;

    TrimSize(String name) {
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
