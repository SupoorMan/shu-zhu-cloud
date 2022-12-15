package com.szc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szc.entity.IntegralProduct;
import com.szc.transfer.IntegralProductPage;

/**
 * <p>
 * 积分商品表 服务类
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
public interface IntegralProductService extends IService<IntegralProduct> {

    IPage<IntegralProduct> pages(IntegralProductPage integralProductPage);

}
