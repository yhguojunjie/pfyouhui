package com.yoxi.pfhudongtui.plugin.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.ActClassic;
import com.yoxi.pfhudongtui.plugin.entity.ActClassicEntity;
import com.yoxi.pfhudongtui.plugin.entity.ActClassicPic;
import com.yoxi.pfhudongtui.plugin.entity.PluginAct;
import com.yoxi.pfhudongtui.plugin.service.ActClassicPicService;
import com.yoxi.pfhudongtui.plugin.service.ActClassicService;
import com.yoxi.pfhudongtui.plugin.service.PluginActService;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;
import com.yoxi.pfhudongtui.utils.FastDFSUtils;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: ActClassic
 * @author jwhat generate
 * @date 2015-03-25 20:27:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/actClassicController")
public class ActClassicController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActClassicController.class);

	@Autowired
	private ActClassicService actClassicService;

	@Autowired
	private PluginActService pluginActService;
	
	@Autowired
	private ActClassicPicService actClassicPicService;
	/**
	 * ActClassic列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "actClassic")
	public ModelAndView actClassic(HttpServletRequest request) {
		return new ModelAndView("plugin/actclassic/actClassicList");
	}
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "actClassicGrid")
	public void actClassicGrid(ActClassic actClassic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(ActClassic.class, dataGrid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", SortDirection.asc);
		map.put("updateTime", SortDirection.desc);
		cq.setOrder(map);
		String actTitle = oConvertUtils.getString(request.getParameter("actTitle"));
		if(StringUtils.isNotBlank(actTitle)){
			cq.createAlias("pluginAct", "ts");
			cq.like("ts.title", actTitle);
			//cq.add(Restrictions.like("ts.title",actTitle));
		}
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actClassic);
		this.actClassicService.getDataGridReturn(cq, true);
		List<ActClassic> list = dataGrid.getReaults();
		List<ActClassicEntity> actList = new ArrayList<ActClassicEntity>();
		for(ActClassic l :list){
			ActClassicEntity act = new ActClassicEntity();
			act.setId(l.getId());
			act.setSeq(l.getSeq());
			if(l.getPluginAct()==null){
				//act.setActIcon(server + "/");
				act.setActTitle(l.getTitle());
			}else{
				//act.setActIcon(server + "/"+l.getPluginAct().getIcon());
				act.setActTitle(l.getPluginAct().getTitle());
			}
			act.setBannerLogo(server + "/"+ l.getBannerLogo());
			act.setBannerName(l.getBannerName());
			act.setType(l.getType());
			actList.add(act);
		}
		dataGrid.setReaults(actList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除ActClassic
	 * 
	 * @return
	 */
	@RequestMapping(params = "delActClassic")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delActClassic(ActClassic actClassic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		actClassic = systemService.getEntity(ActClassic.class, actClassic.getId());
		message = "删除成功";
		List<ActClassicPic> list = actClassic.getActClassicPic();
		for(ActClassicPic a : list){
			FastDFSUtils.deleteFile(a.getUrl());
		}
		actClassicService.delete(actClassic);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加ActClassic
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveActClassic")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveActClassic(ActClassic actClassic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(actClassic.getId())) {
			message = "更新成功";
			this.isNew = false;
			ActClassic t = actClassicService.get(ActClassic.class, actClassic.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(actClassic, t);
				if("2".equals(actClassic.getType())){
					t.setUpdateTime(new Date());
					//PlatformUser formuser = new PlatformUser();
					//formuser.setUserId(Integer.parseInt(request.getParameter("userId")));
					//t.setPlatformUser(formuser);
					actClassicService.saveOrUpdate(t);
					actClassicPicService.saveClassicPic(actClassic,request);
				}else{
					t.setUpdateTime(new Date());
					actClassicService.saveOrUpdate(t);
					actClassicPicService.saveClassicPic(actClassic,request);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			if("2".equals(actClassic.getType())){
				actClassic.setCreateTime(new Date());
				actClassic.setUpdateTime(new Date());
				actClassic.setPluginAct(null);
				actClassic.setHideState("0");
				actClassic.setAuditState("1");
				//PlatformUser formuser = new PlatformUser();
				//formuser.setUserId(Integer.parseInt(request.getParameter("userId")));
				//actClassic.setPlatformUser(formuser);
			}else{
				actClassic.setCreateTime(new Date());
				actClassic.setUpdateTime(new Date());
				PluginAct act = pluginActService.getEntity( PluginAct.class, Integer.parseInt(request.getParameter("actId")));
				actClassic.setPluginAct(act);
				actClassic.setTitle(null);
				actClassic.setPluginId(act.getPlugin().getId());
				actClassic.setPlatformUser(act.getPlatformUser());
				actClassic.setHideState("0");
				actClassic.setAuditState("1");
			}
			actClassicService.save(actClassic);
			actClassicPicService.saveClassicPic(actClassic,request);
		}
		return j;
	}

	/**
	 * ActClassic列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateActClassic")
	public ModelAndView addorupdateActClassic(ActClassic actClassic, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(actClassic.getId())) {
			actClassic = actClassicService.getEntity(ActClassic.class, actClassic.getId());
			req.setAttribute("actClassicPage", actClassic);
			return new ModelAndView("plugin/actclassic/actClassicPage").addObject("server", server);
		}else{
			return new ModelAndView("plugin/actclassic/actClassicAdd").addObject("server",server);
		}
	}
	/**
	 * 手动设置活动案例
	 * 
	 * @return
	 */
	@RequestMapping(params = "classicManualAdd")
	public ModelAndView classicManualAdd(ActClassic actClassic, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		List<String> pluginNameList = systemService.findListbySql("select concat(id,'@',name) as pluginName from t_plugin where status='1'");
		req.setAttribute("pluginNameList", pluginNameList);
		if (StringUtil.isNotEmpty(actClassic.getId())) {
			actClassic = actClassicService.getEntity(ActClassic.class, actClassic.getId());
			req.setAttribute("actClassicPage", actClassic);
			return new ModelAndView("plugin/actclassic/actClassicPage").addObject("server", server);
		}else{
			return new ModelAndView("plugin/actclassic/classicManualAdd").addObject("server",server);
		}
	}
	/**
	 * 删除PluginPic
	 * 
	 * @return
	 */
	@RequestMapping(params = "delActClassicPic")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delActClassicPic(ActClassicPic actClassicPic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String sql = "delete from t_act_classic_pic where id =?";
		actClassicPicService.executeSql(sql, actClassicPic.getId());	
		String url = request.getParameter("url");
		FastDFSUtils.deleteFile(url);
		j.setMsg("删除成功");
		j.setSuccess(true);
		j.setMsg(message);
		return j;
	}
	/**
	 * 选择活动列表跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "userActList")
	public String userActList(HttpServletRequest req) {
		return "plugin/actclassic/userActList";
	}
	/**
	 * 用户列表
	 * @param platformUser
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "userActListGrid")
	public void actClassicGrid(PlatformUser platformUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PlatformUser.class, dataGrid);
		cq.eq("role", "1");
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, platformUser);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 编辑手动设置经典案例
	 * 
	 * @return
	 */
	@RequestMapping(params = "classicManualUpdate")
	public ModelAndView classicManualUpdate(ActClassic actClassic, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		List<String> pluginNameList = systemService.findListbySql("select concat(id,'@',name) as pluginName from t_plugin where status='1'");
		req.setAttribute("pluginNameList", pluginNameList);
		if (StringUtil.isNotEmpty(actClassic.getId())) {
			actClassic = actClassicService.getEntity(ActClassic.class, actClassic.getId());
			req.setAttribute("actClassicPage", actClassic);
			return new ModelAndView("plugin/actclassic/classicManualUpdate").addObject("server", server);
		}else{
			return new ModelAndView("plugin/actclassic/classicManualAdd").addObject("server",server);
		}
	}
}
