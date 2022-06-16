package com.ck.tinnydouban.modules.admin.controller;

import com.ck.tinnydouban.api.CommonResult;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.admin.dto.UserRoleVO;
import com.ck.tinnydouban.modules.security.service.UserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("admin/")
public class AdminRoleController {

    @Resource
    private UserRoleService userRoleService;

    /**
     * 给用户关联一个角色
     * @param userRoleVO
     * @return
     * @throws ApiException
     */
    @PostMapping("user/addRole")
    @ApiOperation(value = "插入用户-角色关系")
    public CommonResult insert(@RequestBody UserRoleVO userRoleVO) throws ApiException {
        userRoleService.insert(userRoleVO.getUserId(), userRoleVO.getRoleId());
        return CommonResult.success("关系插入成功");
    }
}
