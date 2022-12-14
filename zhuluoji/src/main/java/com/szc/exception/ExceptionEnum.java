package com.szc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 继承 http 标准异常标码
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    ERROR(500,"未知错误 😳"),


    CSV_ERROR(10001,"csv解析失败 😳"),
    ;

    private final int code;
    private final String msg;

}
