package com.jevon.message;

public class SupportInfo {
    private int code; // 0 : 当前无客服在线; 1:当前有空闲客服 ； -1：当前客服忙线。
    private String supportId;

    public SupportInfo(int code, String supportId) {
        this.code = code;
        this.supportId = supportId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSupportId() {
        return supportId;
    }

    public void setSupportId(String supportId) {
        this.supportId = supportId;
    }
}
