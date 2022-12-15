package com.szc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 配置表
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ConfigInfo对象", description = "配置表")
public class ConfigInfo implements Serializable {


    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private Integer id;

    @ApiModelProperty(value = "key")
    @TableField("`key`")
    private String key;

    @ApiModelProperty(value = "value")
    private String value;

    @ApiModelProperty(value = "context")
    private String context;

    @ApiModelProperty(value = "details")
    private String details;

    @ApiModelProperty(value = "状态: 0.关闭 1.开启")
    private Integer state;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
