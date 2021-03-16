package com.offcn.mp.enums;

/**
 * Created by @author XuYangW on 2019/7/9 0009.
 **/
public enum ErrorCode {

    /**
     * 请求成功
     */
    EC_OK( 200, "请求成功" ),

    /**
     * 请求失败
     */
    EC_SERVER_ERROE(500,"请求失败"),

    /**************** 通用异常码 401-501 ***********/
    EC_CHECKFAIL_ERROR(400,"参数校验异常"),
    EC_TIME_OUT(408,"请求超时"),
    EC_UNAUTHORIZED(403,"权限不足");

    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorCode fromValue(Integer code) {
        for (ErrorCode s : ErrorCode.values()) {
            if (s.code.equals( code )) {
                return s;
            }
        }
        return null;
    }
}