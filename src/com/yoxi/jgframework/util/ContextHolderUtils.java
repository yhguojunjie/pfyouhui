package com.yoxi.jgframework.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yoxi.jgframework.common.model.common.SessionInfo;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.system.entity.TSUser;

/**
 * @ClassName: ContextHolderUtils
 * @Description: TODO(上下文工具类)
 * @author com.yoxi.jgframework
 * @date 2012-12-15 下午11:27:39
 * 
 */
public class ContextHolderUtils {
	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes == null) {
			return null;
		}
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return request;

	}

	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest() != null ? getRequest().getSession(false) : null;
	}

	public static TSUser getUser() {
		HttpSession session = getSession();
		if (session != null) {
			SessionInfo sessioninfo = (SessionInfo) session.getAttribute(Globals.USER_SESSION);
			if (sessioninfo != null && sessioninfo.getUser() != null) {
				return sessioninfo.getUser();
			}
		}
		return null;
	}
}
