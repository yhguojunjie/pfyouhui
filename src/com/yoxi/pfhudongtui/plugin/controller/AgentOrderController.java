package com.yoxi.pfhudongtui.plugin.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.yoxi.jgframework.util.JSONHelper;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.AgentOrder;
import com.yoxi.pfhudongtui.plugin.entity.AgentOrderEntity;
import com.yoxi.pfhudongtui.plugin.entity.PluginAct;
import com.yoxi.pfhudongtui.plugin.entity.PluginActEntity;
import com.yoxi.pfhudongtui.plugin.service.AgentOrderService;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;
import com.yoxi.pfhudongtui.utils.ConvertUtil;
import com.yoxi.pfhudongtui.utils.EncryptUtils;
import com.yoxi.pfhudongtui.utils.HttpBankRequest;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: AgentOrder
 * @author jwhat generate
 * @date 2015-03-27 09:35:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/agentOrderController")
public class AgentOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AgentOrderController.class);

	@Autowired
	private AgentOrderService agentOrderService;

	/**
	 * AgentOrder列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentOrder")
	public ModelAndView agentOrder(HttpServletRequest request) {
		return new ModelAndView("plugin/agentorder/agentOrderList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "agentOrderGrid")
	public void agentOrderGrid(AgentOrder agentOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(AgentOrder.class, dataGrid);
		cq.addOrder("orderTime", SortDirection.desc);
		cq.eq("agentInfo.id", getUser().getId());
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentOrder);
		this.agentOrderService.getDataGridReturn(cq, true);
		List<AgentOrder> list = dataGrid.getReaults();
		List<AgentOrderEntity> aList = new ArrayList<AgentOrderEntity>();
		for(AgentOrder l :list){
			AgentOrderEntity a = new AgentOrderEntity();
			a.setId(l.getId());
			a.setPluginName(l.getPlugin().getName());
			String realName = l.getPlatformUser().getRealName();
			if(realName==null){
				a.setUserName(l.getPlatformUser().getNickName());
			}else{
				a.setUserName(l.getPlatformUser().getNickName()+"<br>"+"姓名:"+realName);
			}
			if(l.getSalePrice() == null){
				a.setSalePrice(null);
			}else{
				double salePrice = l.getSalePrice();
				a.setSalePrice((int)salePrice);
			}
			a.setBuyNum(l.getBuyNum()+"个月");
			if("1".equals(l.getPurchaseType())){
				if(l.getSalePrice() == null){
					if(l.getRepreCoin() == null){
						a.setAmount(null);
					}else{
						double repreCoin = l.getRepreCoin();
						a.setAmount("代币实付"+(int)repreCoin);
					}
				}else{
					double repreCoin = l.getRepreCoin();
					a.setAmount("代币应付:"+(int)ConvertUtil.calPluginPrice(l.getBuyNum(), l.getSalePrice())+"<br>代币实付"+(int)repreCoin);
				}
			}else{
				if(l.getSalePrice() == null){
					if(l.getCharge() == null){
						a.setAmount(null);
					}else{
						double charge = l.getCharge();
						a.setAmount("实付:"+(int)charge);
					}
				}else{
					double charge = l.getCharge();
					a.setAmount("应付:"+(int)ConvertUtil.calPluginPrice(l.getBuyNum(), l.getSalePrice())+"<br>实付:"+(int)charge);
				}
			}
			a.setOrderTime(l.getOrderTime());
			a.setUserId(l.getPlatformUser().getUserId());
			a.setPluginIcon(server + "/" +l.getPlugin().getIcon());
			a.setTradeState(l.getTradeState());
			a.setPayOrderStatus(l.getPayOrderStatus());
			a.setProductType(l.getProductType());
			aList.add(a);
		}
		dataGrid.setReaults(aList);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 管理员查看所有订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "adminOrder")
	public ModelAndView adminOrder(HttpServletRequest request) {
		return new ModelAndView("plugin/agentorder/adminOrderList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "adminOrderGrid")
	public void adminOrderGrid(AgentOrder agentOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(AgentOrder.class, dataGrid);
		cq.addOrder("orderTime", SortDirection.desc);
		String companyName = oConvertUtils.getString(request.getParameter("companyName"));
		if(StringUtils.isNotBlank(companyName)){
			cq.createAlias("agentInfo", "ts");
			cq.like("ts.companyName", companyName);
			//cq.add(Restrictions.like("ts.title",actTitle));
		}
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentOrder);
		this.agentOrderService.getDataGridReturn(cq, true);
		List<AgentOrder> list = dataGrid.getReaults();
		List<AgentOrderEntity> aList = new ArrayList<AgentOrderEntity>();
		for(AgentOrder l :list){
			AgentOrderEntity a = new AgentOrderEntity();
			a.setId(l.getId());
			a.setPluginName(l.getPlugin().getName());
			String realName = l.getPlatformUser().getRealName();
			if(realName==null){
				a.setUserName(l.getPlatformUser().getNickName());
			}else{
				a.setUserName(l.getPlatformUser().getNickName()+"<br>"+"姓名:"+realName);
			}
			if(l.getSalePrice() == null){
				a.setSalePrice(null);
			}else{
				double salePrice = l.getSalePrice();
				a.setSalePrice((int)salePrice);
			}
			a.setBuyNum(l.getBuyNum()+"个月");
			if("1".equals(l.getPurchaseType())){
				if(l.getSalePrice() == null){
					double repreCoin = l.getRepreCoin();
					a.setAmount("代币实付"+(int)repreCoin);
				}else{
					double repreCoin = l.getRepreCoin();
					a.setAmount("代币应付:"+(int)ConvertUtil.calPluginPrice(l.getBuyNum(), l.getSalePrice())+"<br>代币实付"+(int)repreCoin);
				}
			}else{
				if(l.getSalePrice() == null){
					if(l.getCharge() == null){
						a.setAmount(null);
					}else{
						double charge = l.getCharge();
						a.setAmount("实付:"+(int)charge);
					}
				}else{
					double charge = l.getCharge();
					a.setAmount("应付:"+(int)ConvertUtil.calPluginPrice(l.getBuyNum(), l.getSalePrice())+"<br>实付:"+(int)charge);
				}
			}
			a.setOrderTime(l.getOrderTime());
			a.setUserId(l.getPlatformUser().getUserId());
			a.setPluginIcon(server + "/" +l.getPlugin().getIcon());
			a.setTradeState(l.getTradeState());
			a.setPayOrderStatus(l.getPayOrderStatus());
			a.setCompanyName(l.getAgentInfo().getCompanyName());
			a.setProductType(l.getProductType());
			aList.add(a);
		}
		dataGrid.setReaults(aList);
		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * 删除AgentOrder
	 * 
	 * @return
	 */
	@RequestMapping(params = "delAgentOrder")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAgentOrder(AgentOrder agentOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		agentOrder = systemService.getEntity(AgentOrder.class, agentOrder.getId());
		message = "删除成功";
		agentOrderService.delete(agentOrder);		
		j.setMsg(message);
		return j;
	}
	/**
	 * 关闭交易订单
	 * @param agentOrder
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "showDownOrder")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson showDownOrder(AgentOrder agentOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Integer n = agentOrderService.showDownOrder(agentOrder.getId());	
		if(n>0)
			message = "关闭成功";
		else
			message = "关闭失败";
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加AgentOrder
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAgentOrder")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAgentOrder(AgentOrder agentOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		double charge = agentOrder.getCharge();
		if(charge == 0){
			try {
				AgentOrder order = agentOrderService.get(AgentOrder.class, agentOrder.getId());
				String paybackUrl = PropertiesUtils.getPara("paybackUrl");
				String key = PropertiesUtils.getPara("paybackAuthKey");
				StringBuffer authBuffer = new StringBuffer();
				authBuffer.append(order.getPlatformUser().getUserId());
				authBuffer.append(order.getPlugin().getId());
				authBuffer.append(order.getAgentInfo().getId());
				authBuffer.append(key);
				String authorcator = EncryptUtils.encodeByMd5(authBuffer.toString());
				System.out.print("~~~~~~~~~~~~~~~~~~:"+authorcator);
				//调用前端接口
				//0处理成功 1 签权失败   2该定单已被处理过 3 交易状态值不明确 4 该定单不存在
				String s = HttpBankRequest.post(paybackUrl, "authStr="+authorcator+"&orderId="+order.getId());
				JSONObject obj =JSONHelper.toJSONObject(s);  
				Integer status = obj.getInt("status");
				if(status == 0)
					j.setMsg("处理成功");
				if(status == 1)
					j.setMsg("签权失败");
				if(status == 2)
					j.setMsg("该定单已被处理过 ");
				if(status == 3)
					j.setMsg("交易状态值不明确 ");
				if(status == 4)
					j.setMsg("该定单不存在");
				
			} catch (Exception e) {
				j.setMsg("系统繁忙,请稍后处理");
				e.printStackTrace();
			}
		}else{
			if (StringUtil.isNotEmpty(agentOrder.getId())) {
				message = "更新成功";
				this.isNew = false;
				AgentOrder t = agentOrderService.get(AgentOrder.class, agentOrder.getId());
				try {
					MyBeanUtils.copyBeanNotNull2Bean(agentOrder, t);
					agentOrderService.saveOrUpdate(t);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				message = "添加成功";
				this.isNew = true;
				agentOrderService.save(agentOrder);
			}
		}
		return j;
	}

	/**
	 * AgentOrder列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateAgentOrder")
	public ModelAndView addorupdateAgentOrder(AgentOrder agentOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(agentOrder.getId())) {
			agentOrder = agentOrderService.getEntity(AgentOrder.class, agentOrder.getId());
			if(agentOrder.getSalePrice() != null){
				//应付
				if("1".equals(agentOrder.getPurchaseType())){
					req.setAttribute("price", (int)ConvertUtil.calPluginPrice(agentOrder.getBuyNum(), agentOrder.getSalePrice()));
				}else{
					req.setAttribute("price", (int)ConvertUtil.calPluginPrice(agentOrder.getBuyNum(),agentOrder.getSalePrice()));
				}
				//单价
				double salePrice = agentOrder.getSalePrice();
				req.setAttribute("salePrice", (int)salePrice);
			}
			
			//成本价
			if(agentOrder.getCostPrice() != null)
				req.setAttribute("costPrice", (int)ConvertUtil.calPluginPrice(agentOrder.getBuyNum(), agentOrder.getCostPrice()));
			req.setAttribute("agentOrderPage", agentOrder);
		}
		return new ModelAndView("plugin/agentorder/agentOrderPage");
	}
	
	/**
	 * AgentOrder列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "contactOrder")
	@ResponseBody
	@AddOrUpdateAnnotation
	public PlatformUser contactOrder(PlatformUser platformUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(platformUser.getUserId())) {
			platformUser = systemService.getEntity(PlatformUser.class, platformUser.getUserId());
		}
		return platformUser;
	}
}
