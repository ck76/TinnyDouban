package com.ck.tinnydouban.modules.social.service.impl;

import com.ck.tinnydouban.dao.MovieMapper;
import com.ck.tinnydouban.dao.TopicMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.service.UserService;
import com.ck.tinnydouban.modules.social.config.TopicType;
import com.ck.tinnydouban.modules.social.dto.topic.TopicParam;
import com.ck.tinnydouban.modules.social.dto.topic.TopicVO;
import com.ck.tinnydouban.modules.social.service.PostService;
import com.ck.tinnydouban.modules.social.service.TopicService;
import com.ck.tinnydouban.pojo.entity.Movie;
import com.ck.tinnydouban.pojo.entity.Topic;
import com.ck.tinnydouban.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    @Resource
    private UserService userService;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private MovieMapper movieMapper;

    @Resource
    private PostService postService;


    @Override
    public void createTopic(TopicParam param) throws ApiException {

        Topic topic = new Topic();
        if (TopicType.MOVIE.check(param.getTopicType())) {
            // 电影类型话题
            Movie movie = movieMapper.selectByPrimaryKey(param.getMovieId());
            ApiException.when(movie == null, "依附的电影不存在");
            topic.setName(movie.getName());
            topic.setMovieId(movie.getId());
            topic.setTopicAbstract(movie.getName() + "默认话题");

        } else {
            BeanUtils.copyProperties(param, topic);
        }

        Long userId = userService.currentUserId();
        topic.setTopicType(param.getTopicType());
        topic.setCreateUserId(userId);
        topic.setUserNum(1L);
        topic.setPostNum(0L);
        topic.setCreatedTime(DateUtil.currentDate());
        topic.setUpdatedTime(DateUtil.currentDate());
        ApiException.when(topicMapper.insert(topic) == 0, "话题创建失败");
    }

    @Override
    public void updateTopic(TopicParam param) throws ApiException {

        Topic topic = topicMapper.selectByPrimaryKey(param.getId());
        ApiException.when(topic == null, "该主题不存在");

        topic.setName(param.getName());
        topic.setTopicAbstract(param.getTopicAbstract());
        topic.setUpdatedTime(DateUtil.currentDate());
        ApiException.when(topicMapper.updateByPrimaryKey(topic) == 0, "更新失败");
    }

    @Override
    public void deleteTopic(Long topicId) throws ApiException {

        Topic topic = topicMapper.selectByPrimaryKey(topicId);
        ApiException.when(topic == null, "主题不存在");

        Long uid = userService.currentUserId();
        ApiException.when(!uid.equals(topic.getCreateUserId()), "只有创建者能删除主题");

        // 删除主题前， 先删除主题对应的帖子
        postService.deletePostsByTopic(topicId);
        ApiException.when(topicMapper.deleteByPrimaryKey(topicId) == 0, "删除失败");
    }

    @Override
    public List<TopicVO> queryAllCommonTopic() throws ApiException {

        List<Topic> topicList = topicMapper.selectAll();

        List<TopicVO> voList = topicList.stream()
                .filter(topic -> TopicType.COMMON.check(topic.getTopicType()))
                .map(topic -> {
                    TopicVO vo = new TopicVO();
                    BeanUtils.copyProperties(topic, vo);
                    return vo;
                })
                .sorted((o1, o2) -> o1.getPostNum() > o2.getPostNum() ? 1 : 0)
                .collect(Collectors.toList());
        return voList;
    }
}
