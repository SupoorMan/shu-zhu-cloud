package com.szc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.AppletDeliveryAddress;
import com.szc.mapper.AppletDeliveryAddressMapper;
import com.szc.service.AppletDeliveryAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 小程序收货地址表 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Service
public class AppletDeliveryAddressServiceImpl extends ServiceImpl<AppletDeliveryAddressMapper, AppletDeliveryAddress> implements AppletDeliveryAddressService {

    @Override
    public void cancelDefaults(Integer id, Integer appletUserId) {
        LambdaQueryWrapper<AppletDeliveryAddress> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(AppletDeliveryAddress::getId, id);
        wrapper.eq(AppletDeliveryAddress::getAppletUserId, appletUserId);

        AppletDeliveryAddress address = new AppletDeliveryAddress();
        address.setDefaults(0);
        this.update(address, wrapper);
    }

    @Override
    public int countByAppletUserId(Integer appletUserId) {
        LambdaQueryWrapper<AppletDeliveryAddress> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(AppletDeliveryAddress::getAppletUserId, appletUserId);
        return this.count(wrapper);
    }

    @Override
    public List<AppletDeliveryAddress> findList(Integer appletUserId) {
        LambdaQueryWrapper<AppletDeliveryAddress> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(AppletDeliveryAddress::getAppletUserId, appletUserId);
        wrapper.orderByDesc(AppletDeliveryAddress::getDefaults)
                .orderByAsc(AppletDeliveryAddress::getId);
        return this.list(wrapper);
    }
}
