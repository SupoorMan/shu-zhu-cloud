package com.szc.controller;


import com.alibaba.fastjson.JSONObject;
import com.szc.config.BaseController;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.SignInService;
import com.szc.util.BitUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.BitSet;

/**
 * <p>
 * 签到表 前端控制器
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@RestController
@Api(tags = "签到")
@RequestMapping("/signIn")
public class SignInController extends BaseController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/sign")
    @ApiOperation(value = "签到")
    public Result sign() {
        Integer userId = getUserId();
        return signInService.sign(userId);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询签到集合")
    @ApiImplicitParam(name = "date", value = "时间字符串(yyyy-MM-dd)", dataType = "java.lang.String", required = true)
    public Result list(String date) {
        StringBuilder data = new StringBuilder();

        Integer userId = getUserId();

        int maxDayOfMonth = LocalDate.parse(date).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        String signs = redisUtil.getRange("sign:" + userId + ":" + date, 0, maxDayOfMonth);
        BitSet bitSet = BitUtil.convertRedisBitmapToJava(signs.getBytes());
        for (int i = 0; i < maxDayOfMonth; i++) {
            data.append(bitSet.get(i) ? 1 : 0);
        }

        String key = "sign:" + userId + ":" + date;
        long count = redisUtil.execute(n -> n.bitCount(key.getBytes())) + 1;

        JSONObject result = new JSONObject();
        result.put("all", count);
        result.put("sign", data.toString());
        return ResultUtil.success(result);
    }


}

















