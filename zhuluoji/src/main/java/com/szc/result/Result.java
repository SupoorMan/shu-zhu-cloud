package com.szc.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("统一响应体 - Result")
public class Result {

    @ApiModelProperty(value = "响应码: 200.成功 500.错误 1000以上.失败")
    private int code;
    @ApiModelProperty(value = "响应消息")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private Object data;


    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    public Result(ResultEnum resultEnum, Object data) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = data;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{code:" + code +
                ", msg:'" + msg +
                "', data:" + data +
                '}';
    }
}
