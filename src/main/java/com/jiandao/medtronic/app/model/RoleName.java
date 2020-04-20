package com.jiandao.medtronic.app.model;


import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleName {
    ROLE_ADMIN("admin"),
    ROLE_DEV("dev"),
    ROLE_USER("user"),
    ROLE_PUBLIC("public");

    private String shortName;

    RoleName (String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return shortName;
    }

    @JsonCreator
    public static RoleName create (String value) {
        if(value == null) {
            throw new IllegalArgumentException();
        }
        for(RoleName v : values()) {
            if(value.equalsIgnoreCase(v.getShortName())) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getShortName() {
        return shortName;
    }
}