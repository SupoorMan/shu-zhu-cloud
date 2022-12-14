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
 * 积分商品表
 * </p>
 *
 * @author Poor
 * @since 2022-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="积分商品 - IntegralProduct")
public class IntegralProduct implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "标签类型: 0.新品 1.人气")
    private Integer tagType;

    @ApiModelProperty(value = "价值积分")
    private BigDecimal integral;

    @ApiModelProperty(value = "商品类型: 0.玻璃餐具 1.睡衣浴袍 2.床上用品 3.家具装点")
    private Integer productType;

    @ApiModelProperty(value = "限购数量")
    private Integer purchaseLimit;

    @ApiModelProperty(value = "红星数")
    private Integer starter;

    @ApiModelProperty(value = "详情图文内容")
    private String details;

    @ApiModelProperty(value = "0.下架 1.上架")
    private Integer shopping;

    @ApiModelProperty(value = "推荐: 0.不是 1.是")
    private Integer recommend;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
