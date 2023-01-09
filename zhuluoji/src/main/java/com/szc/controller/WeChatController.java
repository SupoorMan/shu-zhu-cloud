package com.szc.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.szc.config.RedisUtil;
import com.szc.entity.AppletUser;
import com.szc.entity.LoginLog;
import com.szc.oss.OssUtil;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.AppletUserService;
import com.szc.service.LoginLogService;
import com.szc.tool.JwtTool;
import com.szc.transfer.OSSObjects;
import com.szc.transfer.WeChatLogin;
import com.szc.util.HttpUtils;
import com.szc.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Api(tags = "微信小程序")
@RestController
@RequestMapping("/wx")
public class WeChatController {

    @Autowired
    private AppletUserService appletUserService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LoginLogService loginLogService;


    @GetMapping("/test")
    public Result test() {
        return ResultUtil.success("辣鸡李函珏!");
    }


    @PostMapping("/login")
    @ApiOperation(value = "微信小程序登录", notes = "微信登录获取OpenId,SessionKey,unionid")
    public Result login(@RequestBody WeChatLogin weChatLogin, HttpServletResponse response) {
        //组装获得code2Session参数 请求weixin 返回OpenId,SessionKey,unionid
        OSSObjects ssObjects = OssUtil.oSSObjects;
        String url = String.format(ssObjects.getCode2Session(), ssObjects.getAppid(), ssObjects.getAccessKeySecret(), weChatLogin.getCode());
        String res = HttpUtils.sendGet(url);

        if (StringUtil.isEmpty(res)) {
            return ResultUtil.fail("微信凭证请求失败");
        }

        JSONObject resJson = JSONObject.parseObject(res);
        String openid = resJson.getString("openid");
        if (StringUtil.isEmpty(openid)) {
            return ResultUtil.fail("微信凭证openid获取失败");
        }
        AppletUser appletUser = appletUserService.findByOpenid(openid);
        //第一次登录
        if (appletUser == null) {
            appletUser = new AppletUser();
            appletUser.setOpenid(openid);
            appletUser.setUid(StringUtil.generateUid());
            appletUser.setNickname(weChatLogin.getUserInfo().getNickName());
            appletUser.setAvatarUrl(weChatLogin.getUserInfo().getAvatarUrl());
            appletUser.setGender(weChatLogin.getUserInfo().getGender());
            appletUserService.save(appletUser);
        } else {
            appletUser.setNickname(weChatLogin.getUserInfo().getNickName());
            appletUser.setAvatarUrl(weChatLogin.getUserInfo().getAvatarUrl());
            appletUser.setGender(weChatLogin.getUserInfo().getGender());
            appletUserService.updateById(appletUser);
        }

        //删除作废的token
        String token1 = redisUtil.get("authentic::" + appletUser.getId());
        if (StringUtil.isNotEmpty(token1)) {
            redisUtil.delete("authentic::" + appletUser.getId());
            redisUtil.delete(token1);
        }

        //生成token
        String token = JwtTool.getToken(appletUser);
        String[] tokens = token.split("\\.");

        response.addHeader("token", tokens[1]);
        response.addHeader("uid", appletUser.getUid());
        response.setStatus(200);

        redisUtil.setEx("authentic::" + appletUser.getId(), tokens[1], 15, TimeUnit.DAYS);
        redisUtil.setEx(tokens[1], tokens[0] + ".%s." + tokens[2], 15, TimeUnit.DAYS);

        //记录登录信息
        LoginLog loginLog = new LoginLog();
        loginLog.setType(0);
        loginLog.setUserId(appletUser.getId());
        loginLogService.save(loginLog);

        appletUser.setSessionKey(resJson.getString("session_key"));
        return ResultUtil.success(appletUser);
    }


    @PostMapping("/auth/logout")
    @ApiOperation(value = "微信小程序退出", notes = "退出")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        String uid = request.getHeader("uid");

        String token02 = redisUtil.get(token);
        String lastToken = String.format(token02, token);
        DecodedJWT verify = JwtTool.verify(lastToken, uid);

        Claim userId = verify.getClaim("userId");

        AppletUser appletUser = appletUserService.getById(userId.asInt());

        redisUtil.delete(token);
        redisUtil.delete("authentic::" + appletUser.getId());

        response.setHeader(token, null);
        return ResultUtil.success();
    }

}





