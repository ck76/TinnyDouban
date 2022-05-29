package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class FilmmakerProfession implements Serializable {

    private static final long serialVersionUID = -3953472382928019339L;

    private Long id;

    private Long filmmakerId;

    private Long filmmakerDoubanId;

    private Long professionId;

    private Date createdTime;

    private Date updatedTime;
}
