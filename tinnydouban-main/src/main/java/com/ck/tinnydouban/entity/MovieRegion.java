package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class MovieRegion implements Serializable {
    private static final long serialVersionUID = 8027418923546953096L;
    private Long id;

    private Long movieId;

    private Long movieDoubanId;

    private Long regionId;

    private Date createdTime;

    private Date updatedTime;

}
