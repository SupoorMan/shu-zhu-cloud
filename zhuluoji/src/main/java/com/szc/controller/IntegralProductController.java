package com.szc.controller;


import com.szc.entity.IntegralProduct;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.IntegralProductService;
import com.szc.transfer.IntegralProductPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/page")
    @ApiOperation(value = "积分商品分页查询", notes = "分页查询")
    public Result page(IntegralProductPage page) {
        return ResultUtil.success(integralProductService.pages(page));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增积分商品")
    public Result add(@RequestBody IntegralProduct product) {
        integralProductService.save(product);
        return ResultUtil.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新积分商品")
    public Result update(@RequestBody IntegralProduct product) {
        integralProductService.updateById(product);
        return ResultUtil.success();
    }


    @PostMapping("/delete")
    @ApiOperation(value = "删除积分商品")
    public Result delete(@RequestBody IntegralProduct product) {
        integralProductService.removeById(product.getId());
        return ResultUtil.success();
    }


}
