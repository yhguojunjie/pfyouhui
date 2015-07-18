package com.yoxi.jgframework.system.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.pfhudongtui.plugin.service.PluginAgentService;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.user.service.AgentInfoService;
import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.common.SessionInfo;
import com.yoxi.jgframework.common.model.common.UploadFile;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.common.model.json.ValidForm;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.entity.TSRole;
import com.yoxi.jgframework.system.entity.TSRoleUser;
import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.ContextHolderUtils;
import com.yoxi.jgframework.util.PasswordUtil;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * @ClassName: UserController
 * @Description: TODO(用户管理处理类)
 * @author com.yoxi.jgframework
 */
@Controller
@RequestMapping("/userController")
public class UserController extends BaseServiceController {
	
	@Autowired
	private AgentInfoService agentInfoService;
	
	//代理商插件service
	@Autowired
	private PluginAgentService pluginAgentService;

	
//	/**
//	 * 根据用户，获取菜单列表
//	 * 
//	 * @param request
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@RequestMapping(params = "menu")
//	public void menu(HttpServletRequest request, HttpServletResponse response) {
//		SetListSort sort = new SetListSort();
//		TSUser u = ResourceUtil.getSessionUserName();
//		// 登陆者的权限
//		Set<TSFunction> loginActionlist = new HashSet<TSFunction>();// 已有权限菜单
//		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());
//		for (TSRoleUser ru : rUsers) {
//			TSRole role = ru.getTSRole();
//			List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
//			if (roleFunctionList.size() > 0) {
//				for (TSRoleFunction roleFunction : roleFunctionList) {
//					TSFunction function = (TSFunction) roleFunction.getTSFunction();
//					loginActionlist.add(function);
//				}
//			}
//		}
//		List<TSFunction> bigActionlist = new ArrayList<TSFunction>();// 一级权限菜单
//		List<TSFunction> smailActionlist = new ArrayList<TSFunction>();// 二级权限菜单
//		if (loginActionlist.size() > 0) {
//			for (TSFunction function : loginActionlist) {
//				if (function.getFunctionLevel() == 0) {
//					bigActionlist.add(function);
//				} else if (function.getFunctionLevel() == 1) {
//					smailActionlist.add(function);
//				}
//			}
//		}
//		// 菜单栏排序
//		Collections.sort(bigActionlist, sort);
//		Collections.sort(smailActionlist, sort);
//		String logString = ListtoMenu.getMenu(bigActionlist, smailActionlist);
//		// request.setAttribute("loginMenu",logString);
//		try {
//			response.getWriter().write(logString);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**************************用户列表 begin****************************/

	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "user")
	public String user() {
		return "system/user/userList";
	}

	
	/**
	 * easyuiAJAX用户列表请求数据 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "userGrid")
	public void userGrid(TSUser user,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
		
		String departName = oConvertUtils.getString(request.getParameter("departName"));
		if(StringUtils.isNotBlank(departName)){
			cq.createAlias("TSDepart", "ts");
			cq.add(Restrictions.eq("ts.departName",departName));
		}
		
//		String userName = oConvertUtils.getString(request.getParameter("userName"));//用户名查询字段
//		String realName = oConvertUtils.getString(request.getParameter("realName"));//用户真实名查询字段
//		
//		Short[] userstate = new Short[] { Globals.User_Normal, Globals.User_ADMIN,Globals.User_Forbidden };
//		cq.in("status", userstate);
//		if (oConvertUtils.isEmpty(userName)==false ) {
//			cq.like("userName", userName);//用户名匹配查询
//		}
//		if (oConvertUtils.isEmpty(realName)==false ) {
//			cq.like("realName", realName);//用户真实名匹配查询
//		}
//		cq.add();

		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除用户操作
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "delUser")
	@ResponseBody
	@DeleteAnnotation	
	public AjaxJson delUser(TSUser user, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		
		//----------------------------------------------------------------
		//update-begin--Author:shiyanping  Date:20130319 for：admin账户不能删除
		if("admin".equals(user.getUserName())){
			message = "超级管理员[admin]不可删除";
			j.setMsg(message);
			return j;
		}
		//update-end--Author:shiyanping  Date:20130319 for：admin账户不能删除
		//----------------------------------------------------------------
		user = systemService.getEntity(TSUser.class, user.getId());
		List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (!Globals.User_ADMIN.equals(user.getStatus())) {
			if (roleUser.size()>0) {
				// 删除用户时先删除用户和角色关系表
				delRoleUser(user);
				userService.delete(user);
				//删除代理商用户信息
				AgentInfo agentInfo = agentInfoService.get(AgentInfo.class, user.getId());
				if(agentInfo != null){
					agentInfoService.delete(agentInfo);
				}
				message = "用户：" + user.getUserName() + "删除成功";
				//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			} else {
				userService.delete(user);
				message = "用户：" + user.getUserName() + "删除成功";
			}
		} else {
			message = "超级管理员不可删除";
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 同步删除用户角色关联表
	 * @param user
	 */
	private void delRoleUser(TSUser user) {
		// 同步删除用户角色关联表
		List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (roleUserList.size() >= 1) {
			for (TSRoleUser tRoleUser : roleUserList) {
				systemService.delete(tRoleUser);
			}
		}
	}
	/**
	 * 新建、修改用户页面跳转
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "addorupdateUser")
	public ModelAndView addorupdateUser(TSUser user, HttpServletRequest req) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		if (StringUtil.isNotEmpty(user.getId())) {
			user = systemService.getEntity(TSUser.class, user.getId());
			req.setAttribute("user", user);
			getUserRolesIdAndName(req, user);
		}
		return new ModelAndView("system/user/user");

	}
	
	/**
	 * 根据用户获取，用户对应的角色ID，角色名称列表
	 * @param req
	 * @param user
	 */
	private void getUserRolesIdAndName(HttpServletRequest req, TSUser user) {
		List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		String roleId = "";
		String roleName = "";
		if (roleUsers.size() > 0) {
			for (TSRoleUser tRoleUser : roleUsers) {
				roleId += tRoleUser.getTSRole().getId() + ",";
				roleName += tRoleUser.getTSRole().getRoleName() + ",";
			}
		}
		req.setAttribute("id", roleId);
		req.setAttribute("roleName", roleName);

	}
	
	/**************************用户列表 end****************************/
	
	
	
	
	/**************************用户编辑 begin****************************/
	
	/**
	 * 检查用户名是否重复
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkUser")
	@ResponseBody
	public ValidForm checkUser(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String userName=oConvertUtils.getString(request.getParameter("param"));
		String code=oConvertUtils.getString(request.getParameter("code"));
		List<TSUser> roles=systemService.findByProperty(TSUser.class,"userName",userName);
		if(roles.size()>0&&!code.equals(userName))
		{
			v.setInfo("用户名已存在");
			v.setStatus("n");
		}
		return v;
	}

	/**
	 * 用户新建、修改时，保存用户数据库
	 * 
	 * @param user
	 * @param req
	 * @return
	 * 
	 */

	@RequestMapping(params = "saveUser")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveUser(HttpServletRequest req, TSUser user) {
		AjaxJson j = new AjaxJson();
		// 得到用户的角色
		String roleid = oConvertUtils.getString(req.getParameter("roleid"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		if (StringUtil.isNotEmpty(user.getId())) {
			TSUser users = systemService.getEntity(TSUser.class, user.getId());
			users.setEmail(user.getEmail());
			users.setOfficePhone(user.getOfficePhone());
			users.setMobilePhone(user.getMobilePhone());
			users.setTSDepart(user.getTSDepart());
			users.setRealName(user.getRealName());
			users.setStatus(Globals.User_Normal);
			users.setActivitiSync(user.getActivitiSync());
			users.setPycode(user.getPycode());
			users.setSex(user.getSex());
			users.setBirthday(user.getBirthday());
			users.setIdcard(user.getIdcard());
			if(!"".equals(password) && password != null){
				users.setPassword(PasswordUtil.encrypt(users.getUserName(), password, PasswordUtil.getStaticSalt()));
			}
			systemService.updateEntitie(users);
			List<TSRoleUser> ru = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			systemService.deleteAllEntitie(ru);
			message = "用户: " + users.getUserName() + "更新成功";
			this.isNew = false;
			if (StringUtil.isNotEmpty(roleid)) {
				saveRoleUser(users, roleid);
			}
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName",user.getUserName());
			if (users != null) {
				message = "用户: " + users.getUserName() + "已经存在";
				this.isNew = null;
			} else {
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt()));
				if (user.getTSDepart().equals("")) {
					user.setTSDepart(null);
				}
				user.setStatus(Globals.User_Normal);
//				systemService.save(user);
				Serializable id =  systemService.saveForId(user);
				//代理商处理
				if(StringUtils.isNotBlank(user.getTSDepart().getId())){
					if (user.getTSDepart().getId().equals("01000000")){
						AgentInfo agentInfo = new AgentInfo();
						agentInfo.setId(Integer.valueOf(id.toString()));
						agentInfo.setCreateTime(new Date());
						agentInfo.setUpdateTime(new Date());
						agentInfo.setIsOfficial("0");
						agentInfo.setBlance(0.00);
						agentInfo.setTotalIncome(0.00);
						agentInfo.setTotalCash(0.00);
						systemService.save(agentInfo);
						pluginAgentService.savePluginAgent(agentInfo);
					}
				} 
				
				message = "用户: " + user.getUserName() + "添加成功";
				this.isNew = true;
				if (StringUtil.isNotEmpty(roleid)) {
					saveRoleUser(user, roleid);
				}
				//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}

		}
		j.setMsg(message);

		return j;
	}

	/**
	 * 保存用户角色关联信息
	 * @param user
	 * @param roleidstr
	 */
	private void saveRoleUser(TSUser user, String roleidstr) {
		String[] roleids = roleidstr.split(",");
		for (int i = 0; i < roleids.length; i++) {
			TSRoleUser rUser = new TSRoleUser();
			TSRole role = systemService.getEntity(TSRole.class, oConvertUtils.getInt(roleids[i]));
			rUser.setTSRole(role);
			rUser.setTSUser(user);
			systemService.save(rUser);

		}
	}

	/**
	 * 用户选择角色跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "roles")
	public String roles() {
		return "system/user/users";
	}

	/**
	 * 角色显示列表,获取Grid数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "roleGrid")
	public void roleGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**************************用户编辑 end****************************/

	

	/*************************用户信息显示  begin*********************************/
	/**
	 * 用户信息显示
	 * 
	 * @return
	 */
	@RequestMapping(params = "userinfo")
	public String userinfo(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/userinfo";
	}
	/*************************用户信息显示  end*********************************/

	/*************************设置用户签名  begin*********************************/
	/**
	 * 设置用户签名跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "userSign")
	public ModelAndView userSign(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return new ModelAndView("system/user/usersign");
	}

	/**
	 * 用户录入
	 * 
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "saveSign", method = RequestMethod.POST)
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveSign(HttpServletRequest req) {
		UploadFile uploadFile = new UploadFile(req);
		String id = uploadFile.get("id");
		TSUser user = systemService.getEntity(TSUser.class, id);
		uploadFile.setRealPath("signatureFile");
		uploadFile.setCusPath("signature");
		uploadFile.setByteField("signature");
		uploadFile.setBasePath("resources");
		uploadFile.setRename(false);
		uploadFile.setObject(user);
		AjaxJson j = new AjaxJson();
		message = user.getUserName() + "设置签名成功";
		this.isNew = false;
		systemService.uploadFile(uploadFile);
		//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);

		return j;
	}
	
	/*************************设置用户签名  end*********************************/

	
	/*************************修改密码  begin*********************************/
	
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "changePassword")
	public String changePassword(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/changepassword";
	}

	/**
	 * 修改密码时，保存密码数据
	 * 
	 * @return
	 */
	@RequestMapping(params = "savePassword")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePassword(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		String password = oConvertUtils.getString(request.getParameter("password"));
		String newpassword = oConvertUtils.getString(request.getParameter("newpassword"));
		String pString = PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt());
		if (!pString.equals(user.getPassword())) {
			j.setMsg("原密码不正确");
			this.isNew = null;
			j.setSuccess(false);
		} else {
			try {
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), newpassword, PasswordUtil.getStaticSalt()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			systemService.mergeEntitie(user);
			j.setMsg("用户：" + user.getUserName() + "密码修改成功");
			this.message = "用户：" + user.getUserName() + "密码修改成功";
			this.isNew =false;
			
			//更新session
			HttpSession session = ContextHolderUtils.getSession();
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Globals.USER_SESSION);
			sessionInfo.setUser(user);
		}
		return j;
	}
	public static void main(String[] args) {
//		zhongxin PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt())
		System.out.println(PasswordUtil.encrypt("zhongxin", "123456", PasswordUtil.getStaticSalt()));
	}
	
	/*************************修改密码  end*********************************/
}