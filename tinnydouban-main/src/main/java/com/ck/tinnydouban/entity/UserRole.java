package com.ck.tinnydouban.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = -5243457377587528628L;

    private Integer userId;

    private Integer roleId;
}
