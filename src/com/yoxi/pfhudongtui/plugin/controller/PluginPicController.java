package com.yoxi.pfhudongtui.plugin.controller;
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
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.PluginPic;
import com.yoxi.pfhudongtui.plugin.service.PluginPicService;
import com.yoxi.pfhudongtui.utils.FastDFSUtils;

/**   
 * @Title: Controller
 * @Description: PluginPic
 * @author jwhat generate
 * @date 2015-03-21 20:28:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/pluginPicController")
public class PluginPicController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PluginPicController.class);

	@Autowired
	private PluginPicService pluginPicService;

	/**
	 * PluginPic列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "pluginPic")
	public ModelAndView pluginPic(HttpServletRequest request) {
		return new ModelAndView("plugin/pluginpic/pluginPicList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "pluginPicGrid")
	public void pluginPicGrid(PluginPic pluginPic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PluginPic.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pluginPic);
		this.pluginPicService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除PluginPic
	 * 
	 * @return
	 */
	@RequestMapping(params = "delPluginPic")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delPluginPic(PluginPic pluginPic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String sql = "delete from t_plugin_pic where id =?";
		systemService.executeSql(sql, pluginPic.getId());	
		String url = request.getParameter("url");
		FastDFSUtils.deleteFile(url);
		j.setMsg("删除成功");
		j.setSuccess(true);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加PluginPic
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "savePluginPic")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePluginPic(PluginPic pluginPic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(pluginPic.getId())) {
			message = "更新成功";
			this.isNew = false;
			PluginPic t = pluginPicService.get(PluginPic.class, pluginPic.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(pluginPic, t);
				pluginPicService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			pluginPicService.save(pluginPic);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * PluginPic列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdatePluginPic")
	public ModelAndView addorupdatePluginPic(PluginPic pluginPic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pluginPic.getId())) {
			pluginPic = pluginPicService.getEntity(PluginPic.class, pluginPic.getId());
			req.setAttribute("pluginPicPage", pluginPic);
		}
		return new ModelAndView("plugin/pluginpic/pluginPicPage");
	}
}
