package com.ck.tinnydouban.modules.social.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.dto.vo.UserVO;
import com.github.pagehelper.PageInfo;


public interface FollowService {


    void followOther(Long followUserId) throws ApiException;

    void cancelFollowOther(Long followUserId) throws ApiException;


    PageInfo<UserVO> queryFollowingUsers(Integer offset, Integer count) throws ApiException;

    PageInfo<UserVO> queryFollowedUsers(Integer offset, Integer count) throws ApiException;

}
