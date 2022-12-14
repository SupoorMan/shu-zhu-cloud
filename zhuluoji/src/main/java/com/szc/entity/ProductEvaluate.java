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
 * 积分商品评价表
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductEvaluate对象", description="积分商品评价表")
public class ProductEvaluate implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "积分商品id")
    private Integer productId;

    @ApiModelProperty(value = "小程序用户id")
    private Integer appletUserId;

    @ApiModelProperty(value = "评价消息")
    private String message;

    @ApiModelProperty(value = "评价类型")
    private Integer type;

    @ApiModelProperty(value = "红星数")
    private Integer starter;

    @ApiModelProperty(value = "状态: 0.正常 1.删除 2.隐藏")
    private Integer state;

    private LocalDateTime createTime;


}
