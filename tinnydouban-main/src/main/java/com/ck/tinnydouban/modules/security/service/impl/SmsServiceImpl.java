package com.ck.tinnydouban.modules.security.service.impl;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.service.SmsService;
import com.ck.tinnydouban.modules.security.util.RedisUtil;
import com.ck.tinnydouban.modules.security.util.SmsUtil;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SmsServiceImpl implements SmsService {


    @Value("${sms.expiration}")
    private Long expiration;

    @Resource
    private SmsUtil smsUtil;


    @Resource
    private RedisUtil redisUtil;


    @Override
    public Boolean sendMessage(String phone) throws TencentCloudSDKException, ApiException {

        // 生成验证码
        String captcha = smsUtil.generateCaptchaCode();
        // 验证码存入redis, 并设置验证码有效时间
        redisUtil.setStrValue(phone, captcha);
        redisUtil.setMinuteExpire(phone, expiration);
        // 发送验证码
        return smsUtil.sendMessage(phone, captcha);
    }


}
