package com.yoxi.pfhudongtui.plugin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.system.entity.TSRole;
import com.yoxi.jgframework.system.entity.TSRoleUser;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.pfhudongtui.content.entity.NoticeVersion;
import com.yoxi.pfhudongtui.plugin.entity.AgentOrder;
import com.yoxi.pfhudongtui.plugin.service.AgentOrderService;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.utils.DateUtils;

@Controller
@RequestMapping("/homePageController")
public class HomePageController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(HomePageController.class);
	
	@Autowired
	private AgentOrderService agentOrderService;
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "homePage")
	public ModelAndView pluginAct(HttpServletRequest request) {
//		String roles = "";
//		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", getUser().getId());
//		for (TSRoleUser ru : rUsers) {
//			TSRole role = ru.getTSRole();
//			roles += role.getRoleName() + ",";
//		}
//		if(roles.contains("管理员")){
//			return new ModelAndView("plugin/homepage/adminPage");
//		}
		String depart = getUser().getTSDepart().getId();
		if("01000000".equals(depart)){
			List<NoticeVersion> noticeVersionList = systemService.findObjForJdbc("select * from t_notice_version order by seq asc", 1, 5, NoticeVersion.class);
			request.setAttribute("noticeVersionList", noticeVersionList);
			return new ModelAndView("plugin/homepage/homePage");
		}else{
			return new ModelAndView("plugin/homepage/adminPage");
		}
	}
	//今日订单数
	@RequestMapping(params = "todayOrderNum")
	@ResponseBody
	public Integer todayOrderNum(HttpServletRequest request){
		Date startTime = DateUtils.getTodayStart();
		Date endTime =DateUtils.getTodayEnd();
		CriteriaQuery cq = new CriteriaQuery(AgentOrder.class);
		cq.between("orderTime", startTime, endTime);
		cq.eq("agentInfo.id",  getUser().getId());
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new AgentOrder());
		List<AgentOrder> list = systemService.getListByCriteriaQuery(cq, false);
		return list.size();
	}
	//待付款数
	@RequestMapping(params = "payMentNum")
	@ResponseBody
   public Integer payMentNum(HttpServletRequest request){
		List<Integer> list = systemService.findListbySql("select id from t_agent_order where agentId="+ getUser().getId()+" and payOrderStatus='0' and tradeState='0'");
		return list.size();
   }
   //模板总数
	@RequestMapping(params = "agentPluginNum")
	@ResponseBody
   public Integer agentPluginNum(HttpServletRequest request){
		List<Integer> list = systemService.findListbySql("select id from t_plugin_agent where agentId="+ getUser().getId());
		return list.size();
   }
   //待定价数
	@RequestMapping(params = "withPricNum")
	@ResponseBody
   public Integer withPricNum(HttpServletRequest request){
		List<Integer> list = systemService.findListbySql("select id from t_plugin_agent where agentId="+ getUser().getId()+" and salePrice=0");
		return list.size();
   }
   //总访数
	@RequestMapping(params = "trafficNum")
	@ResponseBody
   public Integer trafficNum(HttpServletRequest request){
		List<Integer> list = systemService.findListbySql("select browseNum from t_plugin_act where agentId="+ getUser().getId());
		Integer sum =0;
		for(Integer a : list){
			sum=sum+a;
		}
		return sum;
   }
   //昨日成交订单数
	@RequestMapping(params = "yesterdayOrderNum")
	@ResponseBody
   public Map<String,?> yesterdayOrderNum(HttpServletRequest request){
		Date startTime = DateUtils.getStartDate(DateUtils.getDateBefore(new Date(), 1));
		Date endTime = DateUtils.getEndDate(DateUtils.getDateBefore(new Date(), 1));
		CriteriaQuery cq = new CriteriaQuery(AgentOrder.class);
		cq.between("orderTime", startTime, endTime);
		cq.eq("agentInfo.id",  getUser().getId());
		cq.eq("payOrderStatus", "1");
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new AgentOrder());
		List<AgentOrder> list = systemService.getListByCriteriaQuery(cq, false);
		Map<String,Object> map = new HashMap<String, Object>();
		Integer orderNum = list.size();
		double sumAmount = 0;
		double sumEarnings = 0;
		for(AgentOrder a : list){
			if(a.getCharge() == null)continue;
			//昨日成交金额
			sumAmount = sumAmount + a.getCharge();
			//昨日收益
			sumEarnings = sumEarnings + (a.getCharge()-a.getPlugin().getPrice());
		}
		map.put("yesterdayOrderNum", orderNum);
		map.put("yesterdayAmount", sumAmount);
		map.put("yesterdayEarnings", sumEarnings);
		return map;
   }
   //累计收益
	@RequestMapping(params = "addEarnings")
	@ResponseBody
   public  Map<String,?> addEarnings(HttpServletRequest request){
	   AgentInfo info = systemService.getEntity(AgentInfo.class,  getUser().getId());
	   Map<String,Object> map = new HashMap<String, Object>();
	   if(info == null){
		   //累计收益
		   map.put("addEarnings", 0);
		   //当前账户余额
		   map.put("blance", 0);
		   //指向域名
		   map.put("mydomain", "");
	   }else{
		   //累计收益
		   map.put("addEarnings", info.getTotalIncome());
		   //当前账户余额
		   map.put("blance", info.getBlance());
		   //指向域名
		   map.put("mydomain", info.getMydomain());
		   
	   }
	   return map;
   }
   //本月成交订单数
	@RequestMapping(params = "monthOrder")
	@ResponseBody
   public Integer monthOrder(HttpServletRequest request){
		Date startTime = DateUtils.getCurrentMonthStartTime();
		Date endTime =DateUtils.getCurrentMonthEndTime();
		CriteriaQuery cq = new CriteriaQuery(AgentOrder.class);
		cq.between("orderTime", startTime, endTime);
		cq.eq("agentInfo.id",  getUser().getId());
		cq.eq("payOrderStatus", "1");
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, new AgentOrder());
		List<AgentOrder> list = systemService.getListByCriteriaQuery(cq, false);
		return list.size();
   }
   /**
    * 跳转支付界面
    * @param req
    * @return
    */
	@RequestMapping(params = "chargeBlance")
	public ModelAndView chargeBlance( HttpServletRequest req) {
		AgentInfo info = systemService.getEntity(AgentInfo.class,  getUser().getId());
		req.setAttribute("agentInfo", info);
		return new ModelAndView("plugin/homepage/chargeBlance");
	}
   
}
