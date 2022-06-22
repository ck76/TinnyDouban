package com.ck.tinnydouban.modules.security.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.service.SmsService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("sms/")
@Validated
@Api(value = "短信接口类")
public class SmsController {


    @Resource
    private SmsService smsService;


    @GetMapping("captcha/{phone_number}")
    @ApiOperation(value = "验证码发送")
    CommonResult sendMessage(@ApiParam(value = "手机号")
                             @NotBlank(message = "手机号不能为空")
                             @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
                             @Size(min = 11, max = 11, message = "手机号只能为11位")
                             @PathVariable("phone_number") String phone) throws TencentCloudSDKException, ApiException {
        ApiException.when(!smsService.sendMessage(phone), "验证码发送失败");
        return CommonResult.success("验证码发送成功");
    }


}
