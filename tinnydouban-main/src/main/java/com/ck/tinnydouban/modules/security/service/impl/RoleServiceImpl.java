package com.ck.tinnydouban.modules.security.service.impl;

import com.ck.tinnydouban.dao.RoleMapper;
import com.ck.tinnydouban.modules.security.service.RoleService;
import com.ck.tinnydouban.pojo.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public Role queryById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
