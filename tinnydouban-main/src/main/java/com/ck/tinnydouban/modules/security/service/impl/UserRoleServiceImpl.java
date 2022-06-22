package com.ck.tinnydouban.modules.security.service.impl;

import com.ck.tinnydouban.dao.RoleMapper;
import com.ck.tinnydouban.dao.UserMapper;
import com.ck.tinnydouban.dao.UserRoleMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.service.UserRoleService;
import com.ck.tinnydouban.pojo.entity.Role;
import com.ck.tinnydouban.pojo.entity.User;
import com.ck.tinnydouban.pojo.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {


    @Resource
    private UserRoleMapper userRoleMapper;


    @Resource
    private UserMapper userMapper;


    @Resource
    private RoleMapper roleMapper;


    public List<UserRole> getAllUserRoleByUid(Integer uid) {
        return userRoleMapper.selectByUid(uid);
    }

    @Override
    public int deleteByUid(Integer uid) {
        return userRoleMapper.deleteByUid(uid);
    }


    public int insert(Integer uid, Integer rid) throws ApiException {

        User user = userMapper.selectByPrimaryKey(uid);
        ApiException.when(user == null, "用户不存在");

        Role role = roleMapper.selectByPrimaryKey(rid);
        ApiException.when(role == null, "角色不存在");

        ApiException.when(userRoleMapper.selectByPrimaryKey(uid, rid) != null, "该关系已存在");

        UserRole userRole = new UserRole();
        userRole.setUserId(uid);
        userRole.setRoleId(rid);
        return userRoleMapper.insert(userRole);
    }


}
