package com.szc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.Orders;
import com.szc.mapper.OrdersMapper;
import com.szc.service.OrdersService;
import com.szc.transfer.OrdersPage;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分商品订单 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Override
    public IPage<Orders> pages(OrdersPage ordersPage) {
        LambdaQueryWrapper<Orders> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByDesc(Orders::getCreateTime);
        return this.page(ordersPage.build(), wrapper);
    }
}
