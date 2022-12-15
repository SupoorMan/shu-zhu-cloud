package com.szc.controller;


import com.szc.entity.ConfigInfo;
import com.szc.oss.OssUtil;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.ConfigInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 * 全局配置
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@Api(tags = "全局配置管理")
@RestController
@RequestMapping("/configInfo")
public class ConfigInfoController {

    @Autowired
    private ConfigInfoService configInfoService;


    @GetMapping("/list")
    @ApiOperation(value = "查询配置列表")
    public Result list(String key) {
        return ResultUtil.success(configInfoService.findListByKey(key));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增配置")
    public Result add(@RequestBody ConfigInfo configInfo) {
        configInfoService.save(configInfo);
        return ResultUtil.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新配置")
    public Result update(@RequestBody ConfigInfo configInfo) {
        configInfoService.updateById(configInfo);
        return ResultUtil.success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除配置")
    public Result delete(@RequestBody ConfigInfo configInfo) {
        configInfoService.removeById(configInfo.getId());
        return ResultUtil.success();
    }

    @PostConstruct
    public void getOSSObjects() {
        List<ConfigInfo> oss = configInfoService.findListByKey("oss");

        OssUtil.oSSObjects.setAccessKeySecret(oss.stream().filter(n -> n.getValue().equals("accessKeySecret")).map(ConfigInfo::getContext).findFirst().get());
        OssUtil.oSSObjects.setUrl(oss.stream().filter(n -> n.getValue().equals("url")).map(ConfigInfo::getContext).findFirst().get());
        OssUtil.oSSObjects.setEndpoint(oss.stream().filter(n -> n.getValue().equals("endpoint")).map(ConfigInfo::getContext).findFirst().get());
        OssUtil.oSSObjects.setBucketName(oss.stream().filter(n -> n.getValue().equals("bucketName")).map(ConfigInfo::getContext).findFirst().get());
        OssUtil.oSSObjects.setAccessKeyId(oss.stream().filter(n -> n.getValue().equals("accessKeyId")).map(ConfigInfo::getContext).findFirst().get());
    }

}
