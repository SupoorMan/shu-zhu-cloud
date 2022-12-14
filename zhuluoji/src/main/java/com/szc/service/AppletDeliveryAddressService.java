package com.szc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szc.entity.AppletDeliveryAddress;

import java.util.List;

/**
 * <p>
 * 小程序收货地址表 服务类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
public interface AppletDeliveryAddressService extends IService<AppletDeliveryAddress> {

    void cancelDefaults(Integer id, Integer appletUserId);

    int countByAppletUserId(Integer appletUserId);

    List<AppletDeliveryAddress> findList(Integer appletUserId);

}
