package com.szc.controller;


import com.szc.config.BaseController;
import com.szc.entity.AppletDeliveryAddress;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.AppletDeliveryAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 小程序收货地址表 前端控制器
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Api(tags = "小程序收货地址")
@RestController
@RequestMapping("/appletDeliveryAddress")
public class AppletDeliveryAddressController extends BaseController {

    @Autowired
    private AppletDeliveryAddressService appletDeliveryAddressService;


    @GetMapping("/list")
    @ApiOperation(value = "查询小程序用户收货地址")
    public Result list() {
        return ResultUtil.success(appletDeliveryAddressService.findList(getUserId()));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增收货地址")
    public Result add(@RequestBody AppletDeliveryAddress address) {
        int count = appletDeliveryAddressService.countByAppletUserId(getUserId());
        if (count <= 0) {
            address.setDefaults(1);
        }
        appletDeliveryAddressService.save(address);
        return ResultUtil.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新收货地址")
    public Result update(@RequestBody AppletDeliveryAddress address) {
        if (address.getDefaults() != null) {
            AppletDeliveryAddress deliveryAddress = appletDeliveryAddressService.getById(address.getId());
            if (!deliveryAddress.getDefaults().equals(address.getDefaults())) {
                appletDeliveryAddressService.cancelDefaults(deliveryAddress.getId(), getUserId());
            }
        }

        appletDeliveryAddressService.updateById(address);
        return ResultUtil.success();
    }


}
