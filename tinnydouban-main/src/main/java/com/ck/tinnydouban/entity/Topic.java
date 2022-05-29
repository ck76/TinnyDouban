package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Topic implements Serializable {
    private static final long serialVersionUID = 184352294995019825L;
    private Long id;

    private String name;

    private Integer topicType;

    private Long movieId;

    private Long createUserId;

    private Long postNum;

    private Long userNum;

    private Date createdTime;

    private Date updatedTime;

    private String topicAbstract;
}
