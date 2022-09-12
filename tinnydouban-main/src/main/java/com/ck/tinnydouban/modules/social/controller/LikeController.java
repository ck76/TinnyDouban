package com.ck.tinnydouban.modules.social.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.modules.social.dto.like.LikeParam;
import com.ck.tinnydouban.modules.social.service.LikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bbs")
public class LikeController {

    @Resource
    private LikeService likeService;


    @PostMapping("/like")
    public CommonResult<Void> clickLike(@RequestBody LikeParam param) {
        likeService.clickLike(param);
        return CommonResult.success("点赞成功");
    }

    @PostMapping("/unlike")
    public CommonResult<Void> cancelLike(@RequestBody LikeParam param) {
        likeService.cancelLike(param);
        return CommonResult.success("取消点赞成功");
    }

}
