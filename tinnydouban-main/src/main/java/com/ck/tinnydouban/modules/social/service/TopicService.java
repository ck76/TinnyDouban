package com.ck.tinnydouban.modules.social.service;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.social.dto.topic.TopicParam;
import com.ck.tinnydouban.modules.social.dto.topic.TopicVO;

import java.util.List;

public interface TopicService {

    void createTopic(TopicParam param) throws ApiException;

    void updateTopic(TopicParam param) throws ApiException;

    void deleteTopic(Long topicId) throws ApiException;


    List<TopicVO> queryAllCommonTopic() throws ApiException;

}
