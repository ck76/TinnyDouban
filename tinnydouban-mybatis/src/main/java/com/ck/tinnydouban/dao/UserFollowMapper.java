package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.UserFollow;

import java.util.List;

public interface UserFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFollow record);

    UserFollow selectByPrimaryKey(Long id);

    List<UserFollow> selectAll();

    int updateByPrimaryKey(UserFollow record);
}
