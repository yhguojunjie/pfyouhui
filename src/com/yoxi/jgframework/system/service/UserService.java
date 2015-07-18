package com.yoxi.jgframework.system.service;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.jgframework.system.entity.TSUser;


public interface UserService extends CommonService{

	public TSUser checkUserExits(TSUser user);
	public String getUserRole(TSUser user);
	public void pwdInit(TSUser user, String newPwd);
}
