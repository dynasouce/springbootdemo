package com.dy.learn.learn.mq;

public enum QueueEnum {

    PayQueue("PayQueue","PayQueue"),
    LoginQueue("LoginQueue","LoginQueue");

    private String code;
    private String msg;

    private QueueEnum(String code,String msg){
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
