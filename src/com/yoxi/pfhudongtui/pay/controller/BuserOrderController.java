package com.yoxi.pfhudongtui.pay.controller;
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

import com.yoxi.pfhudongtui.pay.entity.BuserOrder;
import com.yoxi.pfhudongtui.pay.service.BuserOrderService;

/**   
 * @Title: Controller
 * @Description: BuserOrder
 * @author jwhat generate
 * @date 2015-04-01 11:37:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/buserOrderController")
public class BuserOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BuserOrderController.class);

	@Autowired
	private BuserOrderService buserOrderService;

	/**
	 * BuserOrder列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "buserOrder")
	public ModelAndView buserOrder(HttpServletRequest request) {
		return new ModelAndView("pay/buserorder/buserOrderList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "buserOrderGrid")
	public void buserOrderGrid(BuserOrder buserOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BuserOrder.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, buserOrder);
		this.buserOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除BuserOrder
	 * 
	 * @return
	 */
	@RequestMapping(params = "delBuserOrder")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delBuserOrder(BuserOrder buserOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		buserOrder = systemService.getEntity(BuserOrder.class, buserOrder.getId());
		message = "删除成功";
		buserOrderService.delete(buserOrder);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加BuserOrder
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveBuserOrder")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveBuserOrder(BuserOrder buserOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(buserOrder.getId())) {
			message = "更新成功";
			this.isNew = false;
			BuserOrder t = buserOrderService.get(BuserOrder.class, buserOrder.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(buserOrder, t);
				buserOrderService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			buserOrderService.save(buserOrder);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * BuserOrder列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateBuserOrder")
	public ModelAndView addorupdateBuserOrder(BuserOrder buserOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(buserOrder.getId())) {
			buserOrder = buserOrderService.getEntity(BuserOrder.class, buserOrder.getId());
			req.setAttribute("buserOrderPage", buserOrder);
		}
		return new ModelAndView("pay/buserorder/buserOrderPage");
	}
}
