package com.yoxi.pfhudongtui.utils;

import java.io.Serializable;

public class ResponseCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1752371136188992662L;
	
	/** 鍝嶅簲浠ｇ爜 */
	protected String code;
	/**
	 * 鍝嶅簲缁撴灉淇℃伅
	 */
	protected String msg;

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
