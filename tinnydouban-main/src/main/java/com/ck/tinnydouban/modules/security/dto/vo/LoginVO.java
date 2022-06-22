package com.ck.tinnydouban.modules.security.dto.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.*;


@Data
public class LoginVO {


    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Size(min = 11, max = 11, message = "手机号只能为11位")
    @ApiParam(value = "手机号")
    @JsonProperty(value = "phone_number")
    String phone;


    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 16, message = "请确保密码为8~16位")
    @ApiParam(value = "用户密码")
    @JsonProperty(value = "password")
    String password;

}
