package com.mss.boot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="响应对象")
public class BaseRes<T> {

	@ApiModelProperty(value="响应码")
	private String code;
	
	@ApiModelProperty(value="响应信息")
	private String msg;
	
	@ApiModelProperty(value="响应报文体")
	private T body;

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

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	
}
