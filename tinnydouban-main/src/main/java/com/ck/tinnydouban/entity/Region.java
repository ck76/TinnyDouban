package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Region implements Serializable {
    private static final long serialVersionUID = 1626454459704410765L;
    private Long id;

    private String regionName;

    private Date createdTime;

    private Date updatedTime;

}
