package com.yoxi.jgframework.common.model.common;

import java.io.Serializable;

import com.yoxi.jgframework.system.entity.TSUser;



public class SessionInfo implements Serializable {

	private static final long serialVersionUID = -3851655461178618298L;
	private TSUser user;
	public TSUser getUser() {
		return user;
	}

	public void setUser(TSUser user) {
		this.user = user;
	}

}
