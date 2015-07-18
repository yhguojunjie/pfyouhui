package com.yoxi.pfhudongtui.plugin.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.Plugin;
import com.yoxi.pfhudongtui.plugin.service.PluginPicService;
import com.yoxi.pfhudongtui.plugin.service.PluginService;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: Plugin
 * @author jwhat generate
 * @date 2015-03-18 14:59:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/templateController")
public class templateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(templateController.class);

	@Autowired
	private PluginService pluginService;
	@Autowired
	private PluginPicService pluginPicService;

	/**
	 * Plugin列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "plugin")
	public ModelAndView plugin(HttpServletRequest request) {
		return new ModelAndView("plugin/pluginList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "pluginGrid")
	public void pluginGrid(Plugin plugin,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(Plugin.class, dataGrid);
		cq.addOrder("id", SortDirection.desc);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, plugin);
		this.pluginService.getDataGridReturn(cq, true);
		List<Plugin> list = dataGrid.getReaults();
		List<Plugin> pluginList = new ArrayList<Plugin>();
		for(Plugin l :list){
			l.setIcon(server+"/"+l.getIcon());
			pluginList.add(l);
		}
		dataGrid.setReaults(pluginList);
		TagUtil.datagrid(response, dataGrid);
	}
	/***
	 * 跳转到开发者界面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "developers")
	public ModelAndView developers(HttpServletRequest request) {
		return new ModelAndView("plugin/developersList");
	}
	/**
	 * 开发者
	 * @param plugin
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "developersGrid")
	public void developersGrid(PlatformUser platformUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PlatformUser.class, dataGrid);
		cq.eq("role", "2");
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, platformUser);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 删除Plugin
	 * 
	 * @return
	 */
	@RequestMapping(params = "delPlugin")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delPlugin(Plugin plugin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		plugin = systemService.getEntity(Plugin.class, plugin.getId());
		message = "删除成功";
		pluginService.delete(plugin);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}
	/**
	 * 上线或下线
	 * @param plugin
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "onlineOrOffline")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson onlineOrOffline(Plugin plugin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		plugin = systemService.getEntity(Plugin.class, plugin.getId());
		if("0".equals(plugin.getStatus())){
			plugin.setStatus("1");
			plugin.setPublisherId(getUser().getId());
			plugin.setPublishTime(new Date());
			pluginService.saveOrUpdate(plugin);
			pluginService.online(plugin);
			message = "上架成功";
		}else{
			plugin.setStatus("0");
			pluginService.saveOrUpdate(plugin);
			pluginService.offline(plugin);
			message = "下架成功";
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Plugin
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "savePlugin")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePlugin(Plugin plugin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(plugin.getId())) {
			message = "更新成功";
			this.isNew = false;
			Plugin t = pluginService.get(Plugin.class, plugin.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(plugin, t);
				plugin.setUpdateTime(new Date());
				pluginService.saveOrUpdate(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			plugin.setUploadTime(new Date());
			plugin.setBuyNum(0);
			plugin.setTryoutNum(0);
			plugin.setType("1");
			plugin.setAuditState("0");
			pluginService.save(plugin);
		}
		
		return j;
	}

	/**
	 * Plugin列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdatePlugin")
	public ModelAndView addorupdatePlugin(Plugin plugin, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(plugin.getId())) {
			plugin = pluginService.getEntity(Plugin.class, plugin.getId());
			double price = plugin.getPrice();
			req.setAttribute("price", (int)price);
			req.setAttribute("pluginPage", plugin);
			List<String> list = systemService.findListbySql("select nickName from t_user where userId="+plugin.getUserId());
			if(list.size()!=0){
				req.setAttribute("nickName", list.get(0));
			}else{
				req.setAttribute("nickName", "");
			}
		
			return new ModelAndView("plugin/pluginPage").addObject("server", server);
		}else{
			return new ModelAndView("plugin/pluginAdd").addObject("server", server);
		}
		
	}
	
	@RequestMapping(params = "updatePlugin")
	public ModelAndView updatePlugin(Plugin plugin, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(plugin.getId())) {
			plugin = pluginService.getEntity(Plugin.class, plugin.getId());
			req.setAttribute("pluginPage", plugin);		
		}
		return new ModelAndView("plugin/pluginUpdate").addObject("server", server);
	}
	
	/**
	 * 上传插件图片页面
	 * @param plugin
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "uploadPluginImg")
	public ModelAndView uploadPluginImg(Plugin plugin, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(plugin.getId())) {
			plugin = pluginService.getEntity(Plugin.class, plugin.getId());
			req.setAttribute("pluginPage", plugin);		
		}
		return new ModelAndView("plugin/pluginImg").addObject("server", server);
	}
	/**
	 * 保存插件图片
	 * @param plugin
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "savePluginImg")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePluginImg(Plugin plugin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "添加成功";
		this.isNew = true;
		Plugin t = pluginService.get(Plugin.class, plugin.getId());
		pluginService.savePluginImg( t , request);
		return j;
	}
}
