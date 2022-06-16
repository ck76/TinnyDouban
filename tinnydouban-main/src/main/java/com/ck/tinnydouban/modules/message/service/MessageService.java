package com.ck.tinnydouban.modules.message.service;


import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.message.MessageType;
import com.ck.tinnydouban.modules.message.dto.MessageTypeVO;
import com.ck.tinnydouban.modules.message.dto.MessageVO;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;

import java.util.List;


public interface MessageService {


    List<MessageTypeVO> queryAllMessageType();


    PageInfo<MessageVO> queryMessagesByType(Integer typeId, Integer offset, Integer count) throws ApiException;


    void createMessage(MessageType type, Long typeId, Long fromUserId, Long sendUserId);


    void createSystemMessage(String msg) throws ApiException;


    void readMessage(Long msgId) throws ApiException;



}
