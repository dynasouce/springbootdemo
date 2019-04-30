package com.dy.learn.learn.enums;

public enum EQueueEnum {

    PayQueue("PayQueue","PayQueue"),
    LoginQueue("LoginQueue","LoginQueue");

    private String code;
    private String msg;

    private EQueueEnum(String code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
