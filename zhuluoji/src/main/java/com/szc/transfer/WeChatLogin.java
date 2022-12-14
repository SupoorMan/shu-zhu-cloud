package com.szc.transfer;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("微信登录 - WeChatLogin")
public class WeChatLogin {

    @ApiModelProperty(value = "微信登录凭证[code]")
    @NotBlank(message = "微信登录凭证不允许为空")
    private String code;

    @ApiModelProperty(value = "微信用户信息")
    private WeChatUserInfo userInfo;
}
