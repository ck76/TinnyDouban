package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.UserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFollow record);

    UserFollow selectByPrimaryKey(Long id);

    List<UserFollow> selectAll();

    int updateByPrimaryKey(UserFollow record);


    UserFollow selectByUids(@Param("uid") Long uid,@Param("followUid") Long followUid);

    List<UserFollow> selectFollowingList(Long userId);

    List<UserFollow> selectFollowedList(Long followUserId);

}
