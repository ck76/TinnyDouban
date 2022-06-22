package com.ck.tinnydouban.modules.security.dto.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginSmsVO {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Size(min = 11, max = 11, message = "手机号只能为11位")
    @ApiParam(value = "手机号")
    @JsonProperty(value = "phone_number")
    String phone;


    @NotBlank(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "请确保6位验证码")
    @ApiParam(value = "短信验证码")
    @JsonProperty(value = "captcha")
    String captcha;
}
