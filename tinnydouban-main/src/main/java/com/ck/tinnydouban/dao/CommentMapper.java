package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPostId(Long postId);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    List<Comment> selectByPost(Long postId);

    int updateByPrimaryKey(Comment record);
}
