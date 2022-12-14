package com.szc.controller;


import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.LoginLogService;
import com.szc.transfer.LoginLogPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录记录表 前端控制器
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;


    @GetMapping("/page")
    @ApiOperation(value = "用户登录记录分页查询")
    public Result page(LoginLogPage loginLogPage) {
        return ResultUtil.success(loginLogService.pages(loginLogPage));
    }

}
