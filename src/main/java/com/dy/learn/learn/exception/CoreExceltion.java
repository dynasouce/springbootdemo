package com.dy.learn.learn.exception;

import com.dy.learn.learn.enums.ResultCode;

public class CoreExceltion extends RuntimeException {

    private int errorCode;
    private String errorMsg;

    public CoreExceltion(String message){
        super(message);
        this.errorMsg = message;
    }

    public CoreExceltion(ResultCode code){
        super(code.getErrorMsg());
        this.errorMsg = code.getErrorMsg();
        this.errorCode=code.getErrorCode();
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

    @Override
    public String toString() {
        return "CoreExceltion{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
