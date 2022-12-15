package com.szc.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.szc.entity.AppletUser;
import com.szc.exception.PoorException;
import com.szc.service.AppletUserService;
import com.szc.tool.JwtTool;
import com.szc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 拦截器
 */

@SpringBootConfiguration
public class HttpHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AppletUserService appletUserService;

    private static final long TWO_DAY = 24 * 2 * 60 * 60;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String uid = request.getHeader("uid");
        if (StringUtil.isEmpty(token)) {
            throw new PoorException("请重新登录");
        }
        try {
            String token02 = redisUtil.get(token);
            String lastToken = String.format(token02, token);
            JwtTool.verify(lastToken, uid);
        } catch (SignatureVerificationException e) {
            throw new PoorException("凭证签名无效,请重新登录");
        } catch (TokenExpiredException e) {
            throw new PoorException("凭证过期,请重新登录");
        } catch (AlgorithmMismatchException e) {
            throw new PoorException("凭证版本无效,请重新登录");
        } catch (Exception e) {
            throw new PoorException("凭证无效,请重新登录");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String token = request.getHeader("token");
        String uid = request.getHeader("uid");
        if (StringUtil.isEmpty(token) || StringUtil.isEmpty(uid)) {
            throw new PoorException("请重新登录");
        }

        //续时间
        Long expire = redisUtil.getExpire(token);
        if (expire <= TWO_DAY) {
            redisUtil.delete(token);

            AppletUser user = appletUserService.findByUid(uid);
            String newToken = JwtTool.getToken(user);

            String[] tokens = newToken.split("\\.");
            response.setHeader("token", tokens[1]);
            redisUtil.expire(tokens[1], 15, TimeUnit.DAYS);
            redisUtil.expire("authentic::" + user.getId(), 15, TimeUnit.DAYS);
        }

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
