package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Category implements Serializable {

    private static final long serialVersionUID = -7967678198077499630L;

    private Long id;

    private String categoryName;

    private Date createdTime;

    private Date updatedTime;

}
