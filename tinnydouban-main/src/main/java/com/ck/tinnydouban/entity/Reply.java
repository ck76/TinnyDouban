package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Reply implements Serializable {

    private static final long serialVersionUID = 8359067353888055474L;
    private Long id;

    private Integer replyType;

    private Long commentId;

    private Long replyId;

    private Long userId;

    private Long replyUserId;

    private Boolean status;

    private Date createdTime;

    private Date updatedTime;

    private String content;

}
