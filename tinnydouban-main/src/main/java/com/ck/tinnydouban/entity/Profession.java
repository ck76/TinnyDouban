package com.ck.tinnydouban.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Profession implements Serializable {

    private static final long serialVersionUID = -7165090256947754204L;

    private Long id;

    private String name;

    private Date createdTime;

    private Date updatedTime;


}
