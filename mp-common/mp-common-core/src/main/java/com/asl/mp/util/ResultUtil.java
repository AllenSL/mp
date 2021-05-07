package com.asl.mp.util;

import com.asl.mp.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Map;

@Slf4j
public abstract class ResultUtil implements Serializable {
    private static final long serialVersionUID = -7461152521445512321L;

    /**
     * 默认成功编码
     */
    public static final int DEFAULT_SUCC_CODE = 200;

    /**
     * 默认失败编码--系统错误
     */

    public static ResultInfo success() {
        return success( null, null );
    }

    public static ResultInfo success(Object resultData) {
        return success( null, resultData );
    }

    public static ResultInfo success(String message, Object resultData) {
        ResultInfo result = new ResultInfo();
        result.setCode( DEFAULT_SUCC_CODE );
        result.setMsg( message );
        result.setData( resultData );
        return result;
    }

    public static ResultInfo simplesuccess(String message) {
        ResultInfo result = new ResultInfo();
        result.setCode( DEFAULT_SUCC_CODE );
        result.setMsg( message );
        result.setData( null );
        return result;
    }

    public static ResultInfo error(ErrorCode codeEnum) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode( codeEnum.getCode() );
        resultInfo.setMsg( codeEnum.getMessage() );
        return resultInfo;
    }

    public static ResultInfo error(int errorCode, String message) {
        ResultInfo result = new ResultInfo();
        result.setCode( errorCode );
        result.setMsg( message );
        return result;
    }

    public static ResultInfo error(String message) {
        ResultInfo result = new ResultInfo();
        result.setCode( ErrorCode.EC_SERVER_ERROE.getCode() );
        result.setMsg( ErrorCode.EC_SERVER_ERROE.getMessage() );
        return result;
    }

    public static ResultInfo error(int errorCode, String message, Object resultData) {
        ResultInfo result = new ResultInfo();
        result.setCode( errorCode );
        result.setMsg( message );
        result.setData( resultData );
        return result;
    }

    public static ResultInfo sendPostRequest(String url, Map<String, Object> params) {
        RestTemplate client = new RestTemplate();
        //设置超时
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(1500); //单位为ms 建立连接超时
        clientHttpRequestFactory.setReadTimeout(2000); //单位为ms 建立连接成功后 从服务器读取超时
        client.setRequestFactory(clientHttpRequestFactory);
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以什么方式提交，自行选择，一般使用json，或者表单
        headers.setContentType( MediaType.APPLICATION_JSON_UTF8 );
        // 将请求头部和参数合成一个请求
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>( params, headers );
        // 执行HTTP请求，将返回的结构使用Response类格式化
        ResponseEntity<ResultInfo> response = null;
        try {
            response = client.exchange( url, method, requestEntity, ResultInfo.class );
        } catch (Exception e) {
            log.error("执行HTTP请求失败，异常信息：{}", e.getMessage(), e);
        }

        if (response != null) {
            return response.getBody();
        }
        return ResultUtil.error( ErrorCode.EC_TIME_OUT );

    }
}
