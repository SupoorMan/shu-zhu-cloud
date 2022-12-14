package com.szc.transfer;

import com.szc.constant.Pages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("小程序用户分页查询使用 - AppletUserPage")
public class AppletUserPage extends Pages {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "uid")
    private String uid;

    @ApiModelProperty(value = "昵名")
    private String nickname;

    @ApiModelProperty(value = "openid")
    private String openid;

    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    @ApiModelProperty(value = "0.女 1.男")
    private Integer gender;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "积分")
    private BigDecimal integral;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "经验")
    private Integer experience;

    @ApiModelProperty(value = "成长值")
    private Integer growth;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "DY账号")
    private String dyId;

    @ApiModelProperty(value = "TB账号")
    private String tbId;

    @ApiModelProperty(value = "小红书账号")
    private String redbookId;

    @ApiModelProperty(value = "状态: 0.禁用 1.可用")
    private Integer state;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
