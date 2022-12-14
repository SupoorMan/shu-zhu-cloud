package com.szc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 积分商品订单
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Orders对象", description="积分商品订单")
public class Orders implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "价值积分")
    private BigDecimal integral;

    @ApiModelProperty(value = "购买规格/数量")
    private String amount;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "收货地址详情")
    private String receiveAddress;

    @ApiModelProperty(value = "运费")
    private BigDecimal expressFee;

    @ApiModelProperty(value = "订单状态: -1.取消 0.下单未支付 1.下单支付完成 2.发货中 3.签收完成")
    private Integer status;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "完成时间")
    private LocalDateTime finishTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
