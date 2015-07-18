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
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.ActClassic;
import com.yoxi.pfhudongtui.plugin.entity.PluginAct;
import com.yoxi.pfhudongtui.plugin.entity.PluginActEntity;
import com.yoxi.pfhudongtui.plugin.service.ActClassicService;
import com.yoxi.pfhudongtui.plugin.service.PluginActService;
import com.yoxi.pfhudongtui.utils.DateUtils;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: PluginAct
 * @author jwhat generate
 * @date 2015-03-25 16:50:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/pluginActController")
public class PluginActController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PluginActController.class);

	@Autowired
	private PluginActService pluginActService;
	@Autowired
	private ActClassicService actClassicService;

	/**
	 * PluginAct列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "pluginAct")
	public ModelAndView pluginAct(HttpServletRequest request) {
		return new ModelAndView("plugin/pluginact/pluginActList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "pluginActGrid")
	public void pluginActGrid(PluginAct pluginAct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(PluginAct.class, dataGrid);
		cq.addOrder("id", SortDirection.desc);
		cq.eq("agentInfo.id", getUser().getId());
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pluginAct);
		this.pluginActService.getDataGridReturn(cq, true);
		List<PluginAct> list = dataGrid.getReaults();
		List<PluginActEntity> actList = new ArrayList<PluginActEntity>();
		for(PluginAct l :list){
			PluginActEntity act = new PluginActEntity();
			act.setId(l.getId());
			act.setTitle(l.getTitle());
			if(l.getIcon() == null || "".equals(l.getIcon().trim()) ){
				act.setIcon(server + "/"+l.getPlugin().getIcon());
			}else{
				act.setIcon(server + "/"+l.getIcon());
			}
			act.setJoinNum(l.getJoinNum());
			act.setBrowseNum(l.getBrowseNum());
			String startTime="无";
			String endTime ="无";
			if(l.getStartTime() != null){
				startTime =	DateUtils.getYmdhms(l.getStartTime());
			}
			if(l.getEndTime()!=null){
				endTime = DateUtils.getYmdhms(l.getEndTime());
			}
			String actTime = startTime+"<br>至<br>"+endTime;
			act.setActTime(actTime);
			act.setNickName(l.getPlatformUser().getNickName());
			//act.setAgentName(l.getAgentInfo().getCompanyName());
			act.setActOpen(l.getActOpen());
			act.setActCase(l.getActClassic()==null?0:1);
			actList.add(act);
		}
		dataGrid.setReaults(actList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除PluginAct
	 * 
	 * @return
	 */
	@RequestMapping(params = "delPluginAct")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delPluginAct(PluginAct pluginAct, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		pluginAct = systemService.getEntity(PluginAct.class, pluginAct.getId());
		message = "删除成功";
		pluginActService.delete(pluginAct);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加PluginAct
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "savePluginAct")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePluginAct(PluginAct pluginAct, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(pluginAct.getId())) {
			message = "更新成功";
			this.isNew = false;
			PluginAct t = pluginActService.get(PluginAct.class, pluginAct.getId());
			String actCase = request.getParameter("actCase");
			if("0".equals(actCase)){//否
				ActClassic act = actClassicService.findUniqueByProperty(ActClassic.class, "pluginAct.id", t.getId());
				actClassicService.delete(act);;
			}
			if("1".equals(actCase)){//是
				ActClassic act = new ActClassic();
				act.setPluginAct(t);
				act.setSeq(1);
				act.setCreateTime(new Date());
				act.setPluginId(t.getPlugin().getId());
				actClassicService.save(act);
			}
			try {
				MyBeanUtils.copyBeanNotNull2Bean(pluginAct, t);
				pluginActService.saveOrUpdate(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			pluginActService.save(pluginAct);
		}
		
		return j;
	}

	/**
	 * PluginAct列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdatePluginAct")
	public ModelAndView addorupdatePluginAct(PluginAct pluginAct, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pluginAct.getId())) {
			pluginAct = pluginActService.getEntity(PluginAct.class, pluginAct.getId());
			req.setAttribute("pluginActPage", pluginAct);
			req.setAttribute("actClassicNum", pluginAct.getActClassic()==null?0:1);
		}
		return new ModelAndView("plugin/pluginact/pluginActPage");
	}
	
	/**
	 * 选择活动列表跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "pluginList")
	public String roles(HttpServletRequest req) {
		return "plugin/actclassic/pluginList";
	}
	
	/**
	 * 选择经典活动列表
	 * @param pluginAct
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "pluginActClassGrid")
	public void pluginActClassGrid(PluginAct pluginAct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PluginAct.class, dataGrid);
		cq.add(Restrictions.isEmpty("actClassic"));
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pluginAct);
		this.pluginActService.getDataGridReturn(cq, true);
		List<PluginAct> list = dataGrid.getReaults();
		List<PluginActEntity> actList = new ArrayList<PluginActEntity>();
		for(PluginAct l :list){
				PluginActEntity act = new PluginActEntity();
				act.setId(l.getId());
				act.setTitle(l.getTitle());
				act.setPluginName(l.getPlugin().getName());
				actList.add(act);
		}
		dataGrid.setReaults(actList);
		TagUtil.datagrid(response, dataGrid);
	}

}
