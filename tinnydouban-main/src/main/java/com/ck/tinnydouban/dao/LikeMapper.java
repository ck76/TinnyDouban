package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Like record);

    Like selectByPrimaryKey(Long id);

    Like selectByPostId(@Param("uid") Long uid, @Param("post_id") Long postId);

    Like selectByCommentId(@Param("uid") Long uid, @Param("comment_id") Long commentId);

    List<Like> selectAll();

    int updateByPrimaryKey(Like record);
}
