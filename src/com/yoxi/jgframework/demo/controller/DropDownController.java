package com.yoxi.jgframework.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * @ClassName: demoController
 * @Description: TODO(演示例子处理类)
 * @author com.yoxi.jgframework
 */
@Controller
@RequestMapping("/dropdownController")
public class DropDownController extends BaseController {
	
	/**
	 *自动完成
	 */
	@RequestMapping(params = "selectTabs")
	public ModelAndView selectTabs(HttpServletRequest request) {
		return new ModelAndView("demo/AJAX/selectTabs");
	}
	
	/**
	 *自动完成
	 */
	@RequestMapping(params = "autocomplete")
	public ModelAndView autocomplete(HttpServletRequest request) {
		return new ModelAndView("demo/autocomplete/basic");
	}
	

	/**
	 *下拉联动跳转
	 */
	@RequestMapping(params = "select")
	public ModelAndView select(HttpServletRequest request) {
		// 新闻
		CriteriaQuery cq2 = new CriteriaQuery(TSFunction.class);
		cq2.eq("functionLevel",Globals.Function_Leave_ONE);
		cq2.add();
		List<TSFunction> funList = systemService.getListByCriteriaQuery(cq2, true);
		request.setAttribute("funList", funList);
		return new ModelAndView("demo/AJAX/select");
	}
	
	
	/**
	 * AJAX 示例下拉框
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getDropdown")
	@ResponseBody
	public AjaxJson getDemo(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		Integer id = oConvertUtils.getInt(StringUtil.getEncodePra(req.getParameter("id")));
		String floor = "";
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		cq.eq("TSFunction.id", id);
		cq.add();
		List<TSFunction> functions = systemService.getListByCriteriaQuery(cq, false);
		if (functions.size() > 0) {
			for (TSFunction function : functions) {
				floor += "<input type=\"checkbox\"  name=\"floornum\" id=\"floornum\" value=\"" + function.getId() + "\">" + function.getFunctionName() + "&nbsp;&nbsp;";
			}
		} else {
			floor += "没有子项目!";
		}

		j.setMsg(floor);
		return j;
	}
	/*
	*//**
	 *下拉联动跳转
	 *//*
	@RequestMapping(params = "selectCombobox")
	@ResponseBody
	public List<ComboBox> selectCombobox(HttpServletRequest request) {
		// 新闻
		CriteriaQuery cq2 = new CriteriaQuery(TSFunction.class);
		cq2.eq("functionLevel",Globals.Function_Leave_ONE);
		cq2.add();
		List<TSFunction> funList = systemService.getListByCriteriaQuery(cq2, true);
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		for (TSFunction tsFunction : funList) {
			ComboBox comboBox = new ComboBox();
			comboBox.setId(tsFunction.getId().toString());
			comboBox.setText(tsFunction.getFunctionName());
			comboBoxs.add(comboBox);
		}
		return comboBoxs;
	}*/
	
	
}
