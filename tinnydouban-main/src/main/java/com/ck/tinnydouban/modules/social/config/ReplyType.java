package com.ck.tinnydouban.modules.social.config;


public enum ReplyType {

    TO_COMMENT(1, "to_comment"),
    TO_REPLY(0, "to_reply");


    private Integer value;

    private String name;

    ReplyType(Integer value, String name) {
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
