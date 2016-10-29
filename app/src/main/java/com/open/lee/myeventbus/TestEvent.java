package com.open.lee.myeventbus;

/**
 * Created by Lee on 2016/10/29.
 */

public class TestEvent {
    private String msg;
    public TestEvent(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
