package com.szc.controller;

import com.szc.oss.OssUtil;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "文件管理")
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传", notes = "文件上传 传项目名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", paramType = "form", dataType = "java.io.File", required = true),
            @ApiImplicitParam(name = "area", dataType = "java.lang.String", value = "传项目名称", required = true)
    })
    public Result upload(@RequestPart("file") MultipartFile file, String area) throws IOException {
        return ResultUtil.success(OssUtil.upload(file.getInputStream(), area
                + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename()));
    }


}
