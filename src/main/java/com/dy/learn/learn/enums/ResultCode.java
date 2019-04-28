package com.dy.learn.learn.enums;

public enum ResultCode {

    SUCCESS(1000,"成功"),
    USENAME_NOT_EXISTS(2001,"用户不存在"),
    UNKNOW(9999,"未知异常");

    private int errorCode;
    private String errorMsg;

    ResultCode(int code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
