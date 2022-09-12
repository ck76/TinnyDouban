package com.ck.tinnydouban.modules.social.service.impl;

import com.ck.tinnydouban.dao.CommentMapper;
import com.ck.tinnydouban.dao.PostMapper;
import com.ck.tinnydouban.dao.ReplyMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.message.MessageType;
import com.ck.tinnydouban.modules.message.service.MessageService;
import com.ck.tinnydouban.modules.security.service.UserService;
import com.ck.tinnydouban.modules.social.config.ReplyType;
import com.ck.tinnydouban.modules.social.dto.reply.ReplyParam;
import com.ck.tinnydouban.modules.social.dto.reply.ReplyVO;
import com.ck.tinnydouban.modules.social.service.ReplyService;
import com.ck.tinnydouban.pojo.entity.Comment;
import com.ck.tinnydouban.pojo.entity.Post;
import com.ck.tinnydouban.pojo.entity.Reply;
import com.ck.tinnydouban.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private UserService userService;

    @Resource
    private ReplyMapper replyMapper;


    @Resource
    private CommentMapper commentMapper;

    @Resource
    private PostMapper postMapper;

    @Resource
    private MessageService messageService;


    @Override
    public void replyToComment(ReplyParam param) throws ApiException {

        Reply reply = replyMapper.selectByPrimaryKey(param.getId());
        ApiException.when(reply != null, "当前回复已存在");

        reply = new Reply();

        // 每个回复都挂在一个评论下
        Comment comment = commentMapper.selectByPrimaryKey(param.getCommentId());
        ApiException.when(comment == null, "回复的评论不存在");
        reply.setReplyType(param.getReplyType());
        reply.setCommentId(param.getCommentId());

        Long sendUserId = comment.getUserId();
        if (ReplyType.TO_REPLY.check(param.getReplyType())) {
            // 回复某条回复
            Reply originReply = replyMapper.selectByPrimaryKey(param.getReplyId());
            ApiException.when(originReply == null, "回复的回复不存在");
            reply.setReplyId(param.getReplyId());
            sendUserId = originReply.getUserId();
        }

        Long uid = userService.currentUserId();

        reply.setStatus(true);
        reply.setContent(param.getContent());
        reply.setReplyUserId(param.getReplyUserId());
        reply.setUserId(uid);
        reply.setCreatedTime(DateUtil.currentDate());
        reply.setUpdatedTime(DateUtil.currentDate());
        ApiException.when(replyMapper.insert(reply) == 0, "添加评论失败");

        // 添加回复数量
        comment.setReplyNum(comment.getReplyNum() + 1);
        commentMapper.updateByPrimaryKey(comment);

        // 发送回复信息
        messageService.createMessage(MessageType.REPLY,reply.getId(),uid,sendUserId);
    }

    @Override
    public PageInfo<ReplyVO> queryRepliesByComment(Long commentId, Integer offset, Integer count) throws ApiException {

        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        ApiException.when(comment == null, "评论不存在");

        PageHelper.offsetPage(offset, count);
        List<Reply> list = replyMapper.selectByComment(commentId);

        List<ReplyVO> voList = list.stream()
                .map(reply -> {
                    ReplyVO vo = new ReplyVO();
                    BeanUtils.copyProperties(reply, vo);
                    return vo;
                })
                .collect(Collectors.toList());
        return new PageInfo<>(voList);
    }


    @Override
    public void deleteReply(Long replyId) throws ApiException {

        Reply reply = replyMapper.selectByPrimaryKey(replyId);
        ApiException.when(reply == null, "回复不存在");

        // 判断当前是否有权删除
        Long uid = userService.currentUserId();

        // 发表当前评论的用户
        Comment comment = commentMapper.selectByPrimaryKey(reply.getCommentId());
        ApiException.when(comment == null, "评论不存在");

        // 发表帖子的用户
        Post post = postMapper.selectByPrimaryKey(comment.getPostId());
        ApiException.when(post == null, "帖子不存在");

        if (uid.equals(reply.getUserId()) || uid.equals(comment.getUserId()) || uid.equals(post.getCreateUserId())) {
            ApiException.when(replyMapper.deleteByPrimaryKey(replyId) == 0, "删除失败");
            return;
        }

        throw new ApiException("无删除权限");
    }


    @Override
    public void deleteReplyByComment(Long commentId) throws ApiException {
        replyMapper.deleteByComment(commentId);
    }

}
