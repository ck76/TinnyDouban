package com.ck.tinnydouban.modules.social.service;



import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.social.dto.reply.ReplyParam;
import com.ck.tinnydouban.modules.social.dto.reply.ReplyVO;
import com.github.pagehelper.PageInfo;

public interface ReplyService {


    void replyToComment(ReplyParam param) throws ApiException;


    PageInfo<ReplyVO> queryRepliesByComment(Long commentId, Integer offset, Integer count) throws ApiException;


    void deleteReply(Long replyId) throws ApiException;


    void deleteReplyByComment(Long commentId) throws ApiException;


}
