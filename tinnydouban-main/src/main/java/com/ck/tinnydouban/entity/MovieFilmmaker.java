package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class MovieFilmmaker implements Serializable {
    private static final long serialVersionUID = 2116143173817886745L;
    private Long id;

    private Long movieId;

    private Long movieDoubanId;

    private Long filmmakerId;

    private Long filmmakerDoubanId;

    private Long professionId;

    private Date createdTime;

    private Date updatedTime;

}
