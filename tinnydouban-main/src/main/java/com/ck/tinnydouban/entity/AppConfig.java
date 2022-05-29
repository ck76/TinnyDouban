package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
public class AppConfig implements Serializable {

    private static final long serialVersionUID = -6198843822949596699L;

    private Long id;

    private Integer mainVersionCode;

    private Integer subVersionCode;

    private Integer grayVersionCode;

    private String versionCode;

    private String resourceUrl;

    private Date createdTime;

    private Date updatedTime;

    private String updateInfo;
}
