package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    Post selectByPrimaryKey(Long id);

    List<Post> selectByTopic(Long topicId);

    List<Post> selectAll();

    int updateByPrimaryKey(Post record);

    int deleteAllPostByTopic(Long topicId);

    List<Post> selectNum(Integer num);
}
