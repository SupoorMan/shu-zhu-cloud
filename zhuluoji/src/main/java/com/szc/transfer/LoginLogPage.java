package com.szc.transfer;

import com.szc.constant.Pages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户登录信息分页 - LoginLogPage")
public class LoginLogPage extends Pages {

    @ApiModelProperty(value = "类型: 0.小程序 1.系统后台")
    private Integer type;
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
}
