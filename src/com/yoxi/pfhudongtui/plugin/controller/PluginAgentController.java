package com.yoxi.pfhudongtui.plugin.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.yoxi.jgframework.util.oConvertUtils;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.PluginAgent;
import com.yoxi.pfhudongtui.plugin.entity.PluginEntity;
import com.yoxi.pfhudongtui.plugin.service.PluginAgentService;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: PluginAgent
 * @author jwhat generate
 * @date 2015-03-20 12:17:09
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/pluginAgentController")
public class PluginAgentController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PluginAgentController.class);

	@Autowired
	private PluginAgentService pluginAgentService;

	/**
	 * PluginAgent列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "pluginAgent")
	public ModelAndView pluginAgent(HttpServletRequest request) {
		return new ModelAndView("plugin/pluginagent/pluginAgentList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "pluginAgentGrid")
	public void pluginAgentGrid(PluginAgent pluginAgent,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(PluginAgent.class, dataGrid);
		cq.eq("agentInfo.id",  getUser().getId());
		String name = oConvertUtils.getString(request.getParameter("name"));
		if(StringUtils.isNotBlank(name)){
			cq.createAlias("plugin", "ts");
			cq.like("ts.name", name);
			//cq.add(Restrictions.like("ts.title",actTitle));
		}
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pluginAgent);
		this.pluginAgentService.getDataGridReturn(cq, true);
		List<PluginAgent> list1=dataGrid.getReaults();
		List<PluginEntity> entity = new ArrayList<PluginEntity>();
		for(PluginAgent p: list1){
			PluginEntity pe= new PluginEntity();
			pe.setId(p.getId());
			pe.setName(p.getPlugin().getName());
			pe.setOnlineState(p.getOnlineState());
			pe.setIcon(server+"/"+p.getPlugin().getIcon());
			double price = p.getPlugin().getPrice();
			pe.setPrice((int)price);
			//pe.setRealname(p.getAgentInfo().getCompanyName());
			double salePrice = p.getSalePrice();
			pe.setSalePrice((int)salePrice);
			pe.setRealname(p.getAgentInfo().getCompanyName());
			entity.add(pe);
		}
		dataGrid.setReaults(entity);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除PluginAgent
	 * 
	 * @return
	 */
	@RequestMapping(params = "delPluginAgent")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delPluginAgent(PluginAgent pluginAgent, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		pluginAgent = systemService.getEntity(PluginAgent.class, pluginAgent.getId());
		message = "删除成功";
		pluginAgentService.delete(pluginAgent);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加PluginAgent
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "savePluginAgent")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePluginAgent(PluginAgent pluginAgent, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(pluginAgent.getId())) {
			message = "更新成功";
			this.isNew = false;
			PluginAgent t = pluginAgentService.get(PluginAgent.class, pluginAgent.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(pluginAgent, t);
				t.setUpdateTime(new Date());
				pluginAgentService.saveOrUpdate(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			pluginAgentService.save(pluginAgent);
		}
		
		return j;
	}

	/**
	 * PluginAgent列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdatePluginAgent")
	public ModelAndView addorupdatePluginAgent(PluginAgent pluginAgent, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(pluginAgent.getId())) {
			pluginAgent = pluginAgentService.getEntity(PluginAgent.class, pluginAgent.getId());
			double salePrice = pluginAgent.getSalePrice();
			req.setAttribute("salePrice", (int)salePrice);
			double price = pluginAgent.getPlugin().getPrice();
			req.setAttribute("price",(int)price );
			req.setAttribute("pluginAgentPage", pluginAgent);
			
		}
		return new ModelAndView("plugin/pluginagent/pluginAgentUpdate").addObject("server", server);
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
	public AjaxJson onlineOrOffline(PluginAgent pluginAgent, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
        if("0".equals(pluginAgent.getOnlineState())){
        	pluginAgentService.updateBySqlString("update t_plugin_agent set onlineState=1 where id="+pluginAgent.getId());
        	message="下架成功";
        }
        if("1".equals(pluginAgent.getOnlineState())){
        	pluginAgentService.updateBySqlString("update t_plugin_agent set onlineState=0 where id="+pluginAgent.getId());
        	message="上架成功";
        }
		j.setMsg(message);
		return j;
	}

}
