package com.ck.tinnydouban.modules.social.service.impl;

import com.ck.tinnydouban.dao.UserFollowMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.message.MessageType;
import com.ck.tinnydouban.modules.message.service.MessageService;
import com.ck.tinnydouban.modules.security.dto.vo.UserVO;
import com.ck.tinnydouban.modules.security.service.UserService;
import com.ck.tinnydouban.modules.social.service.FollowService;
import com.ck.tinnydouban.pojo.entity.UserFollow;
import com.ck.tinnydouban.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class FollowServiceImpl implements FollowService {


    @Resource
    private UserFollowMapper userFollowMapper;

    @Resource
    private UserService userService;

    @Resource
    private MessageService messageService;

    /**
     * 关注他人
     *
     * @param followUserId
     * @throws ApiException
     */
    @Override
    public void followOther(Long followUserId) throws ApiException {

        Long uid = userService.currentUserId();

        UserFollow userFollow = userFollowMapper.selectByUids(uid, followUserId);

        if (userFollow == null) {
            userFollow = new UserFollow();
            userFollow.setUserId(uid);
            userFollow.setFollowUserId(followUserId);
            userFollow.setCreatedTime(DateUtil.currentDate());
        }

        userFollow.setFollow(true);
        userFollow.setUpdatedTime(DateUtil.currentDate());

        ApiException.when(userFollowMapper.insert(userFollow) == 0, "关注失败");

        // 发送信息
        messageService.createMessage(MessageType.FOLLOW, userFollow.getId(), uid, followUserId);
    }

    /**
     * 取消关注
     * @param followUserId
     * @throws ApiException
     */
    @Override
    public void cancelFollowOther(Long followUserId) throws ApiException {

        Long uid = userService.currentUserId();
        UserFollow userFollow = userFollowMapper.selectByUids(uid, followUserId);
        ApiException.when(userFollow == null, "未关注当前用户");

        userFollow.setFollow(false);
        userFollow.setUpdatedTime(DateUtil.currentDate());
        ApiException.when(userFollowMapper.updateByPrimaryKey(userFollow) == 0, "取消关注失败");
    }

    @Override
    public PageInfo<UserVO> queryFollowingUsers(Integer offset, Integer count) throws ApiException {

        Long uid = userService.currentUserId();

        PageHelper.offsetPage(offset, count);
        List<UserFollow> userFollows = userFollowMapper.selectFollowingList(uid);

        List<UserVO> voList = new ArrayList<>();

        for (UserFollow uf : userFollows) {
            UserVO vo = userService.getUserInfoById(Math.toIntExact(uf.getFollowUserId()));
            voList.add(vo);
        }

        return new PageInfo<>(voList);
    }

    @Override
    public PageInfo<UserVO> queryFollowedUsers(Integer offset, Integer count) throws ApiException {

        Long uid = userService.currentUserId();
        PageHelper.offsetPage(offset, count);

        List<UserFollow> userFollows = userFollowMapper.selectFollowedList(uid);
        List<UserVO> voList = new ArrayList<>();

        for (UserFollow uf : userFollows) {
            UserVO vo = userService.getUserInfoById(Math.toIntExact(uf.getUserId()));
            voList.add(vo);
        }

        return new PageInfo<>(voList);
    }
}
