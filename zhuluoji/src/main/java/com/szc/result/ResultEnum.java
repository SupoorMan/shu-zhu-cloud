package com.szc.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定义   返回的枚举
 * 格式： 实体类_说明_级别(code,message)
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(200, "success"),
    ERROR(500, "error"),

    FAIL(1001, "fail"),


    ;

    private final int code;
    private final String message;
}
