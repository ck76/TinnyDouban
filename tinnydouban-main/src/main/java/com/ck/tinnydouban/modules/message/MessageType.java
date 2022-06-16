package com.ck.tinnydouban.modules.message;


import com.ck.tinnydouban.modules.message.dto.MessageTypeVO;

public enum MessageType {

    SYSTEM(0, "system", ""),
    LIKE(1, "like", "%s 点赞了你"),
    COMMENT(2, "comment", "%s 回复了你的帖子"),
    REPLY(3, "reply", "%s 回复了你的评论"),
    FOLLOW(4, "follow", "%s 关注了你");


    private Integer value;

    private String name;

    private String format;

    private MessageTypeVO vo;

    MessageType(Integer value, String name, String format) {
        this.value = value;
        this.name = name;
        this.format = format;

        vo = new MessageTypeVO();
        vo.setId(value);
        vo.setName(name);
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public MessageTypeVO vo() {
        return vo;
    }

    public String getFormatString(String fromUsername) {
        return String.format(format, fromUsername);
    }

    public boolean check(Integer value) {
        return this.value.equals(value);
    }

    public boolean check(String type) {
        return name.equals(type);
    }
}
