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
import com.yoxi.pfhudongtui.plugin.entity.Brand;
import com.yoxi.pfhudongtui.plugin.service.AgentBrandService;
import com.yoxi.pfhudongtui.plugin.service.AgentContSwitchService;
import com.yoxi.pfhudongtui.plugin.service.BrandService;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;
import com.yoxi.pfhudongtui.user.service.FUserService;
import com.yoxi.pfhudongtui.user.service.PlatformUserService;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;
import com.yoxi.pfhudongtui.vo.plugin.BrandVO;

/**
 * @Title: Controller
 * @Description: Brand
 * @author jwhat generate
 * @date 2015-04-23 16:29:06
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/brandController")
public class BrandController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BrandController.class);

	@Autowired
	private BrandService brandService;
	@Autowired
	private FUserService fUserService;
	@Autowired
	private AgentContSwitchService agentContSwitchService;
	@Autowired
	private AgentBrandService agentBrandService;
	@Autowired
	private PlatformUserService platformUserService;

	/**
	 * Brand列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "brand")
	public ModelAndView brand(HttpServletRequest request) {
		return new ModelAndView("plugin/brand/brandList");
	}

	/**
	 * 跳转所有用户列表
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "allUserList")
	public String allUserList(HttpServletRequest req) {
		return "plugin/brand/allUserList";
	}

	/**
	 * 添加平台brand
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAddBrand")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAddBrand(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String userIds[] = request.getParameter("userIds").split(",");
		for (String userId : userIds) {
			if ("".equals(userId))
				continue;
			PlatformUser userVO = platformUserService.get(PlatformUser.class, Integer.parseInt(userId));
			Brand brand = new Brand();
			brand.setUserId(Integer.parseInt(userId));
			brand.setSeq(1);
			brand.setLogo(userVO.getHeadimgUrl());
			brand.setHideState("0");
			brand.setCreateTime(new Date());
			brand.setUpdateTime(new Date());
			brandService.save(brand);
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

	@RequestMapping(params = "brandGrid")
	public void brandGrid(Brand brand, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(Brand.class, dataGrid);
		// 查询条件组装器
		// 查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, brand);
		this.brandService.getDataGridReturn(cq, true);
		List<Brand> list = dataGrid.getReaults();
		List<BrandVO> brandList = new ArrayList<BrandVO>();
		for (Brand l : list) {
			BrandVO ll = new BrandVO();
			ll.setLogo(server + "/" + l.getLogo());
			ll.setCreateTime(l.getCreateTime());
			ll.setHideState(l.getHideState());
			ll.setId(l.getId());
			ll.setSeq(l.getSeq());
			ll.setUserId(l.getUserId());
			ll.setUpdateTime(l.getUpdateTime());
			ll.setNickName(l.getPlatformUser().getNickName());
			brandList.add(ll);
		}

		dataGrid.setReaults(brandList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Brand
	 * 
	 * @return
	 */
	@RequestMapping(params = "delBrand")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delBrand(Brand brand, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		brand = systemService.getEntity(Brand.class, brand.getId());
		message = "删除成功";
		brandService.delete(brand);
		// systemService.addLog(message, Globals.Log_Type_DEL,
		// Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加Brand
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveBrand")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveBrand(Brand brand, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(brand.getId())) {
			message = "更新成功";
			this.isNew = false;
			Brand t = brandService.get(Brand.class, brand.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(brand, t);
				brandService.saveOrUpdate(t);
				// systemService.addLog(message, Globals.Log_Type_UPDATE,
				// Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			brand.setCreateTime(new Date());
			brand.setUpdateTime(new Date());
			brandService.save(brand);
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
		return "plugin/brand/userList";
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
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(PlatformUser.class, dataGrid);
		// cq.createAlias("user", "ts");
		List<Integer> userId = systemService.findListbySql("select userId from t_brand");
		// cq.createAlias("agentClassic", "ts");
		// cq.add(Restrictions.isEmpty("agentClassic"));
		if (userId.size() > 0) {
			cq.add(Restrictions.not(Restrictions.in("userId", userId)));
		}
		cq.createAlias("agentInfo", "ts");
		cq.eq("ts.id", getUser().getId());
		// 查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
		this.fUserService.getDataGridReturn(cq, true);
		List<PlatformUser> list = dataGrid.getReaults();
		List<PlatformUser> userList = new ArrayList<PlatformUser>();
		for (PlatformUser l : list) {
			l.setHeadimgUrl(server + "/" + l.getHeadimgUrl());
			userList.add(l);
		}
		dataGrid.setReaults(userList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * Brand列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateBrand")
	public ModelAndView addorupdateBrand(Brand brand, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(brand.getId())) {
			brand = brandService.getEntity(Brand.class, brand.getId());
			BrandVO ll = new BrandVO();
			ll.setLogo(brand.getLogo());
			ll.setCreateTime(brand.getCreateTime());
			ll.setHideState(brand.getHideState());
			ll.setId(brand.getId());
			ll.setSeq(brand.getSeq());
			ll.setUserId(brand.getUserId());
			ll.setUpdateTime(brand.getUpdateTime());
			ll.setNickName(brand.getPlatformUser().getNickName());
			req.setAttribute("brandPage", ll);
		}
		return new ModelAndView("plugin/brand/brandPage").addObject("server", server);
	}
}
