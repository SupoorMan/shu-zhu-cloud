package com.szc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 签到表
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SignIn对象", description = "签到表")
public class SignIn implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "小程序用户id")
    private Integer appletUserId;
    @ApiModelProperty(value = "所得积分")
    private String integral;
    @ApiModelProperty(value = "额外积分")
    private String otherIntegral;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
