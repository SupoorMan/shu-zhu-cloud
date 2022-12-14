package com.szc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szc.entity.SignIn;
import com.szc.result.Result;

/**
 * <p>
 * 签到表 服务类
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
public interface SignInService extends IService<SignIn> {

    SignIn findOne(SignIn signIn);

    Result sign(Integer userId);

}
