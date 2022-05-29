package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Filmmaker implements Serializable {

    private static final long serialVersionUID = 4387178806218084436L;

    private Long id;

    private Long doubanId;

    private String name;

    private String nameEn;

    private String nameZh;

    private String sex;

    private Date birth;

    private String birthPlace;

    private String constellation;

    private Date createdTime;

    private Date updatedTime;

    private String biography;
}
