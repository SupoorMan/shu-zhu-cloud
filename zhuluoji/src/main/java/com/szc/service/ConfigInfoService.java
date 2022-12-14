package com.szc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szc.entity.ConfigInfo;

import java.util.List;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
public interface ConfigInfoService extends IService<ConfigInfo> {

    List<ConfigInfo> findListByKey(String key);

    ConfigInfo findByField(ConfigInfo configInfo);

}
