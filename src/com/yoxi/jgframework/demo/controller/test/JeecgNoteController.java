package com.yoxi.jgframework.demo.controller.test;
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
import com.yoxi.jgframework.demo.entity.test.JeecgNoteEntity;
import com.yoxi.jgframework.demo.service.test.JeecgNoteServiceI;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.StringUtil;



/**   
 * @Title: Controller
 * @Description: 公告
 * @author zhangdaihao
 * @date 2013-03-12 14:06:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jeecgNoteController")
public class JeecgNoteController extends BaseController {
	

	@Autowired
	private JeecgNoteServiceI jeecgNoteService;
	


	/**
	 * 公告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgNote")
	public ModelAndView jeecgNote(HttpServletRequest request) {
		return new ModelAndView("demo/test/jeecgNoteList");
		//return new ModelAndView("com.yoxi.jgframework/demo/test/jeecgNoteList");
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
	public void datagrid(JeecgNoteEntity jeecgNote,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JeecgNoteEntity.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgNote);
		this.jeecgNoteService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除公告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson del(JeecgNoteEntity jeecgNote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jeecgNote = systemService.getEntity(JeecgNoteEntity.class, jeecgNote.getId());
		message = "删除成功";
		jeecgNoteService.delete(jeecgNote);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson save(JeecgNoteEntity jeecgNote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgNote.getId())) {
			message = "更新成功";
			jeecgNoteService.saveOrUpdate(jeecgNote);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			jeecgNoteService.save(jeecgNote);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 公告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JeecgNoteEntity jeecgNote, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jeecgNote.getId())) {
			jeecgNote = jeecgNoteService.getEntity(JeecgNoteEntity.class, jeecgNote.getId());
			req.setAttribute("jeecgNotePage", jeecgNote);
		}
		return new ModelAndView("demo/test/jeecgNote");
		//return new ModelAndView("com.yoxi.jgframework/demo/test/jeecgNote");
	}
}
