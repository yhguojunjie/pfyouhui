package com.yoxi.pfhudongtui.plugin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.pfhudongtui.plugin.entity.AgentBrand;
import com.yoxi.pfhudongtui.plugin.service.AgentBrandService;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;
import com.yoxi.pfhudongtui.user.service.FUserService;
import com.yoxi.pfhudongtui.user.service.PlatformUserService;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;
import com.yoxi.pfhudongtui.vo.plugin.AgentBrandVO;

/**
 * @Title: Controller
 * @Description: AgentBrand
 * @author jwhat generate
 * @date 2015-04-23 16:35:10
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/agentBrandController")
public class AgentBrandController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AgentBrandController.class);

	@Autowired
	private AgentBrandService agentBrandService;
	@Autowired
	private FUserService fUserService;
	@Autowired
	private PlatformUserService platformUserService;

	/**
	 * AgentBrand列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentBrand")
	public ModelAndView agentBrand(HttpServletRequest request) {
		return new ModelAndView("plugin/agentbrand/agentBrandList");
	}

	/**
	 * 跳转所有用户列表
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "allUserList")
	public String allUserList(HttpServletRequest req) {
		return "plugin/agentbrand/allAgentUserList";
	}

	/**
	 * 添加平台brand
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAddAgentBrand")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAddBrand(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String userIds[] = request.getParameter("userIds").split(",");
		for (String userId : userIds) {
			if ("".equals(userId))
				continue;
			// PlatformUser userVO = platformUserService.get(PlatformUser.class,
			// Integer.parseInt(userId));
			AgentBrand agentBrand = new AgentBrand();
			PlatformUser platformUser = new PlatformUser();
			platformUser.setUserId(Integer.parseInt(userId));
			agentBrand.setPlatformUser(platformUser);
			agentBrand.setSeq(1);
			agentBrand.setHideState("0");
			agentBrand.setAgentId(getUser().getId());
			agentBrand.setCreateTime(new Date());
			agentBrand.setUpdateTime(new Date());
			agentBrandService.save(agentBrand);
		}
		message = "添加成功";
		this.isNew = true;

		return j;
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "agentBrandGrid")
	public void agentBrandGrid(AgentBrand agentBrand, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(AgentBrand.class, dataGrid);
		cq.eq("agentId", getUser().getId());
		// 查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentBrand);
		this.agentBrandService.getDataGridReturn(cq, true);
		List<AgentBrand> list = dataGrid.getReaults();
		List<AgentBrandVO> brandList = new ArrayList<AgentBrandVO>();
		for (AgentBrand l : list) {
			AgentBrandVO ll = new AgentBrandVO();
			if (l.getLogo() != null && !"".equals(l.getLogo())) {
				ll.setLogo(server + "/" + l.getLogo());
			} else {
				if (l.getPlatformUser().getHeadimgUrl() != null && l.getPlatformUser().getHeadimgUrl().startsWith("group1")) {
					ll.setLogo(server + "/" + l.getPlatformUser().getHeadimgUrl());
				} else {
					ll.setLogo(l.getPlatformUser().getHeadimgUrl());
				}
			}
			ll.setCreateTime(l.getCreateTime());
			ll.setHideState(l.getHideState());
			ll.setId(l.getId());
			ll.setSeq(l.getSeq());
			ll.setUserId(l.getPlatformUser().getUserId());
			ll.setUpdateTime(l.getUpdateTime());
			ll.setNickName(l.getPlatformUser().getNickName());
			brandList.add(ll);
		}
		dataGrid.setReaults(brandList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除AgentBrand
	 * 
	 * @return
	 */
	@RequestMapping(params = "delAgentBrand")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAgentBrand(AgentBrand agentBrand, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		agentBrand = systemService.getEntity(AgentBrand.class, agentBrand.getId());
		message = "删除成功";
		agentBrandService.delete(agentBrand);
		// systemService.addLog(message, Globals.Log_Type_DEL,
		// Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加AgentBrand
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAgentBrand")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAgentBrand(AgentBrand agentBrand, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(agentBrand.getId())) {
			message = "更新成功";
			this.isNew = false;
			AgentBrand t = agentBrandService.get(AgentBrand.class, agentBrand.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(agentBrand, t);
				agentBrandService.saveOrUpdate(t);
				// systemService.addLog(message, Globals.Log_Type_UPDATE,
				// Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			agentBrand.setCreateTime(new Date());
			agentBrand.setUpdateTime(new Date());
			agentBrand.setAgentId(getUser().getId());
			agentBrandService.save(agentBrand);
			// systemService.addLog(message, Globals.Log_Type_INSERT,
			// Globals.Log_Leavel_INFO);
		}

		return j;
	}

	/**
	 * 选择用户列表跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "userList")
	public String roles(HttpServletRequest req) {
		return "plugin/agentbrand/userList";
	}

	/**
	 * 选择用户列表
	 * 
	 * @param pluginAct
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "userGrid")
	public void userGrid(PlatformUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PlatformUser.class, dataGrid);
		List<Integer> userId = systemService.findListbySql("select userId from t_agent_brand where agentId=" + getUser().getId());
		// cq.createAlias("agentClassic", "ts");
		// cq.add(Restrictions.isEmpty("agentClassic"));
		if (userId.size() > 0) {
			cq.add(Restrictions.not(Restrictions.in("userId", userId)));
		}
		// cq.createAlias("user", "ts");
		cq.eq("agentInfo.id", getUser().getId());
		cq.eq("state", "0");
		// 查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
		this.fUserService.getDataGridReturn(cq, true);
		// List<PlatformUser> userList = dataGrid.getReaults();
		// dataGrid.setReaults(userList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * AgentBrand列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateAgentBrand")
	public ModelAndView addorupdateAgentBrand(AgentBrand agentBrand, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(agentBrand.getId())) {
			agentBrand = agentBrandService.getEntity(AgentBrand.class, agentBrand.getId());
			AgentBrandVO ll = new AgentBrandVO();
			if (agentBrand.getLogo() != null) {
				ll.setLogo(agentBrand.getLogo());
			} else {
				if (agentBrand.getPlatformUser().getHeadimgUrl() != null) {
					ll.setLogo(agentBrand.getPlatformUser().getHeadimgUrl());
				}
			}

			ll.setCreateTime(agentBrand.getCreateTime());
			ll.setHideState(agentBrand.getHideState());
			ll.setId(agentBrand.getId());
			ll.setSeq(agentBrand.getSeq());
			ll.setUserId(agentBrand.getPlatformUser().getUserId());
			ll.setUpdateTime(agentBrand.getUpdateTime());
			ll.setNickName(agentBrand.getPlatformUser().getNickName());
			req.setAttribute("agentBrandPage", ll);
		}
		return new ModelAndView("plugin/agentbrand/agentBrandPage").addObject("server", server);
	}
}
