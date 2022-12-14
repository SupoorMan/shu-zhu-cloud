package com.szc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.LoginLog;
import com.szc.mapper.LoginLogMapper;
import com.szc.service.LoginLogService;
import com.szc.transfer.LoginLogPage;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public IPage<LoginLog> pages(LoginLogPage loginLogPage) {
        LambdaQueryWrapper<LoginLog> wrapper = Wrappers.lambdaQuery();
        if (loginLogPage.getType() != null) {
            wrapper.eq(LoginLog::getType, loginLogPage.getType());
        }
        if (loginLogPage.getStartTime() != null && loginLogPage.getEndTime() != null) {
            wrapper.between(LoginLog::getCreateTime, loginLogPage.getStartTime(), loginLogPage.getEndTime());
        }
        if (loginLogPage.getStartTime() != null) {
            wrapper.ge(LoginLog::getCreateTime, loginLogPage.getStartTime());
        }
        if (loginLogPage.getEndTime() != null) {
            wrapper.le(LoginLog::getCreateTime, loginLogPage.getEndTime());
        }

        return this.page(loginLogPage.build(), wrapper);
    }
}
