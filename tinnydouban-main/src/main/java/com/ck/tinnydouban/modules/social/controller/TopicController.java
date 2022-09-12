package com.ck.tinnydouban.modules.social.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.social.dto.topic.TopicParam;
import com.ck.tinnydouban.modules.social.dto.topic.TopicVO;
import com.ck.tinnydouban.modules.social.service.TopicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bbs/topic")
public class TopicController {


    @Resource
    private TopicService topicService;


    @PostMapping("/create")
    @ApiOperation("创建话题")
    CommonResult<Void> createTopic(@RequestBody TopicParam param) throws ApiException {
        topicService.createTopic(param);
        return CommonResult.success("话题创建成功");
    }


    @PutMapping("/update")
    @ApiOperation("更新话题")
    CommonResult<Void> updateTopic(@RequestBody TopicParam param) throws ApiException {
        topicService.updateTopic(param);
        return CommonResult.success("话题更新成功");
    }


    @DeleteMapping("delete/{id}")
    @ApiOperation("删除主题")
    CommonResult<Void> deleteTopic(@PathVariable("id") Long id) throws ApiException {
        topicService.deleteTopic(id);
        return CommonResult.success("删除成功");
    }

    @GetMapping("/commons")
    @ApiOperation("获取全部常规话题, 按照帖子数量排序")
    CommonResult<List<TopicVO>> queryAllCommon() throws ApiException {
        List<TopicVO> voList = topicService.queryAllCommonTopic();
        return CommonResult.success(voList, "查询成功");
    }


}
