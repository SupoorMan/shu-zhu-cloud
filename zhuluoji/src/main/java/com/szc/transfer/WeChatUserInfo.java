package com.szc.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@ApiModel("微信用户信息 - WeChatUserInfo")
public class WeChatUserInfo {

    @Schema(name = "nickName", description = "昵名")
    private String nickName;

    @Schema(name = "gender", description = "性别")
    private Integer gender;

    @Schema(name = "language", description = "语言")
    private String language;

    @Schema(name = "city", description = "城市")
    private String city;

    @Schema(name = "province", description = "省")
    private String province;

    @Schema(name = "country", description = "区县")
    private String country;

    @Schema(name = "avatarUrl", description = "头像url")
    private String avatarUrl;


}
