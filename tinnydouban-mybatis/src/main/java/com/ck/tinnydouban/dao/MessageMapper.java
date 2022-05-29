package com.ck.tinnydouban.dao;

import com.ck.tinnydouban.pojo.entity.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    Message selectByPrimaryKey(Long id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);
}
