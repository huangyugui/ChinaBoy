package com.mss.boot.enums;

public enum BaseCodeEnum {

	CODE_0000("0000","成功"),
	CODE_9999("9999","失败");
	
	private String code;
	private String msg;
	
	private BaseCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
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
