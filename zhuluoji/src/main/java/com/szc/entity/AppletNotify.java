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
 * 通知表
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AppletNotify对象", description="通知表")
public class AppletNotify implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "通知类型: 0.站内 1.系统 2.直播预告 3.活动消息 4.订单物流 5.等级 6.积分")
    private Integer type;

    @ApiModelProperty(value = "通知消息/模板")
    private String message;

    @ApiModelProperty(value = "通知消息类型 0.消息 1.模板")
    private Integer messageType;

    @ApiModelProperty(value = "状态: 0.关闭 1.开启")
    private Integer state;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
