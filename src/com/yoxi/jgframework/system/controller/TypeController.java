package com.yoxi.jgframework.system.controller;

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
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.common.model.json.ValidForm;
import com.yoxi.jgframework.system.entity.TSCodeDetail;
import com.yoxi.jgframework.system.entity.TSCodeType;
import com.yoxi.jgframework.system.service.CodeDetailService;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * 字典管理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/typeController")
public class TypeController extends BaseServiceController {
	
	protected CodeDetailService codedetailService;
		
	public CodeDetailService getCodedetailService() {
		return codedetailService;
	}
	
	@Autowired
	public void setCodedetailService(CodeDetailService codedetailService) {
		this.codedetailService = codedetailService;
	}
	
	
	
	/*******************************字典类型管理  begin*************************************/
	/**
	 * 字典类型列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "typeGroup")
	public ModelAndView typeGroupList(HttpServletRequest request) {
		return new ModelAndView("system/type/typeGroupList");
	}
	
	/**
	 * easyuiAJAX请求数据，获取字典类型Grid数据
	 */

	@RequestMapping(params = "typeGroupGrid")
	public void typeGroupGrid(TSCodeType aTSCodeType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSCodeType.class, dataGrid);
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aTSCodeType);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 删除字典类型操作
	 * 
	 * @return
	 */
	@RequestMapping(params = "delTypeGroup")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delTypeGroup(TSCodeType typegroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		typegroup = systemService.getEntity(TSCodeType.class, typegroup.getId());
		message = "代码类型: " + typegroup.getTypeName() + "被删除成功";
		systemService.delete(typegroup);
		//刷新缓存
		systemService.refleshTypeGroupCach();
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 检查字典类型编码（主键）唯一性
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkTypeID")
	@ResponseBody
	public ValidForm checkTypeID(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		//输入的值
		String id=oConvertUtils.getString(request.getParameter("param"));
		//修改前的原始值
		String idOld =oConvertUtils.getString(request.getParameter("id"));
		if (idOld.equals(id)) //值没有被修改，直接返回
			return v;
		//查找数据，判断
		List<TSCodeType> typegroups=systemService.findByProperty(TSCodeType.class,"id",id);
		if(typegroups.size()>0&&!idOld.equals(id))
		{
			v.setInfo("代码类型编码已存在");
			v.setStatus("n");
		}
		return v;
	}
	
	/**
	 * 字典类型新建、编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "aouTypeGroup")
	public ModelAndView aouTypeGroup(TSCodeType typegroup, HttpServletRequest req) {
		if (typegroup.getId() != null) {
			typegroup = systemService.getEntity(TSCodeType.class, typegroup.getId());
			req.setAttribute("typegroup", typegroup);
		}
		return new ModelAndView("system/type/typegroup");
	}

	/**
	 * 添加、修改字典类型时，数据保存操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveTypeGroup")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveTypeGroup(TSCodeType typegroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//由于checkbox的未选中时 checked的值是null，因此如果null则为false
		if (typegroup.getAllowAdd() == null)
			typegroup.setAllowAdd(false);
		if (typegroup.getAllowDelete() == null)
			typegroup.setAllowDelete(false);
		if (typegroup.getAllowModify() == null)
			typegroup.setAllowModify(false);
		String idOld =oConvertUtils.getString(request.getParameter("idOld"));
		if (StringUtil.isNotEmpty(idOld)) {
			message = "代码类型: " + typegroup.getTypeName() + "被更新成功";
			this.isNew = false;
			userService.saveOrUpdate(typegroup);
			//刷新缓存
			systemService.refleshTypeGroupCach();
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "代码类型: " + typegroup.getTypeName() + "被添加成功";
			this.isNew = true;
			userService.save(typegroup);
			//刷新缓存
			systemService.refleshTypeGroupCach();
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/*******************************字典类型管理   end*************************************/
	
	
	/*******************************字典细项管理  begin*************************************/
	/**
	 * 字典细项维护列表页面跳转
	 * 为每个字典类型创建一个tab
	 * @return
	 */
	@RequestMapping(params = "typeGroupTabs")
	public ModelAndView typeGroupTabs(HttpServletRequest request) {
		List<TSCodeType> typegroupList = systemService.loadAll(TSCodeType.class);
		request.setAttribute("typegroupList", typegroupList);
		return new ModelAndView("system/type/typeGroupTabs");
	}

	/**
	 * 某一个字典细项列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "type")
	public ModelAndView typeList(HttpServletRequest request) {
		String typegroupid = request.getParameter("typegroupid");
		TSCodeType typegroup = systemService.getEntity(TSCodeType.class, typegroupid);
		request.setAttribute("typegroup", typegroup);
		return new ModelAndView("system/type/typeList");
	}

	
	/**
	 * easyuiAJAX请求数据，某一个字典细项Grid数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "typeGrid")
	public void typeGrid(TSCodeDetail aTSCodeDetail, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String typegroupid = request.getParameter("typegroupid");
		CriteriaQuery cq = new CriteriaQuery(TSCodeDetail.class, dataGrid);
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aTSCodeDetail);
		cq.eq("TSCodeType.id", typegroupid);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}	
	
	/**
	 * 删除字典细项
	 * 
	 * @return
	 */
	@RequestMapping(params = "delType")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson delType(TSCodeDetail type, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		type = systemService.getEntity(TSCodeDetail.class, type.getId());
		type.setDeleteFlag(true);
		message = "代码: " + type.getCodeName() + "被禁用成功";
		this.isNew = false;
		systemService.saveOrUpdate(type);
		//刷新缓存
		systemService.refleshCodeDetailsCach(type);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	/**
	 * 恢复字典细项
	 * 
	 * @return
	 */
	@RequestMapping(params = "restoreType")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson restoreType(TSCodeDetail type, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		type = systemService.getEntity(TSCodeDetail.class, type.getId());
		type.setDeleteFlag(false);
		message = "代码: " + type.getCodeName() + "被恢复成功";
		this.isNew = false;
		systemService.saveOrUpdate(type);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	
	

	
	/**
	 * 新建、修改字典细项页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateType")
	public ModelAndView addorupdateType(TSCodeDetail type, HttpServletRequest req) {
		String typegroupid = req.getParameter("typegroupid");
		req.setAttribute("typegroupid", typegroupid);
		
		TSCodeType typegroup = systemService.getEntity(TSCodeType.class, typegroupid);
		req.setAttribute("typegroup", typegroup);
		
		if (StringUtil.isNotEmpty(type.getId())) {
			type = systemService.getEntity(TSCodeDetail.class, type.getId());
			req.setAttribute("type", type);
		}
		return new ModelAndView("system/type/type");
	}
	
	/**
	 * 新建、修改字典细项时，保存数据操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveType")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveType(TSCodeDetail type, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();		
		if (StringUtil.isNotEmpty(type.getId())) {
			message = "代码: " + type.getCodeName() + "被更新成功";
			this.isNew = false;
			userService.saveOrUpdate(type);
			
			//刷新缓存
			systemService.refleshCodeDetailsCach(type);
			
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "代码: " + type.getCodeName() + "被添加成功";
			this.isNew = true;
			userService.save(type);
			
			//刷新缓存
			systemService.refleshCodeDetailsCach(type);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "saveOrder")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveOrder(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();		
		String currentId = request.getParameter("currentId");
		String backId = request.getParameter("backId");
		String beforeId = request.getParameter("beforeId");
		if (oConvertUtils.isEmpty(currentId))
			message = "没有执行排序动作";
		else if (oConvertUtils.isEmpty(backId) && !oConvertUtils.isEmpty(beforeId)) {
			//将当前项目往前面排序，当前currentId顺序改成backId顺序，backId及其之后的项目顺序全部+1；
			TSCodeDetail currentCodeDetail = systemService.getEntity(TSCodeDetail.class, oConvertUtils.getInt(currentId));
			TSCodeDetail beforeCodeDetail = systemService.getEntity(TSCodeDetail.class, oConvertUtils.getInt(beforeId));
			message = currentId + "往前排序,放在" +beforeId +" 之前";
			
			codedetailService.orderCodeDetail(currentCodeDetail, beforeCodeDetail, null);
			
		}
		else  if (oConvertUtils.isEmpty(beforeId) && !oConvertUtils.isEmpty(backId)) {
			//将当前项目往后面排序，当前currentId顺序改成beforeId顺序，beforeId及其之后的项目顺序全部-1；
			TSCodeDetail currentCodeDetail = systemService.getEntity(TSCodeDetail.class, oConvertUtils.getInt(currentId));
			TSCodeDetail backCodeDetail = systemService.getEntity(TSCodeDetail.class, oConvertUtils.getInt(backId));
			
			message = currentId + "往后排序,放在" +backId +" 之后";
			codedetailService.orderCodeDetail(currentCodeDetail, null, backCodeDetail);
			
		}else{
			message = "没有执行排序动作";
		}	
		
		j.setMsg(message);
		return j;
	}
	

	
}
