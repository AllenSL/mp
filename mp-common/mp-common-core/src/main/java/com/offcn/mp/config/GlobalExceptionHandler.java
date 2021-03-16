package com.offcn.mp.config;

import com.offcn.mp.enums.ErrorCode;
import com.offcn.mp.exception.BaseRuntimeException;
import com.offcn.mp.util.ResultInfo;
import com.offcn.mp.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理器
 * @Autor ansonglin
 * @Date 2019/5/7 11:21
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 处理自定义异常，可在此处集中输出异常日志
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public ResultInfo exceptionHandler(BaseRuntimeException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理未捕获异常，打印出堆栈详情
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultInfo exceptionHandler(Exception e, HttpServletResponse response) {
        log.error("系统出现未捕获异常，异常信息为：", e.getMessage(), e);
        return ResultUtil.error("网络繁忙，请稍候重试...");
    }

    /**
     * Validation注解参数异常类处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultInfo exceptionHandler(MethodArgumentNotValidException e) {
        return ResultUtil.error( ErrorCode.EC_CHECKFAIL_ERROR.getCode(), e.getBindingResult().getFieldError().getDefaultMessage() );
    }


    /**
     * 参数校验异常类处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultInfo exceptionHandler(IllegalArgumentException e) {
        log.error("参数异常：{}",e.getMessage(),e);
        return ResultUtil.error(ErrorCode.EC_CHECKFAIL_ERROR);
    }

    /**
     * 参数校验异常类处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultInfo exceptionHandler(BindException e) {
        return ResultUtil.error( ErrorCode.EC_CHECKFAIL_ERROR.getCode(), e.getFieldError().getDefaultMessage() );
    }

    /**
     * 网关验证异常类处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResultInfo exceptionHandler(AuthException e) {
        return ResultUtil.error(ErrorCode.EC_UNAUTHORIZED);
    }
}
   