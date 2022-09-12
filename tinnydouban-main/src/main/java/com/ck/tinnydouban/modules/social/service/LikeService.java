package com.ck.tinnydouban.modules.social.service;


import com.ck.tinnydouban.modules.social.dto.like.LikeNumDTO;
import com.ck.tinnydouban.modules.social.dto.like.LikeParam;
import com.ck.tinnydouban.pojo.entity.Like;

import java.util.List;


public interface LikeService {


    void clickLike(LikeParam param);

    void cancelLike(LikeParam param);

    Long queryPostLikeNum(Long id);

    Long queryCommentLikeNum(Long id);

    List<Like> parseLikeCache();

    List<LikeNumDTO> parseLikeNumCache();

}
