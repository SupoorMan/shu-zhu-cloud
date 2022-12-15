package com.szc.transfer;

import com.szc.constant.Pages;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("积分商品分页 - IntegralProductPage")
public class IntegralProductPage extends Pages {
}
