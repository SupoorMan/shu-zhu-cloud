package com.szc.controller;


import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.OrdersService;
import com.szc.transfer.OrdersPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分商品订单 前端控制器
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Api(tags = "积分商品订单")
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    @GetMapping("/page")
    @ApiOperation(value = "订单分页")
    public Result page(OrdersPage page) {
        return ResultUtil.success(ordersService.pages(page));
    }

}
