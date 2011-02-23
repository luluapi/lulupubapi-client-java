package com.lulu.publish.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ProjectType {
    HARDCOVER("hardcover"),
    SOFTCOVER("softcover"),
    EBOOK("ebook");

    private static final Map<String, ProjectType> LOOKUP = new HashMap<String, ProjectType>();

    static {
        for (ProjectType type : EnumSet.allOf(ProjectType.class)) {
            LOOKUP.put(type.getName(), type);
        }
    }

    public static ProjectType lookup(String name) {
        return LOOKUP.get(name);
    }

    private String name;

    ProjectType(String name) {
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
