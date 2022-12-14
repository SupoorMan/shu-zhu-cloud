package com.szc.controller;


import com.szc.service.ConfigInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 配置表 前端控制器
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@RestController
@RequestMapping("/configInfo")
public class ConfigInfoController {

    @Autowired
    private ConfigInfoService configInfoService;





}
