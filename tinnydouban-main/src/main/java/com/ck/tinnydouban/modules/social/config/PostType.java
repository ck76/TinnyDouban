package com.ck.tinnydouban.modules.social.config;

public enum  PostType {

    MOVIE(1, "movie"),
    COMMON(0, "common");


    private Integer value;

    private String name;

    PostType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }


    public String getName() {
        return name;
    }

    public boolean check(Integer type) {
        return value.equals(type);
    }
}
