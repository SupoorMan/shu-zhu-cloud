package com.szc.controller;


import com.szc.config.BaseController;
import com.szc.entity.AppletUser;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.transfer.AppletUserPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 小程序用户表 前端控制器
 * </p>
 *
 * @author Poor
 * @since 2022-12-09
 */
@Api(tags = "小程序用户")
@RestController
@RequestMapping("/auser")
public class AppletUserController extends BaseController {

    @GetMapping("/getUser")
    @ApiOperation(value = "获取用户信息", notes = "无参")
    public Result getUser() {
        return ResultUtil.success(getAppletUser());
    }

    @GetMapping("/page")
    @ApiOperation(value = "小程序用户分页查询", notes = "后台管理使用")
    public Result page(AppletUserPage userPage) {
        return ResultUtil.success(appletUserService.page(userPage));
    }

    @PostMapping("/update")
    @ApiOperation(value = "小程序用户更新", notes = "传id和需要更新的字段即可")
    public Result update(@RequestBody AppletUser user) {
        if (user.getId() == null) {
            return ResultUtil.fail("缺省字段");
        }
        appletUserService.updateById(user);
        return ResultUtil.success();
    }

}








