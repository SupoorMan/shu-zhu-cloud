package com.szc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szc.entity.AppletUser;
import com.szc.transfer.AppletUserPage;

/**
 * <p>
 * 小程序用户表 服务类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
public interface AppletUserService extends IService<AppletUser> {

    AppletUser findByOpenid(String openid);

    AppletUser findByUid(String uid);

    IPage<AppletUser> page(AppletUserPage userPage);

}
