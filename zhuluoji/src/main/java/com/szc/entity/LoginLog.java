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
 * 用户登录记录表
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="用户登录记录 - LoginLog")
public class LoginLog implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private Integer id;

    @ApiModelProperty(value = "小程序用户id")
    private Integer userId;

    @ApiModelProperty(value = "类型: 0.小程序 1.系统后台")
    private Integer type;

    private LocalDateTime createTime;


}
