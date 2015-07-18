package com.yoxi.jgframework.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.ComboBox;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.TreeGrid;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.system.entity.TSDemo;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * @ClassName: demoController
 * @Description: TODO(演示例子处理类)
 * @author com.yoxi.jgframework
 */
@Controller
@RequestMapping("/esayuicssController")
public class EasyuiCssController extends BaseController {
	
	
	/**
	 * 表单样式页面跳转
	 */
	@RequestMapping(params = "demoEasyuiCssTabs")
	public ModelAndView demoEasyuiCssTabs(HttpServletRequest request) {
		return new ModelAndView("demo/easyuicss/easyuicssTabs");
	}
	/**
	 * 表单样式页面跳转
	 */
	@RequestMapping(params = "demoEasyuiCss")
	public ModelAndView demoEasyuiCss(HttpServletRequest request) {
		return new ModelAndView("demo/easyuicss/easyuicss");
	}
}
