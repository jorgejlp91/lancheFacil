package com.lanchefacil.entity;

import org.springframework.http.HttpStatus;

public class DefaultMsg {
	
	private Integer code;
	private String msg;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static DefaultMsg ok(String stringMsg){
		DefaultMsg msg = new DefaultMsg();
		msg.setCode(HttpStatus.OK.value());
		msg.setMsg(stringMsg);
		return msg;
	}
	
	public static DefaultMsg error(String stringMsg){
		DefaultMsg msg = new DefaultMsg();
		msg.setCode(HttpStatus.BAD_REQUEST.value());
		msg.setMsg(stringMsg);
		return msg;
	}


}
