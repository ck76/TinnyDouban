package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
}
