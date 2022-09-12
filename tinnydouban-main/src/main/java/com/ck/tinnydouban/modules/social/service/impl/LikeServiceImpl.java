package com.ck.tinnydouban.modules.social.service.impl;

import com.ck.tinnydouban.dao.CommentMapper;
import com.ck.tinnydouban.dao.LikeMapper;
import com.ck.tinnydouban.dao.PostMapper;
import com.ck.tinnydouban.modules.message.MessageType;
import com.ck.tinnydouban.modules.message.service.MessageService;
import com.ck.tinnydouban.modules.security.util.RedisUtil;
import com.ck.tinnydouban.modules.social.config.LikeType;
import com.ck.tinnydouban.modules.social.dto.like.LikeNumDTO;
import com.ck.tinnydouban.modules.social.dto.like.LikeParam;
import com.ck.tinnydouban.modules.social.service.LikeService;
import com.ck.tinnydouban.pojo.entity.Comment;
import com.ck.tinnydouban.pojo.entity.Like;
import com.ck.tinnydouban.pojo.entity.Post;
import com.ck.tinnydouban.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


@Service
@Slf4j
public class LikeServiceImpl implements LikeService {


    public static final String KEY_STATUS = "status";

    public static final String KEY_CREATE_TIME = "create_time";

    public static final String KEY_UPDATE_TIME = "update_time";

    // 缓存存活时间
    public static final int VALID_TIME = 2;


    @Resource
    private LikeMapper likeMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private PostMapper postMapper;

    @Resource
    private MessageService messageService;


    @Override
    public void clickLike(LikeParam param) {
        onLikeChanged(1, param);
    }


    @Override
    public void cancelLike(LikeParam param) {
        onLikeChanged(0, param);
    }


    /**
     * 点赞功能实现
     * <p>
     * 首先根据参数构建redis key, 并访问缓存
     * <p>
     * 若redis命中, 更新过期时间,检查状态是否一致, 不一致则更新状态
     * <p>
     * 若缓存未命中查询DB, 若DB命中, 检查状态是否一致, 不一致则更新状态,
     * 将该记录添加到缓存
     * <p>
     * 若DB仍未命中, 构建新点赞记录，加入缓存
     * <p>
     * 缓存key到期时将记录插入DB
     *
     * @param param 点赞参数
     */
    private void onLikeChanged(int status, LikeParam param) {

        String mainKey = "";

        if (LikeType.POST.check(param.getType())) {
            mainKey = buildLikeKey(param.getUserId(), param.getPostId(), LikeType.POST);
        } else if (LikeType.COMMENT.check(param.getType())) {
            mainKey = buildLikeKey(param.getUserId(), param.getCommentId(), LikeType.COMMENT);
        }

        boolean hasChange = false;

        // 先查缓存数据
        if (redisUtil.hasKey(mainKey)) {

            Map<String, Object> hash = redisUtil.getHashValue(mainKey);
            // 若已经点赞/取消，防止重复点击
            if ((int) hash.get(KEY_STATUS) == status) {
                return;
            }

            // 若状态不一致,则更新存活时长
            redisUtil.editHashValue(mainKey, KEY_STATUS, status);
            redisUtil.editHashValue(mainKey, KEY_UPDATE_TIME, System.currentTimeMillis());
            redisUtil.setHourExpire(mainKey, VALID_TIME);
            hasChange = true;

        } else {
            // 缓存不命中, 查db

            Like like = null;
            if (LikeType.POST.check(param.getType())) {
                like = likeMapper.selectByPostId(param.getUserId(), param.getPostId());
            } else if (LikeType.COMMENT.check(param.getType())) {
                like = likeMapper.selectByCommentId(param.getUserId(), param.getCommentId());
            }

            // 无论DB是否命中,都需要加入新缓存
            Map<String, Object> hash = new HashMap<>();
            hash.put(KEY_CREATE_TIME, System.currentTimeMillis());

            // DB未命中
            if (like == null) {
                hasChange = true;

                // 未命中直接创建新的点赞记录入库
                like = new Like();
                BeanUtils.copyProperties(param, like);
                like.setCommentId(param.getCommentId() == null ? 0L : param.getCommentId());
                like.setPostId(param.getPostId() == null ? 0L : param.getPostId());
                like.setStatus(status == 1);
                like.setCreatedTime(DateUtil.currentDate());
                like.setUpdatedTime(DateUtil.currentDate());
                likeMapper.insert(like);

                // 当点赞记录入库时，发送点赞信息
                Long sendUserId = 0L;
                if (LikeType.POST.check(param.getType())) {
                    Post post = postMapper.selectByPrimaryKey(param.getPostId());
                    sendUserId = post.getCreateUserId();
                } else if (LikeType.COMMENT.check(param.getType())) {
                    Comment comment = commentMapper.selectByPrimaryKey(param.getCommentId());
                    sendUserId = comment.getUserId();
                }
                messageService.createMessage(MessageType.LIKE, like.getId(), param.getUserId(), sendUserId);

            } else {
                // DB命中
                int dbStatus = like.getStatus() ? 1 : 0;
                if (dbStatus != status) {
                    hasChange = true;
                    like.setStatus(status == 1);
                    like.setUpdatedTime(DateUtil.currentDate());
                    likeMapper.updateByPrimaryKey(like);
                    hash.put(KEY_CREATE_TIME, like.getCreatedTime().getTime());
                }
            }


            hash.put(KEY_STATUS, status);
            hash.put(KEY_UPDATE_TIME, System.currentTimeMillis());
            redisUtil.setHashValue(mainKey, hash);
            redisUtil.setHourExpire(mainKey, VALID_TIME);
        }

        if (hasChange) {
            changeLikeNum(status, param);
        }


    }

