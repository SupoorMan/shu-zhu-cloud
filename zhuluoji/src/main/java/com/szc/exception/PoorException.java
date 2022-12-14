package com.szc.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * PoorException 继承 RuntimeException
 * 自定义code,message 构造器
 * poor == exception
 */
@Slf4j
public class PoorException extends RuntimeException {

    // 错误码/响应码
    private int code;

    public PoorException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public PoorException(int code, String message) {
        super(message);
        this.code = code;
    }

    public PoorException(String message) {
        super(message);
        this.code = ExceptionEnum.ERROR.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}