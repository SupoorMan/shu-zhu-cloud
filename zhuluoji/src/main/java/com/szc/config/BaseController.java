package com.szc.config;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.szc.entity.AppletUser;
import com.szc.exception.PoorException;
import com.szc.service.AppletUserService;
import com.szc.tool.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {

    @Autowired
    protected RedisUtil redisUtil;
    @Autowired
    protected AppletUserService appletUserService;

    protected AppletUser getAppletUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String token1 = request.getHeader("token");
        String uid = request.getHeader("uid");

        String tokens = redisUtil.get(token1);
        String token = String.format(tokens, token1);
        DecodedJWT verify = JwtTool.verify(token, uid);

        Claim claim = verify.getClaim("userId");
        return appletUserService.getById(claim.asInt());
    }

    protected Integer getUserId() {
        AppletUser appletUser = getAppletUser();
        if (appletUser == null) {
            throw new PoorException("请重新登录");
        }
        return appletUser.getId();
    }

}









