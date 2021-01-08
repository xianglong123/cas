package com.cas.service.webssh.pojo;

import lombok.Data;

/**
 * @ClassName Result
 * @Author luo jin jiang
 * @Date 2020/3/22 15:58
 * @Version 1.0
 */
@SuppressWarnings("ALL")
@Data
public class Result<T> {
    private final static String SUCCESS_CODE = "0";
    private final static String SUCCESS_MSG = "成功";
    private final static String FAIL_CODE = "1";
    private final static String FAIL_MSG = "失败";


    private String code;
    private String msg;
    private T data;

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return new Result(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> Result<T> fail() {
        return new Result(FAIL_CODE, FAIL_MSG, null);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result(FAIL_CODE, msg, null);
    }

    public static Result fail(String code, String msg) {
        return new Result(code, msg, null);
    }
}
