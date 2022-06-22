package com.ck.tinnydouban.modules.security.dto;

import org.omg.CORBA.PUBLIC_MEMBER;


public enum Permission {


    ADMIN(1, "ADMIN","超级管理员，拥有所有权限"),

    USER(2, "USER","普通注册用户");


    private Integer id;

    private String name;

    private String description;



    private Permission(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }
}
