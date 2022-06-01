package com.ck.tinnydouban.modules.app.controller;

import com.ck.tinnydouban.api.CommonResult;

import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.app.pojo.AppConfigDTO;
import com.ck.tinnydouban.modules.app.pojo.AppConfigVO;
import com.ck.tinnydouban.modules.app.service.AppConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/config")
public class AppConfigController {


    @Resource
    private AppConfigService appConfigService;


    @PostMapping("/create")
    @ApiOperation("创建新版本")
    CommonResult<Void> createVersionConfig(@RequestBody AppConfigDTO dto) throws ApiException {
        appConfigService.createVersionConfig(dto);
        return CommonResult.success("创建版本信息成功");
    }

    @GetMapping("/last")
    @ApiOperation("获取最新版本")
    CommonResult<AppConfigVO> lastVersion() {
        return CommonResult.success(appConfigService.lastVersion(), "查询成功");
    }


    @GetMapping("/list")
    @ApiOperation("获取全部版本信息")
    CommonResult<List<AppConfigVO>> allVersions() {
        List<AppConfigVO> voList = appConfigService.queryAllVersion();
        return CommonResult.success(voList, "查询成功");
    }


}
