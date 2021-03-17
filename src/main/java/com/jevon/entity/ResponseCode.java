package com.jevon.entity;

public enum ResponseCode {
	SUCCESS(200, "SUCCESS"),
	ERROR(404, "ERROR"),
	NEED_LOGIN(403, "NEED_LOGIN"), 
	ILLEGAL_ARGUMENT(401, "ILLEGAL_ARGUMENT");

	private final int code;
	private final String desc;

	ResponseCode(int code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public int getCode(){
		return code;
	}

	public String getDesc(){
		return desc;
	}

}
