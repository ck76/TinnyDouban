package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;



@Data
@ToString
public class UserFollow implements Serializable {

    private static final long serialVersionUID = 8791275325683630202L;

    private Long id;

    private Long userId;

    private Long followUserId;

    private Boolean follow;

    private Date createdTime;

    private Date updatedTime;
}
