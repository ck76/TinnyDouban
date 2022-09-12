package com.ck.tinnydouban.modules.social.config;


public enum LikeType {

    /* 帖子点赞类型 */
    POST(0, "post_like"),
    /* 评论点赞类型 */
    COMMENT(1, "comment_like");


    private Integer value;

    private String name;

    LikeType(Integer value, String name) {
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

    public boolean check(String str) {
        return name.equals(str);
    }
}
