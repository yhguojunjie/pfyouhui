package com.yoxi.pfhudongtui.user.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
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
import com.yoxi.jgframework.common.model.json.ValidForm;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.user.entity.AgentWithdraw;
import com.yoxi.pfhudongtui.user.entity.AgentWithdrawEntity;
import com.yoxi.pfhudongtui.user.entity.VAgentInfo;
import com.yoxi.pfhudongtui.user.service.AgentInfoService;
import com.yoxi.pfhudongtui.user.service.AgentWithdrawService;
import com.yoxi.pfhudongtui.utils.HttpBankRequest;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;
import com.yoxi.pfhudongtui.utils.UtilCard;

/**   
 * @Title: Controller
 * @Description: AgentInfo
 * @author jwhat generate
 * @date 2015-02-05 17:06:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/agentInfoController")
public class AgentInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AgentInfoController.class);

	@Autowired
	private AgentInfoService agentInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private AgentWithdrawService agentWithdrawService;

	/**
	 * AgentInfo列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentInfo")
	public ModelAndView agentInfo(HttpServletRequest request) {
		return new ModelAndView("user/agentinfo/agentInfoList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

//	@RequestMapping(params = "agentInfoGrid")
//	public void agentInfoGrid(AgentInfo agentInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		CriteriaQuery cq = new CriteriaQuery(AgentInfo.class, dataGrid);
//		//查询条件组装器
//		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentInfo);
//		this.agentInfoService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);
//		
//	}
	
//	@RequestMapping(params = "agentInfoGrid")
//	public void agentInfoGrid(TSBaseUser tSBaseUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		CriteriaQuery cq = new CriteriaQuery(TSBaseUser.class, dataGrid);
//		cq.createAlias("TSDepart", "ts");
//		cq.add(Restrictions.eq("ts.id","02000000"));
//		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSBaseUser);
//		this.systemService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);
//		
//	}
	
	@RequestMapping(params = "vagentInfoGrid")
	public void agentInfoGrid(VAgentInfo vAgentInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VAgentInfo.class, dataGrid);
		cq.add(Restrictions.eq("departid","01000000"));
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, vAgentInfo);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		
	}
	

	/**
	 * 删除AgentInfo
	 * 
	 * @return
	 */
	@RequestMapping(params = "delAgentInfo")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAgentInfo(AgentInfo agentInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		agentInfo = systemService.getEntity(AgentInfo.class, agentInfo.getId());
		message = "删除成功";
		agentInfoService.delete(agentInfo);
//		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加AgentInfo
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAgentInfo")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAgentInfo(AgentInfo agentInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(agentInfo.getId())) {
			message = "更新成功";
			this.isNew = false;
			AgentInfo t = agentInfoService.get(AgentInfo.class, agentInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(agentInfo, t);
				agentInfoService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
//			agentInfo.setId(1);
			TSDepart tsDepart = new TSDepart();
			tsDepart.setId("02000000");
			agentInfoService.save(agentInfo);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * AgentInfo列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateAgentInfo")
	public ModelAndView addorupdateAgentInfo(AgentInfo agentInfo, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(agentInfo.getId())) {
			agentInfo = agentInfoService.getEntity(AgentInfo.class, agentInfo.getId());
			req.setAttribute("agentInfoPage", agentInfo);
		}
		return new ModelAndView("user/agentinfo/agentInfoPage").addObject("server", server);
	}
	
	/**
	 * 检查银行卡号是否正确
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkCard")
	@ResponseBody
	public ValidForm checkCard(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String param = request.getParameter("param").trim();
		String card=oConvertUtils.replaceBlank(param);
		if(!UtilCard.checkBankCard(card)){
			v.setInfo("卡号输入有误!");
			v.setStatus("n");
		}
		return v;
	}
	/**
	 * 代理商提现
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "agentWithdrawal")
	public ModelAndView agentWithdrawal(HttpServletRequest request) {
		AgentInfo agentInfo = agentInfoService.getEntity(AgentInfo.class, getUser().getId());
		if(agentInfo != null){
			request.setAttribute("agentInfo", agentInfo);
			double availableCash = agentInfo.getTotalIncome()-agentInfo.getTotalCash();
			request.setAttribute("availableCash", (int)availableCash);
			double totalIncome =  agentInfo.getTotalIncome();
			request.setAttribute("totalIncome",(int)totalIncome);
			double totalCash = agentInfo.getTotalCash();
			request.setAttribute("totalCash", (int)totalCash);
		}
		return new ModelAndView("user/agentinfo/withdrawal");
	}
	/**
	 * 禁止和启用用户
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "userDisableEnable")
	@ResponseBody
	public AjaxJson userDisableEnable(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Integer status = Integer.parseInt(request.getParameter("status"));
		Integer userId = Integer.parseInt(request.getParameter("id"));
		if(status == 0){
			Integer n = agentInfoService.updateBySqlString("update t_s_base_user set status=1 where id ="+userId);
			if(n>0)
				j.setMsg("操作成功");
			else
				j.setMsg("操作失败");
		}else{
			Integer n = agentInfoService.updateBySqlString("update t_s_base_user set status=0 where id ="+userId);
			if(n>0)
				j.setMsg("操作成功");
			else
				j.setMsg("操作失败");
			
		}
		return j;
	}
	/**
	 * 提现记录跳转界面
	 * agentWithdrawRecord列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentWithdrawRecord")
	public ModelAndView agentWithdrawRecord(HttpServletRequest request) {
		return new ModelAndView("user/agentinfo/withdrawalRecordList");
	}
	/**
	 * 提现记录
	 * @param agentWithdraw
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "agentWithdrawRecordGrid")
	public void agentWithdrawRecordGrid(AgentWithdraw agentWithdraw,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AgentWithdraw.class, dataGrid);
		cq.addOrder("applyTime", SortDirection.desc);
		cq.eq("agentInfo.id", getUser().getId());
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentWithdraw);
		this.agentWithdrawService.getDataGridReturn(cq, true);
		List<AgentWithdraw> list = dataGrid.getReaults();
		List<AgentWithdrawEntity> withList = new ArrayList<AgentWithdrawEntity>();
		for(AgentWithdraw l :list){
			AgentWithdrawEntity with = new AgentWithdrawEntity();
			with.setId(l.getId());
			double cash = l.getCash();
			with.setCash((int)cash);
			with.setCashType(l.getCashType());
			with.setApplyName(l.getAgentInfo().getCompanyName());
			with.setApplyTime(l.getApplyTime());
			String auditState = l.getAuditState();
			if("0".equals(auditState)){
				with.setAuditState("<font color='blue'>未处理</font>");
			}else{
				if("1".equals(auditState)){
					with.setAuditState("已处理");
				}else{
					with.setAuditState("其他");
				}
			}
			withList.add(with);
		}
		dataGrid.setReaults(withList);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 修改支付账号
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "editPayAcount")
	public ModelAndView editPayAcount(HttpServletRequest request) {
		AgentInfo agentInfo = agentInfoService.getEntity(AgentInfo.class,getUser().getId());
		request.setAttribute("agentInfo", agentInfo);
		return new ModelAndView("user/agentinfo/agentEditAcount");
	}
	/**
	 * 检测指向域名
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "checkMydomain")
	@ResponseBody
	public ValidForm checkMydomain(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String param = request.getParameter("param").trim();
		List<Integer> list = systemService.findListbySql("select id from t_agent_info where mydomain='"+param+"'");
		if(list.size()>0){
			v.setInfo("该指向域名已被占用");
			v.setStatus("n");
		}
		return v;
	}
	/**
	 * 检测转发域名
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "checkForwardDomain")
	@ResponseBody
	public ValidForm checkForwardDomain(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String param = request.getParameter("param").trim();
		List<Integer> list = systemService.findListbySql("select id from t_agent_info where forwardDomain='"+param+"'");
		if(list.size()>0){
			v.setInfo("该转发域名已被占用");
			v.setStatus("n");
		}
		return v;
	}
	/**
	 * 检测分享跳转域名
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "checkRedirecDomain")
	@ResponseBody
	public ValidForm checkRedirecDomain(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String param = request.getParameter("param").trim();
		List<Integer> list = systemService.findListbySql("select id from t_agent_info where redirecDomain='"+param+"'");
		if(list.size()>0){
			v.setInfo("该分享跳转域名已被占用");
			v.setStatus("n");
		}
		return v;
	}
}
