package com.ck.tinnydouban.modules.message.service.impl;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.message.MessageType;
import com.ck.tinnydouban.modules.message.dto.MessageTypeVO;
import com.ck.tinnydouban.modules.message.dto.MessageVO;
import com.ck.tinnydouban.modules.message.service.MessageService;
import com.ck.tinnydouban.modules.security.service.UserService;
import com.ck.tinnydouban.pojo.entity.Message;
import com.ck.tinnydouban.pojo.entity.User;
import com.ck.tinnydouban.dao.MessageMapper;
import com.ck.tinnydouban.dao.UserMapper;

import com.ck.tinnydouban.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;


    /**
     * 创建消息，插入到数据库
     */
    @Override
    public void createMessage(MessageType type, Long typeId, Long fromUserId, Long sendUserId) {
        Message message = new Message();
        message.setType(type.getValue());
        message.setTypeId(typeId);
        message.setFromUserId(fromUserId);
        message.setSendUserId(sendUserId);
        message.setHasRead(false);
        message.setCreatedTime(DateUtil.currentDate());
        message.setUpdatedTime(DateUtil.currentDate());

        User fromUser = userMapper.selectByPrimaryKey(Math.toIntExact(fromUserId));
        message.setMessage(type.getFormatString(fromUser.getName()));
        messageMapper.insert(message);
    }

    /**
     * 创建系统消息
     */
    @Override
    public void createSystemMessage(String msg) throws ApiException {


        Message message = new Message();
        message.setType(MessageType.SYSTEM.getValue());
        Long uid = userService.currentUserId();
        message.setFromUserId(uid);
        message.setHasRead(false);
        message.setCreatedTime(DateUtil.currentDate());
        message.setUpdatedTime(DateUtil.currentDate());
        message.setMessage(msg);

        // 系统消息不产生事件，也不针对固定用户
        message.setTypeId(0L);
        message.setSendUserId(0L);

        ApiException.when(messageMapper.insert(message) == 0, "插入失败");
    }


    /**
     * 读取消息，设置为已经读取并且更新数据库
     */
    @Override
    public void readMessage(Long msgId) throws ApiException {

        Message message = messageMapper.selectByPrimaryKey(msgId);
        ApiException.when(message == null, "消息不存在");

        // 若当前为未读状态则修改为已读
        if (!message.getHasRead()) {
            message.setHasRead(true);
            message.setUpdatedTime(DateUtil.currentDate());
            messageMapper.updateByPrimaryKey(message);
        }

    }


    @Override
    public List<MessageTypeVO> queryAllMessageType() {

        List<MessageTypeVO> voList = new ArrayList<>();

        voList.add(MessageType.SYSTEM.vo());
        voList.add(MessageType.LIKE.vo());
        voList.add(MessageType.COMMENT.vo());
        voList.add(MessageType.REPLY.vo());
        voList.add(MessageType.FOLLOW.vo());

        return voList;
    }

    @Override
    public PageInfo<MessageVO> queryMessagesByType(Integer typeId, Integer offset, Integer count) throws ApiException {

        Long uid = userService.currentUserId();

        PageHelper.offsetPage(offset, count);
        List<Message> messageList;

        if (MessageType.SYSTEM.check(typeId)) {
            messageList = messageMapper.selectByType(typeId);
        } else {
            messageList = messageMapper.selectTypeAndUser(typeId, uid);
        }

        List<MessageVO> voList = messageList.stream()
                .map(message -> {
                    MessageVO vo = new MessageVO();
                    BeanUtils.copyProperties(message, vo);
                    vo.setSendTime(message.getCreatedTime().toString());

                    return vo;
                })
                .collect(Collectors.toList());

        return new PageInfo<>(voList);
    }


}
