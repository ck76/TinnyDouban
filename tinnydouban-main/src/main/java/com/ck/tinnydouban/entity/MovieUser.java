package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class MovieUser implements Serializable {
    private static final long serialVersionUID = -7468952175429053767L;

    private Long id;

    private Long userId;

    private Long movieId;

    private Long movieDoubanId;

    private Boolean wannaSee;

    private Boolean haveSeen;

    private Double score;

    private Date wannaSeeTime;

    private Date haveSeenTime;

    private Date scoreTime;

    private Date createdTime;

    private Date updatedTime;

}
