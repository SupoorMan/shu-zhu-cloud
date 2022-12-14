package com.szc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.AppletUser;
import com.szc.mapper.AppletUserMapper;
import com.szc.service.AppletUserService;
import com.szc.transfer.AppletUserPage;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小程序用户表 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Service
public class AppletUserServiceImpl extends ServiceImpl<AppletUserMapper, AppletUser> implements AppletUserService {

    @Override
    public AppletUser findByOpenid(String openid) {
        LambdaQueryWrapper<AppletUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AppletUser::getOpenid, openid);
        return this.getOne(wrapper);
    }

    @Override
    public AppletUser findByUid(String uid) {
        LambdaQueryWrapper<AppletUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AppletUser::getUid, uid);
        return this.getOne(wrapper);
    }

    @Override
    public IPage<AppletUser> page(AppletUserPage userPage) {
        LambdaQueryWrapper<AppletUser> wrapper = new LambdaQueryWrapper<>();
        return this.page(userPage.build(), wrapper);
    }
}
