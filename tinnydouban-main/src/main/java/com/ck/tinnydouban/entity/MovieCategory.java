package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class MovieCategory implements Serializable {
    private static final long serialVersionUID = -7136239910545461457L;
    private Long id;

    private Long movieId;

    private Long movieDoubanId;

    private Long categoryId;

    private Date createdTime;

    private Date updatedTime;

}