    @Override
    public Long queryPostLikeNum(Long id) {

        String numKey = buildLikeNumKey(id, LikeType.POST);
        String num = redisUtil.getStrValue(numKey);
        if (StringUtils.isEmpty(num)) {
            Post post = postMapper.selectByPrimaryKey(id);
            return post.getLikeNum();
        }
        return Long.parseLong(num);
    }

    @Override
    public Long queryCommentLikeNum(Long id) {
        String numKey = buildLikeNumKey(id, LikeType.COMMENT);
        String num = redisUtil.getStrValue(numKey);
        if (StringUtils.isEmpty(num)) {
            Comment comment = commentMapper.selectByPrimaryKey(id);
            return comment.getLikeNum();
        }
        return Long.parseLong(num);
    }


    private void changeLikeNum(int status, LikeParam param) {

        String numKey = "";

        if (LikeType.POST.check(param.getType())) {
            numKey = buildLikeNumKey(param.getPostId(), LikeType.POST);
        } else if (LikeType.COMMENT.check(param.getType())) {
            numKey = buildLikeNumKey(param.getCommentId(), LikeType.COMMENT);
        }

        String num = redisUtil.getStrValue(numKey);

        if (StringUtils.isEmpty(num)) {
            // 缓存未命中，查库获得当前点赞数
            Long dbNum = 0L;
            if (LikeType.POST.check(param.getType())) {
                Post post = postMapper.selectByPrimaryKey(param.getPostId());
                dbNum = post.getLikeNum();
            } else if (LikeType.COMMENT.check(param.getType())) {
                Comment comment = commentMapper.selectByPrimaryKey(param.getCommentId());
                dbNum = comment.getLikeNum();
            }
            // 加入缓存
            num = String.valueOf(dbNum);
            redisUtil.setStrValue(numKey, num);
        }
        // 加入缓存
        int action = status == 1 ? 1 : -1;
        redisUtil.setStrValue(numKey, String.valueOf(Long.parseLong(num) + action));
    }


    /**
     * 构建点赞记录在redis中的key
     *
     * @param userId 点赞用户id
     * @param id     帖子/评论id
     * @return key
     */
    String buildLikeKey(Long userId, Long id, LikeType type) {
        return "LIKE:" + userId + ":" + id + ":" + type.getName();
    }

    /**
     * 构建点赞数量在redis中的key
     *
     * @param id   帖子/评论id
     * @param type 点赞类型
     * @return key
     */
    String buildLikeNumKey(Long id, LikeType type) {
        return "LIKE_NUM:" + id + ":" + type.getName();
    }


    @Override
    public List<Like> parseLikeCache() {
        List<Like> likeList = new ArrayList<>();

        // 获取当前全部点赞的key
        Set<String> keys = redisUtil.patternKeySet("LIKE:*");

        if (keys != null && !keys.isEmpty()) {

            for (String key : keys) {

                String[] info = key.split(":");

                if (info.length < 4) {
                    continue;
                }

                Like like = new Like();
                like.setUserId(Long.valueOf(info[1]));

                String type = info[3];
                if (LikeType.POST.check(type)) {

                    like.setType(LikeType.POST.getValue());
                    like.setPostId(Long.valueOf(info[2]));

                } else if (LikeType.COMMENT.check(type)) {
                    like.setType(LikeType.COMMENT.getValue());
                    like.setCommentId(Long.valueOf(info[2]));
                }


                Map<String, Object> hash = redisUtil.getHashValue(key);
                like.setStatus((int) hash.get(KEY_STATUS) == 1);
                Long createTime = (Long) hash.get(KEY_CREATE_TIME);
                Long updateTime = (Long) hash.get(KEY_UPDATE_TIME);
                like.setCreatedTime(new Date(createTime));
                like.setUpdatedTime(new Date(updateTime));
                likeList.add(like);
            }
        }

        return likeList;
    }

    @Override
    public List<LikeNumDTO> parseLikeNumCache() {

        List<LikeNumDTO> dtoList = new ArrayList<>();

        // 获取当前全部点赞的key
        Set<String> keys = redisUtil.patternKeySet("LIKE_NUM:*");

        if (keys != null && !keys.isEmpty()) {

            for (String key : keys) {

                String[] info = key.split(":");

                if (info.length < 3) {
                    continue;
                }

                LikeNumDTO dto = new LikeNumDTO();
                dto.setLikeNum(Long.valueOf(redisUtil.getStrValue(key)));

                String type = info[2];
                if (LikeType.POST.check(type)) {

                    dto.setType(LikeType.POST.getValue());
                    dto.setPostId(Long.valueOf(info[1]));

                } else if (LikeType.COMMENT.check(type)) {
                    dto.setType(LikeType.COMMENT.getValue());
                    dto.setCommentId(Long.valueOf(info[1]));
                }
                dtoList.add(dto);
            }
        }


        return dtoList;
    }

}
