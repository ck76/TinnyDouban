package com.ck.tinnydouban.modules.social.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.social.dto.reply.ReplyParam;
import com.ck.tinnydouban.modules.social.dto.reply.ReplyVO;
import com.ck.tinnydouban.modules.social.service.ReplyService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/bbs/reply")
public class ReplyController {


    @Resource
    private ReplyService replyService;


    @PostMapping("/create")
    @ApiOperation("添加回复")
    CommonResult<Void> replyToComment(@RequestBody ReplyParam param) throws ApiException {
        replyService.replyToComment(param);
        return CommonResult.success("回复评论成功");
    }


    @GetMapping("/list")
    @ApiOperation("获取评论下的回复列表")
    CommonResult<PageInfo<ReplyVO>> replyListByComment(@RequestParam("comment_id") Long commentId,
                                                       @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                       @RequestParam(value = "count", defaultValue = "20") Integer count) throws ApiException {
        PageInfo<ReplyVO> pageInfo = replyService.queryRepliesByComment(commentId, offset, count);
        return CommonResult.success(pageInfo, "回复列表查询成功");
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除某条评论")
    CommonResult<Void> deleteReply(@PathVariable("id") Long replyId) throws ApiException {
        replyService.deleteReply(replyId);
        return CommonResult.success("删除成功");
    }


}
