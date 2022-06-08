package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    Message selectByPrimaryKey(Long id);

    List<Message> selectAll();

    List<Message> selectTypeAndUser(@Param("type_id") Integer typeId, @Param("uid") Long uid);

    List<Message> selectByType(Integer typeId);

    int updateByPrimaryKey(Message record);
}
