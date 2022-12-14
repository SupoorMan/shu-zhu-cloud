package com.szc.controller;


import com.szc.entity.IntegralProduct;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.IntegralProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分商品表 前端控制器
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Api(tags = "积分商品")
@RestController
@RequestMapping("/product")
public class IntegralProductController {

    @Autowired
    private IntegralProductService integralProductService;


    @PostMapping("/add")
    @ApiOperation(value = "新增积分商品")
    public Result add(@RequestBody IntegralProduct product){
        //TODO oss 图文
        integralProductService.save(product);
        return ResultUtil.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新积分商品")
    public Result update(@RequestBody IntegralProduct product){
        //TODO oss 图文
        integralProductService.updateById(product);
        return ResultUtil.success();
    }


}
