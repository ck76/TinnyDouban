package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Comment implements Serializable {
    private static final long serialVersionUID = -6107298652948670276L;
    private Long id;

    private Long postId;

    private Long userId;

    private Long likeNum;

    private Long replyNum;

    private Boolean status;

    private Date createdTime;

    private Date updatedTime;

    private String content;
}
