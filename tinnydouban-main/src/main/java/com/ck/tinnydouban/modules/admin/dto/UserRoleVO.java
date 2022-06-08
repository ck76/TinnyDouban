package com.ck.tinnydouban.modules.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class UserRoleVO {


    @JsonProperty(value = "user_id")
    private Integer userId;


    @JsonProperty(value = "role_id")
    private Integer roleId;

}
