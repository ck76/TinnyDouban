package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class MovieLanguage implements Serializable {
    private static final long serialVersionUID = 6039081932711828633L;
    private Long id;

    private Long movieId;

    private Long movieDoubanId;

    private Long languageId;

    private Date createdTime;

    private Date updatedTime;

}
