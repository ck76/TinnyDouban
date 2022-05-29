package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Language implements Serializable {

    private static final long serialVersionUID = 1189478234518045992L;
    private Long id;

    private String languageName;

    private Date createdTime;

    private Date updatedTime;

}
