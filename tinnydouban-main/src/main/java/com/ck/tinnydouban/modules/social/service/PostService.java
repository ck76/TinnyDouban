package com.ck.tinnydouban.modules.social.service;


import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.social.dto.post.PostParam;
import com.ck.tinnydouban.modules.social.dto.post.PostVO;
import com.github.pagehelper.PageInfo;

public interface PostService {


    void createPost(PostParam param) throws ApiException;

    void updatePost(PostParam param) throws ApiException;

    PageInfo<PostVO> queryAllByTopic(Long topicId, Integer offset, Integer count) throws ApiException;

    PageInfo<PostVO> queryAllByMovie(Long movieId, Integer offset, Integer count) throws ApiException;

    void deletePost(Long postId) throws ApiException;

    void deletePostsByTopic(Long topicId) throws ApiException;

}
