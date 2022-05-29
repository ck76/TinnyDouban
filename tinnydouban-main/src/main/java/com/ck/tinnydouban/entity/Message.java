package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = -8144979336748233056L;

    private Long id;

    private Integer type;

    private Long typeId;

    private Long fromUserId;

    private Long sendUserId;

    private Boolean hasRead;

    private Date createdTime;

    private Date updatedTime;

    private String message;
}
