package com.szc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.entity.IntegralProduct;
import com.szc.mapper.IntegralProductMapper;
import com.szc.service.IntegralProductService;
import com.szc.transfer.IntegralProductPage;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分商品表 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Service
public class IntegralProductServiceImpl extends ServiceImpl<IntegralProductMapper, IntegralProduct> implements IntegralProductService {

    @Override
    public IPage<IntegralProduct> pages(IntegralProductPage integralProductPage) {
        LambdaQueryWrapper<IntegralProduct> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByDesc(IntegralProduct::getCreateTime);
        return this.page(integralProductPage.build(), wrapper);
    }
}
