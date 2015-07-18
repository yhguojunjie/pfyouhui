package com.yoxi.pfhudongtui.common.interceptors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.yoxi.jgframework.extend.datasource.DataSourceContextHolder;
import com.yoxi.jgframework.extend.datasource.DataSourceType;

/**
 * 
 * 登陆拦截
 * 
 * 2015-02-04
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	 private static final Logger log = Logger.getLogger(LoginInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse response,
			Object obj, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jwhat);
/*		HttpSession cmSession = request.getSession();
		ServletContext Context = cmSession.getServletContext();
		ServletContext cmContext = Context.getContext("/cm");
		log.info("*********cmContext********="+cmContext);
		if (cmContext != null && !cmContext.equals("")) {
//			HttpSession session = (HttpSession) cmContext.getAttribute("cmSession");
//			log.info("*********session********="+session);
//			log.info("*********session.getAttribute loginUser********="+session.getAttribute("loginUser"));
//			AcUser user = (AcUser) session.getAttribute("loginUser");
//			Object object = session.getAttribute("loginUser");
			log.info("*********当前登陆用户id********="+cmContext.getAttribute("loginUserId"));
			return true;
		}
		return false;*/
		return true;
	}

}
