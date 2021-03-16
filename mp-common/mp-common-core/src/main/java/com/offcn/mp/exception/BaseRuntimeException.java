package com.offcn.mp.exception;

import com.offcn.mp.enums.ErrorCode;
import lombok.Data;

/**
 * @ClassName BaseRuntimeException
 * @Description 运行时异常基类
 * @Autor ansonglin
 * @Date 2019/5/7 9:32
 * @Version 1.0
 **/
@Data
public class BaseRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;
    private int code;

    public BaseRuntimeException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BaseRuntimeException(String msg, Throwable e) {
        super(msg, e);
        this.message = msg;
    }

    public BaseRuntimeException(int code, String msg) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public BaseRuntimeException(int code, String msg, Throwable e) {
        super(msg, e);
        this.message = msg;
        this.code = code;
    }

    public BaseRuntimeException(String msg, Exception e) {
        super(msg, e);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(ErrorCode error){
        super(error.getMessage());
        this.message = error.getMessage();
        this.code = error.getCode();
    }

}
   