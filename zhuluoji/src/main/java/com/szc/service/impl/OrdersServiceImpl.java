package com.szc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.Orders;
import com.szc.mapper.OrdersMapper;
import com.szc.service.OrdersService;
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

}
