package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Like;

import java.util.List;

public interface LikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Like record);

    Like selectByPrimaryKey(Long id);

    List<Like> selectAll();

    int updateByPrimaryKey(Like record);
}
