package com.szc.result;

/**
 * result 工具
 */
public class ResultUtil {

    // success
    public static Result successMsg(String msg) {
        return success(msg, null);
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        return new Result(ResultEnum.SUCCESS, data);
    }

    public static Result success(String msg, Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    // fail
    public static Result fail() {
        return new Result(ResultEnum.FAIL, null);
    }

    public static Result fail(String msg) {
        return new Result(ResultEnum.FAIL.getCode(), msg, null);
    }


    // error
    public static Result error() {
        return new Result(ResultEnum.ERROR, null);
    }

    public static Result error(String msg) {
        return error(ResultEnum.ERROR.getCode(), msg);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(String msg, Object data) {
        return error(ResultEnum.ERROR.getCode(), msg, data);
    }

    public static Result error(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result error(ResultEnum resultEnum) {
        return error(resultEnum, null);
    }

    public static Result error(ResultEnum resultEnum, Object data) {
        return new Result(resultEnum, data);
    }

}
