package com.ck.tinnydouban.modules.security.service;


import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.dto.vo.*;

public interface UserService {


    String register(RegisterVO registerVO) throws ApiException;

    String loginByPhone(LoginVO loginVO) throws ApiException;

    String loginBySms(LoginSmsVO loginSmsVO) throws ApiException;

    String editPassword(EditPasswordVO editPasswordVO) throws ApiException;

    String forgetPassword(RegisterVO registerVO) throws ApiException;


    int deleteUserById(Integer id) throws ApiException;

    int lockByUid(Integer id) throws ApiException;

    int unlockByUid(Integer id) throws ApiException;


    UserVO getUserInfoByToken();

    UserVO getUserInfoById(Integer id);

    Long currentUserId() throws ApiException;


}
