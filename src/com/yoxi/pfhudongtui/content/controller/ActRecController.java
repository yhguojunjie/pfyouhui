package com.yoxi.pfhudongtui.content.controller;
import java.util.ArrayList;
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
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.content.entity.ActRec;
import com.yoxi.pfhudongtui.content.service.ActRecService;
import com.yoxi.pfhudongtui.plugin.entity.PluginAct;
import com.yoxi.pfhudongtui.plugin.entity.PluginActEntity;

/**   
 * @Title: Controller
 * @Description: ActRec
 * @author jwhat generate
 * @date 2015-04-23 15:40:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/actRecController")
public class ActRecController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActRecController.class);

	@Autowired
	private ActRecService actRecService;

	/**
	 * ActRec列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "actRec")
	public ModelAndView actRec(HttpServletRequest request) {
		return new ModelAndView("content/actrec/actRecList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "actRecGrid")
	public void actRecGrid(ActRec actRec,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ActRec.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actRec);
		this.actRecService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除ActRec
	 * 
	 * @return
	 */
	@RequestMapping(params = "delActRec")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delActRec(ActRec actRec, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		actRec = systemService.getEntity(ActRec.class, actRec.getId());
		message = "删除成功";
		actRecService.delete(actRec);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加ActRec
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveActRec")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveActRec(ActRec actRec, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(actRec.getId())) {
			message = "更新成功";
			this.isNew = false;
			ActRec t = actRecService.get(ActRec.class, actRec.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(actRec, t);
				actRecService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			actRecService.save(actRec);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * ActRec列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateActRec")
	public ModelAndView addorupdateActRec(ActRec actRec, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(actRec.getId())) {
			actRec = actRecService.getEntity(ActRec.class, actRec.getId());
			req.setAttribute("actRecPage", actRec);
		}
		return new ModelAndView("content/actrec/actRecPage");
	}
	
	/**
	 * 选择活动列表跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "pluginActRecList")
	public String pluginActList(HttpServletRequest req) {
		return "content/actrec/pluginActRecList";
	}
	
	/**
	 * 选择经典活动列表
	 * @param pluginAct
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "pluginActListGrid")
	public void pluginActListGrid(PluginAct pluginAct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PluginAct.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pluginAct);
		this.systemService.getDataGridReturn(cq, true);
		List<PluginAct> list = dataGrid.getReaults();
		List<PluginActEntity> actList = new ArrayList<PluginActEntity>();
		for(PluginAct l :list){
			if(l.getActClassic() == null){
				PluginActEntity act = new PluginActEntity();
				act.setId(l.getId());
				act.setTitle(l.getTitle());
				act.setPluginName(l.getPlugin().getName());
				actList.add(act);
			}
		}
		dataGrid.setReaults(actList);
		TagUtil.datagrid(response, dataGrid);
	}
}
