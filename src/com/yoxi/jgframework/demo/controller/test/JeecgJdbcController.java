package com.yoxi.jgframework.demo.controller.test;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.demo.entity.test.JeecgJdbcEntity;
import com.yoxi.jgframework.demo.service.test.JeecgJdbcServiceI;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.jgframework.util.StringUtil;

/**   
 * @Title: Controller
 * @Description: 通过JDBC访问数据库
 * @author Quainty
 * @date 2013-05-20 13:18:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jeecgJdbcController")
public class JeecgJdbcController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JeecgJdbcController.class);

	@Autowired
	private JeecgJdbcServiceI jeecgJdbcService;
	

	/**
	 * 通过JDBC访问数据库列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgJdbc")
	public ModelAndView jeecgJdbc(HttpServletRequest request) {
		return new ModelAndView("demo/test/jeecgJdbcList");
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
	public void datagrid(JeecgJdbcEntity jeecgJdbc,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		// 方式1, 用底层自带的方式往对象中设值  -------------------
		/*
		this.jeecgJdbcService.getDatagrid1(jeecgJdbc, dataGrid);
		TagUtil.datagrid(response, dataGrid);
		// end of 方式1 ========================================= */ 
		
		// 方式2, 取值自己处理(代码量多一些，但执行效率应该会稍高一些)  -------------------------------
		/*
		this.jeecgJdbcService.getDatagrid2(jeecgJdbc, dataGrid);
		TagUtil.datagrid(response, dataGrid);
		// end of 方式2 ========================================= */ 
		
		// 方式3, 取值进一步自己处理(直接转换成easyUI的datagrid需要的东西，执行效率最高，最自由)  -------------------------------
		//*
		JSONObject jObject = this.jeecgJdbcService.getDatagrid3(jeecgJdbc, dataGrid);
		responseDatagrid(response, jObject);
		// end of 方式3 ========================================= */
	}

	/**
	 * 删除通过JDBC访问数据库
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		String sql = "delete from jeecg_demo where id='" + jeecgJdbc.getId() + "'";
		jeecgJdbcService.executeSql(sql);

		message = "删除成功";
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通过JDBC访问数据库
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(JeecgJdbcEntity jeecgJdbc, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgJdbc.getId())) {
			message = "更新成功";
			JeecgJdbcEntity t = jeecgJdbcService.get(JeecgJdbcEntity.class, jeecgJdbc.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(jeecgJdbc, t);
				jeecgJdbcService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			jeecgJdbcService.save(jeecgJdbc);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 通过JDBC访问数据库列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JeecgJdbcEntity jeecgJdbc, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgJdbc.getId())) {
			jeecgJdbc = jeecgJdbcService.getEntity(JeecgJdbcEntity.class, jeecgJdbc.getId());
			req.setAttribute("jeecgJdbcPage", jeecgJdbc);
		}
		return new ModelAndView("demo/test/jeecgJdbc");
	}
	
	
	// -----------------------------------------------------------------------------------
	// 以下各函数可以提成共用部件 (Add by Quainty)
	// -----------------------------------------------------------------------------------
	public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw=response.getWriter();
			pw.write(jObject.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
