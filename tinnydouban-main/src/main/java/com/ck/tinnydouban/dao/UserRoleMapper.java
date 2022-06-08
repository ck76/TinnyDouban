package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int deleteByUid(@Param("userId") Integer userId);

    UserRole selectByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insert(UserRole record);

    List<UserRole> selectAll();

    List<UserRole> selectByUid(Integer userId);
}
