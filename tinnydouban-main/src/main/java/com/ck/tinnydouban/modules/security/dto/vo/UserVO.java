package com.ck.tinnydouban.modules.security.dto.vo;

import lombok.Data;

import java.util.Date;


@Data
public class UserVO {

    private Integer id;

    private String phone;

    private String name;

    private String coverUrl;

    private Boolean status;
}
