package com.dy.learn.learn.bean;

import com.dy.learn.learn.enums.ResultCode;

public class Result <T>{

    private int errorCode;

    private String errorMsg;

    private String url;

    private T data;


    public static <T> Result<T> build(ResultCode resultCode){
        Result<T> result = new Result<T>();
        result.setErrorCode(resultCode.getErrorCode());
        result.setErrorMsg(resultCode.getErrorMsg());
        return result;
    }

    public void setResultCode(ResultCode resultCode){
        this.errorCode = resultCode.getErrorCode();
        this.errorMsg = resultCode.getErrorMsg();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
