package com.ck.tinnydouban.modules.social.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.social.dto.comment.CommentParam;
import com.ck.tinnydouban.modules.social.dto.comment.CommentVO;
import com.github.pagehelper.PageInfo;

public interface CommentService {


    void createComment(CommentParam param) throws ApiException;

    PageInfo<CommentVO> queryComments(Long postId, Integer offset, Integer count)throws ApiException;


    void deleteComment(Long commentId) throws ApiException;


    void deleteCommentByPost(Long postId) throws ApiException;





}
