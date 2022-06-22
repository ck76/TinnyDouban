package com.ck.tinnydouban.modules.security.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.dto.vo.*;
import com.ck.tinnydouban.modules.security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("user/")
@Api(value = "用户接口类")
public class UserController {


    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Resource
    private UserService userService;

    @Resource
    private HttpServletResponse response;


    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public CommonResult register(@RequestBody @Validated RegisterVO request) throws ApiException {
        String token = userService.register(request);
        response.addHeader(tokenHeader, token);
        return CommonResult.success("注册成功");
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public CommonResult login(@RequestBody @Validated LoginVO request) throws ApiException {
        String token = userService.loginByPhone(request);
        response.addHeader(tokenHeader, token);
        return CommonResult.success("登录成功");
    }


    @PostMapping("/login/sms")
    @ApiOperation(value = "短信验证码登录")
    public CommonResult loginBySms(@RequestBody @Validated LoginSmsVO loginSmsVO) throws ApiException {
        String token = userService.loginBySms(loginSmsVO);
        response.addHeader(tokenHeader, token);
        return CommonResult.success("登录成功");
    }


    @PostMapping("/password/edit")
    @ApiOperation(value = "密码修改")
    public CommonResult editPassword(@RequestBody @Validated EditPasswordVO request) throws ApiException {
        String token = userService.editPassword(request);
        response.addHeader(tokenHeader, token);
        return CommonResult.success("密码修改成功");
    }


    @PostMapping("/password/forget")
    @ApiOperation(value = "忘记密码")
    public CommonResult forgetPassword(@RequestBody @Validated RegisterVO registerVO) throws ApiException {
        String token = userService.forgetPassword(registerVO);
        response.addHeader(tokenHeader, token);
        return CommonResult.success("新密码设置成功");
    }


    @GetMapping("/info/token")
    @ApiOperation(value = "token查看用户信息")
    public CommonResult<UserVO> userInfo() {
        UserVO userVo = userService.getUserInfoByToken();
        return CommonResult.success(userVo);
    }


}
