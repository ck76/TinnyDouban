package com.ck.tinnydouban.modules.social.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.dto.vo.UserVO;
import com.ck.tinnydouban.modules.social.service.FollowService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/follow")
public class FollowController {


    @Resource
    private FollowService followService;


    @GetMapping("/{user_id}")
    @ApiOperation("关注用户")
    CommonResult<Void> followOther(@PathVariable("user_id") Long userId) throws ApiException {
        followService.followOther(userId);
        return CommonResult.success("关注成功");
    }


    @GetMapping("/cancel/{user_id}")
    @ApiOperation("取消关注")
    CommonResult<Void> cancelFollowOther(@PathVariable("user_id") Long userId) throws ApiException {
        followService.cancelFollowOther(userId);
        return CommonResult.success("取关成功");
    }


    @GetMapping("/following")
    @ApiOperation("获取当前用户关注人的列表")
    CommonResult<PageInfo<UserVO>> queryFollowingUsers(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                       @RequestParam(value = "count", defaultValue = "20") Integer count) throws ApiException {

        PageInfo<UserVO> pageInfo = followService.queryFollowingUsers(offset, count);
        return CommonResult.success(pageInfo, "查询成功");
    }

    @GetMapping("/followed")
    @ApiOperation("获取当前用户关注人的列表")
    CommonResult<PageInfo<UserVO>> queryFollowedUsers(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                       @RequestParam(value = "count", defaultValue = "20") Integer count) throws ApiException {

        PageInfo<UserVO> pageInfo = followService.queryFollowedUsers(offset, count);
        return CommonResult.success(pageInfo, "查询成功");
    }

}
