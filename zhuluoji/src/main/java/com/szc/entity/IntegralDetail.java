package com.szc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 积分详情
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="IntegralDetail对象", description="积分详情")
public class IntegralDetail implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "积分的变动")
    private String integral;

    @ApiModelProperty(value = "小程序用户id")
    private Integer appletUserId;

    @ApiModelProperty(value = "变动类型")
    private Integer type;

    @ApiModelProperty(value = "详情消息说明")
    private String detail;

    private LocalDateTime createTime;


}
