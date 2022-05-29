package com.ck.tinnydouban.entity;



import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {


    private static final long serialVersionUID = -5118688077648803804L;

    private Integer id;

    private String name;

    private String description;

    private Date createdTime;

    private Date updatedTime;
}
