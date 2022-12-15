package com.szc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szc.entity.Orders;
import com.szc.transfer.OrdersPage;

/**
 * <p>
 * 积分商品订单 服务类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
public interface OrdersService extends IService<Orders> {

    IPage<Orders> pages(OrdersPage ordersPage);

}
