package com.ck.tinnydouban.modules.security.service;


import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.pojo.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> getAllUserRoleByUid(Integer uid);

    int deleteByUid(Integer uid);

    int insert(Integer uid, Integer rid) throws ApiException;
}
