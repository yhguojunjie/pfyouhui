package com.yoxi.jgframework.demo.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.yoxi.jgframework.demo.entity.test.JeecgOrderCustomEntity;
import com.yoxi.jgframework.demo.entity.test.JeecgOrderMainEntity;
import com.yoxi.jgframework.demo.entity.test.JeecgOrderProductEntity;
import com.yoxi.jgframework.demo.page.JeecgOrderMainPage;
import com.yoxi.jgframework.demo.service.test.JeecgOrderMainServiceI;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.StringUtil;

/**   
 * @Title: Controller
 * @Description: 订单信息
 * @author zhangdaihao
 * @date 2013-03-19 22:01:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jeecgOrderMainController")
public class JeecgOrderMainController extends BaseController {
	
	@Autowired
	private JeecgOrderMainServiceI jeecgOrderMainService;
	

	/**
	 * 订单信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgOrderMain")
	public ModelAndView jeecgOrderMain(HttpServletRequest request) {
		return new ModelAndView("demo/test/jeecgOrderMainList");
		//return new ModelAndView("com.yoxi.jgframework/demo/test/jeecgOrderMainList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JeecgOrderMainEntity.class, dataGrid);
		this.jeecgOrderMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订单信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson del(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jeecgOrderMain = systemService.getEntity(JeecgOrderMainEntity.class, jeecgOrderMain.getId());
		message = "删除成功";
		jeecgOrderMainService.delMain(jeecgOrderMain);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单及明细信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson save(JeecgOrderMainEntity jeecgOrderMain ,JeecgOrderMainPage jeecgOrderMainPage,	
			HttpServletRequest request) {
		List<JeecgOrderProductEntity> jeecgOrderProducList =  jeecgOrderMainPage.getJeecgOrderProductList();
		List<JeecgOrderCustomEntity>  jeecgOrderCustomList = jeecgOrderMainPage.getJeecgOrderCustomList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgOrderMain.getId())) {
			message = "更新成功";
			jeecgOrderMainService.updateMain(jeecgOrderMain, jeecgOrderProducList, jeecgOrderCustomList);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			jeecgOrderMainService.addMain(jeecgOrderMain, jeecgOrderProducList, jeecgOrderCustomList);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 订单信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgOrderMain.getId())) {
			jeecgOrderMain = jeecgOrderMainService.getEntity(JeecgOrderMainEntity.class, jeecgOrderMain.getId());
			req.setAttribute("jeecgOrderMainPage", jeecgOrderMain);
		}
		//return new ModelAndView("com.yoxi.jgframework/demo/test/jeecgOrderMain");
		return new ModelAndView("demo/test/jeecgOrderMain");
	}
	/**
	 * 加载产品列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgOrderProductList")
	public ModelAndView getOrderProductList(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgOrderMain.getGoOrderCode())) {
			List<JeecgOrderProductEntity> jeecgOrderProductEntityList =  jeecgOrderMainService.findByProperty(JeecgOrderProductEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode());
			req.setAttribute("jeecgOrderProductList", jeecgOrderProductEntityList);
		}
		return new ModelAndView("demo/test/jeecgOrderProductList");
		//return new ModelAndView("com.yoxi.jgframework/demo/test/jeecgOrderProductList");
	}
	
	/**
	 * 加载客户列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgOrderCustomList")
	public ModelAndView getOrderCustomList(JeecgOrderMainEntity jeecgOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgOrderMain.getGoOrderCode())) {
			List<JeecgOrderCustomEntity> jeecgJeecgOrderCustomEntityList =  jeecgOrderMainService.findByProperty(JeecgOrderCustomEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode());
			req.setAttribute("jeecgOrderCustomList", jeecgJeecgOrderCustomEntityList);
		}
		return new ModelAndView("demo/test/jeecgOrderCustomList");
		//return new ModelAndView("com.yoxi.jgframework/demo/test/jeecgOrderCustomList");
	}
}
