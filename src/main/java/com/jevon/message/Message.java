package com.jevon.message;

import com.alibaba.fastjson.JSONObject;

public class Message {
    private String name;
    private String content;
    private int type; // 0: from_user , 1: from_support  -1:from_server
    private String time;

    public Message(String name, String content, int type, String time) {
        this.name = name;
        this.content = content;
        this.type = type;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toJsonString(){
        return JSONObject.toJSONString(this);
    }
}
