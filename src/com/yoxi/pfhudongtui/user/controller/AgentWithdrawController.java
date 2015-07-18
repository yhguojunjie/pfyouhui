package com.yoxi.pfhudongtui.user.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.yoxi.jgframework.common.model.common.SessionInfo;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.system.entity.TSBaseUser;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.Plugin;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.user.entity.AgentWithdraw;
import com.yoxi.pfhudongtui.user.entity.AgentWithdrawEntity;
import com.yoxi.pfhudongtui.user.service.AgentInfoService;
import com.yoxi.pfhudongtui.user.service.AgentWithdrawService;

/**   
 * @Title: Controller
 * @Description: AgentWithdraw
 * @author jwhat generate
 * @date 2015-03-28 17:36:41
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/agentWithdrawController")
public class AgentWithdrawController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AgentWithdrawController.class);

	@Autowired
	private AgentWithdrawService agentWithdrawService;
	@Autowired
	private AgentInfoService agentInfoService;

	/**
	 * AgentWithdraw列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentWithdraw")
	public ModelAndView agentWithdraw(HttpServletRequest request) {
		return new ModelAndView("user/agentwithdraw/agentWithdrawList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "agentWithdrawGrid")
	public void agentWithdrawGrid(AgentWithdraw agentWithdraw,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AgentWithdraw.class, dataGrid);
		cq.addOrder("applyTime", SortDirection.desc);
		//查询条件组装器
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
			TSBaseUser base = l.gettSBaseUser();
			if(base == null){
				with.setAuditName("");
			}else{
				with.setAuditName(l.gettSBaseUser().getRealName());
			}
			
			with.setAuditState(l.getAuditState());
			with.setAuditTime(l.getAuditTime());
			withList.add(with);
		}
		dataGrid.setReaults(withList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除AgentWithdraw
	 * 
	 * @return
	 */
	@RequestMapping(params = "delAgentWithdraw")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAgentWithdraw(AgentWithdraw agentWithdraw, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		agentWithdraw = systemService.getEntity(AgentWithdraw.class, agentWithdraw.getId());
		message = "删除成功";
		agentWithdrawService.delete(agentWithdraw);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加AgentWithdraw
	 * 
	 * @param ids
	 * @return
	 
	@RequestMapping(params = "saveAgentWithdraw")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAgentWithdraw(AgentWithdraw agentWithdraw, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(agentWithdraw.getId())) {
			message = "更新成功";
			this.isNew = false;
			AgentWithdraw t = agentWithdrawService.get(AgentWithdraw.class, agentWithdraw.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(agentWithdraw, t);
				agentWithdrawService.saveOrUpdate(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			agentWithdrawService.save(agentWithdraw);
		}
		
		return j;
	}
*/
	/**
	 * AgentWithdraw列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateAgentWithdraw")
	public ModelAndView addorupdateAgentWithdraw(AgentWithdraw agentWithdraw, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(agentWithdraw.getId())) {
			agentWithdraw = agentWithdrawService.getEntity(AgentWithdraw.class, agentWithdraw.getId());
			req.setAttribute("agentWithdrawPage", agentWithdraw);
		}
		return new ModelAndView("user/agentwithdraw/agentWithdrawPage");
	}
	
	/**
	 * AgentWithdraw列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveAgentWithdraw")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAgentWithdraw(HttpServletRequest req) {
		Double cash = Double.valueOf(req.getParameter("cash"));
		AjaxJson j = new AjaxJson();
		Integer applyUserId = Integer.parseInt(req.getParameter("applyUserId"));
		String cashType = req.getParameter("cashType");
		AgentInfo agentInfo=agentInfoService.getEntity(AgentInfo.class, applyUserId);
		if(agentInfo == null){
			j.setSuccess(false);
			j.setMsg("错误信息");
			return j;
		}
		if(agentInfo.getBlance()<=0){
			j.setSuccess(false);
			j.setMsg("预存余额不足,停止提现!");
			return j;
		}
		if((agentInfo.getTotalIncome()-agentInfo.getTotalCash())<cash){
			j.setSuccess(false);
			j.setMsg("提现金额超出了可用提现金额!");
			return j;
		}
		if(cash < 1000){
			j.setSuccess(false);
			j.setMsg("提现金额必须在1000元以上!");
			return j;
		}
		if("0".equals(cashType)){
			if("".equals(agentInfo.getAlipay()) || agentInfo.getAlipay() == null){
				j.setSuccess(false);
				j.setMsg("错误信息:支付宝账号没有填写");
				return j;
			}
		}
		if("1".equals(cashType)){
			if("".equals(agentInfo.getBankAccount()) || agentInfo.getBankAccount() == null){
				j.setSuccess(false);
				j.setMsg("错误信息:银行账号写没有填写");
				return j;
			}
		}
		List<Integer> list =agentWithdrawService.findListbySql("select id from t_agent_withdraw where applyUserId="+applyUserId+" and auditState='0'");
		if(list.size()>0){
			j.setSuccess(false);
			j.setMsg("提交失败,你有一单提现金额在审核中");
		}else{
			AgentWithdraw draw = new AgentWithdraw();
			draw.setCash(cash);
			draw.setCashType(cashType);
			draw.setAgentInfo(agentInfo);
			draw.setApplyTime(new Date());
			draw.setAuditState("0");
			agentWithdrawService.save(draw);
			j.setSuccess(true);
			j.setMsg("申请提现成功,预计2-3个工作日完成审核");
		}
		return j;
	}
	
	/**
	 * 提现审核
	 * @param plugin
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "auditWithdraw")
	@ResponseBody
	public AjaxJson auditWithdraw(AgentWithdraw agentWithdraw, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Integer num =agentWithdrawService.executeSql("update  t_agent_withdraw set auditState='1',auditUserId=? ,auditTime=? where id=?",getUser().getId(),new Date(),agentWithdraw.getId());
		if(num > 0){
			AgentWithdraw draw = agentWithdrawService.getEntity(AgentWithdraw.class, agentWithdraw.getId());
			AgentInfo agentInfo = draw.getAgentInfo();
			//提现金额
			Double drawBlance = draw.getCash();
			agentInfo.setTotalCash(agentInfo.getTotalCash()+drawBlance);
			agentInfoService.saveOrUpdate(agentInfo);
			message = "审核成功";
		}else{
			message ="审核失败";
		}
		j.setMsg(message);
		return j;
	}
	
}
