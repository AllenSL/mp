package com.mx.server.entity.response;


public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static Result getRespCode(int errCode) {
        Result result = new Result();
        result.setRespCode(errCode);
        return result;
    }

    public static Result getRespCodeMsg(int errCode, String msg) {
        Result result = new Result();
        result.setRespCode(errCode);
        result.setMsg(msg);
        return result;
    }

}
