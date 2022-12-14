package com.szc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.ConfigInfo;
import com.szc.mapper.ConfigInfoMapper;
import com.szc.service.ConfigInfoService;
import com.szc.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@Service
public class ConfigInfoServiceImpl extends ServiceImpl<ConfigInfoMapper, ConfigInfo> implements ConfigInfoService {

    @Override
    public List<ConfigInfo> findListByKey(String key) {
        if (StringUtil.isEmpty(key)){
            return new ArrayList<>();
        }
        LambdaQueryWrapper<ConfigInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ConfigInfo::getKey, key);
        return this.list(wrapper);
    }

    @Override
    public ConfigInfo findByField(ConfigInfo configInfo) {
        return null;
    }
}
