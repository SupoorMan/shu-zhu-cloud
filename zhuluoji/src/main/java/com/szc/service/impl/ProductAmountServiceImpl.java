package com.szc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.ProductAmount;
import com.szc.mapper.ProductAmountMapper;
import com.szc.service.ProductAmountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规格表 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Service
public class ProductAmountServiceImpl extends ServiceImpl<ProductAmountMapper, ProductAmount> implements ProductAmountService {

}
