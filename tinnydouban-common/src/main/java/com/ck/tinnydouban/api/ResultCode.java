package com.ck.tinnydouban.api;


public enum ResultCode implements IResultCode {

    SUCCESS(200, "操作成功"),

    FAILED(500, "操作失败"),

    VALIDATE_FAILED(404, "参数检验失败"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    FORBIDDEN(403, "没有相关权限"),

    LOCKED(405, "账号被锁定，请联系管理员进行解锁"),

    AUTHORIZATION(406, "权限错误");


    private int code;

    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return message;
    }
}
