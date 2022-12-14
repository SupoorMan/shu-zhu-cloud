package com.szc.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.szc.entity.AppletUser;
import com.szc.exception.PoorException;
import com.szc.util.StringUtil;

import java.util.Calendar;


public class JwtTool {

    /**
     * 获取token
     *
     * @param u user
     * @return token
     */
    public static String getToken(AppletUser u) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 14);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId", u.getId())
                .withClaim("nickname", u.getNickname())
                .withClaim("avatarUrl", u.getAvatarUrl())
                .withClaim("openid", u.getOpenid());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(u.getUid()));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token,String key) throws PoorException {
        if (StringUtil.isEmpty(token)) {
            throw new PoorException("token不能为空");
        }

        JWTVerifier build = JWT.require(Algorithm.HMAC256(key)).build();
        return build.verify(token);
    }

//    public static void main(String[] args) throws InterruptedException {
//        AppletUser user = new AppletUser();
//        user.setId(100);
//        user.setNickname("tom");
//        user.setAvatarUrl("http://www.abc.pic");
//        user.setOpenid("1029384756");
//
//        user.setUid("hello_world");
//
//        String token = getToken(user);
//        System.out.println(token);
//
//        DecodedJWT verify = verify(token,user.getUid());
//
//        System.out.println(verify.getClaim("nickname").asString());
//
//    }

}
