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
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.ActClassic;
import com.yoxi.pfhudongtui.plugin.entity.ActClassicEntity;
import com.yoxi.pfhudongtui.plugin.entity.PluginPrev;
import com.yoxi.pfhudongtui.plugin.entity.PluginPrevEntity;
import com.yoxi.pfhudongtui.plugin.service.PluginPrevService;
import com.yoxi.pfhudongtui.utils.DateUtils;

/**   
 * @Title: Controller
 * @Description: PluginPrev
 * @author jwhat generate
 * @date 2015-04-10 11:54:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/pluginPrevController")
public class PluginPrevController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PluginPrevController.class);

	@Autowired
	private PluginPrevService pluginPrevService;

	/**
	 * PluginPrev列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "pluginPrev")
	public ModelAndView pluginPrev(HttpServletRequest request) {
		return new ModelAndView("plugin/pluginprev/pluginPrevList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "pluginPrevGrid")
	public void pluginPrevGrid(PluginPrev pluginPrev,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PluginPrev.class, dataGrid);
		cq.addOrder("shelTime", SortDirection.desc);
		Date startTime =DateUtils.getDateByYMD(DateUtils.getYmd(new Date()));
		//moreDay
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pluginPrev);
		this.pluginPrevService.getDataGridReturn(cq, true);
		List<PluginPrev> list = dataGrid.getReaults();
		List<PluginPrevEntity> prewList = new ArrayList<PluginPrevEntity>();
		for(PluginPrev l :list){
			PluginPrevEntity entity = new PluginPrevEntity();
			entity.setId(l.getId());
			entity.setCreateTime(l.getCreateTime());
			entity.setDescription(l.getDescription());
			entity.setName(l.getName());
			entity.setSeq(l.getSeq());
			entity.setShelTime(l.getShelTime());
			entity.setUpdateTime(l.getUpdateTime());
			if(DateUtils.moreDay(startTime,l.getShelTime())>0){
				entity.setStatus(1);
			}else{
				entity.setStatus(0);
			}
			prewList.add(entity);
		}
		dataGrid.setReaults(prewList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除PluginPrev
	 * 
	 * @return
	 */
	@RequestMapping(params = "delPluginPrev")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delPluginPrev(PluginPrev pluginPrev, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		pluginPrev = systemService.getEntity(PluginPrev.class, pluginPrev.getId());
		message = "删除成功";
		pluginPrevService.delete(pluginPrev);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加PluginPrev
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "savePluginPrev")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePluginPrev(PluginPrev pluginPrev, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(pluginPrev.getId())) {
			message = "更新成功";
			this.isNew = false;
			PluginPrev t = pluginPrevService.get(PluginPrev.class, pluginPrev.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(pluginPrev, t);
				t.setUpdateTime(new Date());
				pluginPrevService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			pluginPrev.setCreateTime(new Date());
			pluginPrev.setUpdateTime(new Date());
			pluginPrevService.save(pluginPrev);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * PluginPrev列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdatePluginPrev")
	public ModelAndView addorupdatePluginPrev(PluginPrev pluginPrev, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pluginPrev.getId())) {
			pluginPrev = pluginPrevService.getEntity(PluginPrev.class, pluginPrev.getId());
			req.setAttribute("pluginPrevPage", pluginPrev);
		}
		return new ModelAndView("plugin/pluginprev/pluginPrevPage");
	}
}
