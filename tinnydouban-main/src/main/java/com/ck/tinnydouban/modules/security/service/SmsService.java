package com.ck.tinnydouban.modules.security.service;

import com.ck.tinnydouban.exception.ApiException;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;


public interface SmsService {


    Boolean sendMessage(String phone) throws TencentCloudSDKException, ApiException;

}
