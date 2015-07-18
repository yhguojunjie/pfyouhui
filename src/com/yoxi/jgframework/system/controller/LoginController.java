package com.yoxi.jgframework.system.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yoxi.jgframework.annotation.LoginAnnotation;
import com.yoxi.jgframework.annotation.LogoutAnnotation;
import com.yoxi.jgframework.common.model.common.SessionInfo;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.extend.datasource.DataSourceContextHolder;
import com.yoxi.jgframework.extend.datasource.DataSourceType;
import com.yoxi.jgframework.system.entity.TSConfig;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.system.entity.TSRole;
import com.yoxi.jgframework.system.entity.TSRoleFunction;
import com.yoxi.jgframework.system.entity.TSRoleUser;
import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.util.ContextHolderUtils;
import com.yoxi.jgframework.util.ListtoMenu;
import com.yoxi.jgframework.util.NumberComparator;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.util.oConvertUtils;

/**
 * 登陆初始化控制器
 * 
 * 
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseServiceController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(params = "goPwdInit")
	public String goPwdInit(){
		return "login/pwd_init";
	}

	/**
	 * admin账户密码初始化
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "pwdInit")
	public ModelAndView pwdInit(HttpServletRequest request){
		ModelAndView modelAndView = null;
		TSUser user = new TSUser();
		user.setUserName("admin");
		String newPwd = "123456";
		userService.pwdInit(user, newPwd);
		modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
		return modelAndView;
	}

	/**
	 * 检查用户名称，
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "checkuser")
	@ResponseBody
	@LoginAnnotation
	// 登录时间跟踪
	public AjaxJson checkuser(TSUser user, HttpServletRequest req){
		HttpSession session = ContextHolderUtils.getSession();
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jwhat);
		AjaxJson j = new AjaxJson();
		TSUser u = userService.checkUserExits(user);
		if (u != null) {
			if(u.getStatus() == 0){
				logger.error("该账号已被禁用!");
				j.setMsg("该账号已被禁用!");
				j.setSuccess(false);
				return j;
			}
			if (true) {
				message = "用户: " + user.getUserName() + "登录成功";
				SessionInfo sessionInfo = new SessionInfo();
				sessionInfo.setUser(u);
				session.setMaxInactiveInterval(60 * 30);
				session.setAttribute(Globals.USER_SESSION, sessionInfo);
			}
		} else {
			message = "用户: " + user.getUserName() + "用户名或密码错误!";
			logger.error("用户名或密码错误!");
			j.setMsg("用户名或密码错误!");
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(params = "login")
	public String login(HttpServletRequest request){
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jwhat);
		TSUser user = ResourceUtil.getSessionUserName();
		String roles = "";
		if (user != null) {
			List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			for (TSRoleUser ru : rUsers) {
				TSRole role = ru.getTSRole();
				roles += role.getRoleName() + ",";
			}
			request.setAttribute("roleName", roles);
			request.setAttribute("userName", user.getRealName());
			return "main/main";
		} else {
			return "login/login";
		}

	}

	/**
	 * 退出系统
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "logout")
	@LogoutAnnotation
	// 登录时间跟踪
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView modelAndView = null;

		HttpSession session = ContextHolderUtils.getSession();
		// String versionCode =
		// oConvertUtils.getString(request.getParameter("versionCode"));
		TSUser user = ResourceUtil.getSessionUserName();
		// 根据版本编码获取当前软件版本信息
		// TSVersion version =
		// systemService.findUniqueByProperty(TSVersion.class, "versionCode",
		// versionCode);

		// update-begin--Author:chenxu Date:20130322 for：左侧菜单信息放入到session中
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			session.removeAttribute(oConvertUtils.getString(role.getId()));
		}
		// update-end--Author:chenxu Date:20130322 for：左侧菜单信息放入到session中

		// 判断用户是否为空不为空则清空session中的用户object
		session.removeAttribute(Globals.USER_SESSION);// 注销该操作用户
		this.message = "用户" + user.getUserName() + "已退出";
		// systemService.addLog("用户" + user.getUserName() + "已退出",
		// Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
		modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));

		return modelAndView;
	}

	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request){
		TSUser user = ResourceUtil.getSessionUserName();
		String roles = "";

		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		// update-begin--Author:tanghong Date:20130531 for：[140]左侧菜单报错
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(new RedirectView("loginController.do?login"));
		}
		// update-end--Author:tanghong Date:20130531 for：[140]左侧菜单报错

		// 登陆者的权限
		Set<TSFunction> loginActionlist = new HashSet<TSFunction>();// 已有权限菜单
		// update-begin--Author:chenxu Date:20130322 for：左侧菜单信息放入到session中
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			roles += role.getRoleName() + ",";
			List<TSRoleFunction> roleFunctionList = ResourceUtil.getSessionTSRoleFunction(role.getId());
			if (roleFunctionList == null || roleFunctionList.size() == 0) {
				// HttpSession session = ContextHolderUtils.getSession();
				session.setMaxInactiveInterval(60 * 30);
				roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
				session.setAttribute(oConvertUtils.getString(role.getId()), roleFunctionList);
				// update-begin--Author:tanghong Date:20130531 for：[140]左侧菜单报错
			} else {
				if (roleFunctionList.get(0).getId() == null) {
					roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
				}
				// update-end--Author:tanghong Date:20130531 for：[140]左侧菜单报错
			}
			for (TSRoleFunction roleFunction : roleFunctionList) {
				TSFunction function = (TSFunction) roleFunction.getTSFunction();
				loginActionlist.add(function);
			}
		}
		// update-end--Author:chenxu Date:20130322 for：左侧菜单信息放入到session中

		List<TSFunction> bigActionlist = new ArrayList<TSFunction>();// 一级权限菜单
		List<TSFunction> smailActionlist = new ArrayList<TSFunction>();// 二级权限菜单
		if (loginActionlist.size() > 0) {
			for (TSFunction function : loginActionlist) {
				if (function.getFunctionLevel() == 0) {
					bigActionlist.add(function);
				} else if (function.getFunctionLevel() == 1) {
					smailActionlist.add(function);
				}
			}
		}
		// 菜单栏排序
		Collections.sort(bigActionlist, new NumberComparator());
		Collections.sort(smailActionlist, new NumberComparator());
		String logString = ListtoMenu.getEasyuiMenu(bigActionlist, smailActionlist);
		request.setAttribute("loginMenu", logString);
		request.setAttribute("parentFun", bigActionlist);
		request.setAttribute("roleName", roles);
		request.setAttribute("userName", user.getRealName());
		request.setAttribute("childFun", smailActionlist);
		request.setAttribute("userName", user.getRealName());
		List<TSConfig> configs = userService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {

			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/left");
	}

	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request){
		return new ModelAndView("main/home");
	}

	// update-start--Author:邢双阳 Date:20130525 for[105]：菜单权限控制
	/**
	 * 无权限页面提示跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "noAuth")
	public ModelAndView noAuth(HttpServletRequest request){
		return new ModelAndView("common/noAuth");
	}
	// update-end--Author:邢双阳 Date:20130525 for[105]：菜单权限控制
}
