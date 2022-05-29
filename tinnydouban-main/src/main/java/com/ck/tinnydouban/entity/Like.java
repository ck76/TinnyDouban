package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Like implements Serializable {
    private static final long serialVersionUID = -3076352538169770657L;
    private Long id;

    private Integer type;

    private Long postId;

    private Long commentId;

    private Long userId;

    private Boolean status;

    private Date createdTime;

    private Date updatedTime;

}
