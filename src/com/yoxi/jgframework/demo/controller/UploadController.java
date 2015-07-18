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
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.TreeGrid;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.system.entity.TSDemo;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * @ClassName: demoController
 * @Description: TODO(演示例子处理类)
 * @author com.yoxi.jgframework
 */
@Controller
@RequestMapping("/uploadController")
public class UploadController extends BaseController {
	
	/**
	 * 上传TABS跳转
	 */
	@RequestMapping(params = "uploadTabs")
	public ModelAndView uploadTabs(HttpServletRequest request) {
		return new ModelAndView("demo/upload/uploadTabs");
	}
	
	/**
	 * 上传演示
	 */
	@RequestMapping(params = "autoupload")
	public ModelAndView autoupload(HttpServletRequest request) {
		String turn=oConvertUtils.getString(request.getParameter("turn"));
		return new ModelAndView("demo/"+turn+"");
	}

	
}
