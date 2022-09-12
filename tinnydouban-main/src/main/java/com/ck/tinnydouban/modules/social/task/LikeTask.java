package com.ck.tinnydouban.modules.social.task;

import com.ck.tinnydouban.dao.CommentMapper;
import com.ck.tinnydouban.dao.LikeMapper;
import com.ck.tinnydouban.dao.PostMapper;
import com.ck.tinnydouban.modules.security.util.RedisUtil;
import com.ck.tinnydouban.modules.social.config.LikeType;
import com.ck.tinnydouban.modules.social.dto.like.LikeNumDTO;
import com.ck.tinnydouban.modules.social.service.LikeService;
import com.ck.tinnydouban.pojo.entity.Comment;
import com.ck.tinnydouban.pojo.entity.Like;
import com.ck.tinnydouban.pojo.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
public class LikeTask extends QuartzJobBean {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private LikeService likeService;

    @Resource
    private LikeMapper likeMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private PostMapper postMapper;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        syncLikeData();
        syncLikeNumData();
    }


    private void syncLikeData() {

        List<Like> likeList = likeService.parseLikeCache();

        for (Like like : likeList) {

            Like originLike = null;

            if (LikeType.COMMENT.check(like.getType())) {
                originLike = likeMapper.selectByCommentId(like.getUserId(), like.getCommentId());
            } else if (LikeType.POST.check(like.getType())) {
                originLike = likeMapper.selectByPostId(like.getUserId(), like.getPostId());
            }

            if (originLike == null) {
                likeMapper.insert(like);
            } else {
                originLike.setStatus(like.getStatus());
                originLike.setUpdatedTime(like.getUpdatedTime());
                likeMapper.updateByPrimaryKey(originLike);
            }

        }


    }


    private void syncLikeNumData() {

        List<LikeNumDTO> dtoList = likeService.parseLikeNumCache();
        for (LikeNumDTO dto : dtoList) {

            if (LikeType.COMMENT.check(dto.getType())) {

                Comment comment = commentMapper.selectByPrimaryKey(dto.getCommentId());
                comment.setLikeNum(dto.getLikeNum());
                commentMapper.updateByPrimaryKey(comment);

            } else if (LikeType.POST.check(dto.getType())) {

                Post post = postMapper.selectByPrimaryKey(dto.getPostId());
                post.setLikeNum(dto.getLikeNum());
                postMapper.updateByPrimaryKey(post);

            }
        }

    }

}
