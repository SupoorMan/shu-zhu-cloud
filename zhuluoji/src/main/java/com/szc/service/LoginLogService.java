package com.szc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szc.entity.LoginLog;
import com.szc.transfer.LoginLogPage;

/**
 * <p>
 * 用户登录记录表 服务类
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
public interface LoginLogService extends IService<LoginLog> {

    IPage<LoginLog> pages(LoginLogPage loginLogPage);
}
