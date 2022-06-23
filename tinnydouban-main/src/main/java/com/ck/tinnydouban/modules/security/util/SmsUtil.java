package com.ck.tinnydouban.modules.security.util;

import com.ck.tinnydouban.exception.ApiException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class SmsUtil {

//    @Value("${sms.templateId}")
//    private String templateId;
//
//    @Value("${sms.sdkAppId}")
//    private String sdkAppId;
//
//    @Value("${sms.sign}")
//    private String sign;
//
//    @Value("${sms.regionCode}")
//    private String regionCode;
//
//    @Value("${sms.secretId}")
//    private String secretId;
//
//    @Value("${sms.secretKey}")
//    private String secretKey;
//
//    @Value("${sms.expiration}")
//    private Long expiration;


    /**
     * 发送验证码
     *
     * @param phone   手机号
     * @param captcha 验证码
     * @return
     * @throws TencentCloudSDKException
     */
    public Boolean sendMessage(String phone, String captcha) throws TencentCloudSDKException, ApiException {

//
//        Credential cred = new Credential(secretId, secretKey);
//        HttpProfile httpProfile = new HttpProfile();
//        httpProfile.setEndpoint("sms.tencentcloudapi.com");
//
//        ClientProfile clientProfile = new ClientProfile();
//        clientProfile.setHttpProfile(httpProfile);
//
//        SmsClient client = new SmsClient(cred, "", clientProfile);
//        SendSmsRequest req = new SendSmsRequest();
//
//        String phoneNumber = regionCode + phone;
//        String[] phoneNumberSet = {phoneNumber};
//        req.setPhoneNumberSet(phoneNumberSet);
//
//        String[] templateParamSet1 = {captcha, String.valueOf(expiration)};
//        req.setTemplateParamSet(templateParamSet1);
//
//        req.setTemplateID(templateId);
//        req.setSmsSdkAppid(sdkAppId);
//        req.setSign(sign);
//
//        SendSmsResponse resp = client.SendSms(req);
////        log.info(SendSmsResponse.toJsonString(resp));
//
//
//        SendStatus[] statusSet = resp.getSendStatusSet();
//        SendStatus status = statusSet[0];
//        ApiException.when(!"Ok".equals(status.getCode()),"验证码发送失败:" + status.getMessage());

        return true;
    }


    /**
     * 生成6位数随机验证码
     *
     * @return captcha
     */
    public String generateCaptchaCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }


}
