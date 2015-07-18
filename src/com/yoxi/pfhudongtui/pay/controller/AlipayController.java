package com.yoxi.pfhudongtui.pay.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.model.common.SessionInfo;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.util.FileUtils;
import com.yoxi.pfhudongtui.pay.entity.BuserOrder;
import com.yoxi.pfhudongtui.pay.entity.DirectPaySyncBack;
import com.yoxi.pfhudongtui.pay.entity.VOrder;
import com.yoxi.pfhudongtui.pay.service.AlipayService;
import com.yoxi.pfhudongtui.pay.service.BuserOrderService;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**
 * 
 * 支付宝回调
 *
 * @author wql
 *
 * @Date 2015年4月1日
 *
 */
@Controller
@RequestMapping("/alipayController")
public class AlipayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AlipayController.class);
	
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private BuserOrderService buserOrderService;
	
	/**
	 * 下单并跳转到支付宝支付
	 * @param order
	 * @return
	 */
	@RequestMapping(params = "pay")
	public ModelAndView pay(BuserOrder buserOrder,HttpServletRequest request){
		HttpSession session = request.getSession(false);
		SessionInfo sessioninfo = (SessionInfo) session.getAttribute(Globals.USER_SESSION);
		//1.系统下单处理
		//TODO
		buserOrder.setBuserId(sessioninfo.getUser().getId());
		buserOrder.setPurchaseType("2");
		buserOrder.setPayOrderStatus("0");
		buserOrder.setOrderTime(new Date());
		buserOrderService.save(buserOrder);
		//2.下单成功后，准备好参数， 调用支付支付接口
		request.setAttribute("WIDseller_email", PropertiesUtils.getPara("alipay_seller_account"));
		request.setAttribute("notify_url",PropertiesUtils.getPara("alipay_directpay_notify_url"));
		request.setAttribute("return_url",PropertiesUtils.getPara("alipay_directpay_return_url"));
		
		request.setAttribute("WIDout_trade_no", "hdt_"+buserOrder.getId()); //传入系统生成的定单号
		request.setAttribute("WIDsubject", "互动推代理商充值"); //商品描述
		request.setAttribute("WIDtotal_fee",buserOrder.getCharge()+""); //商品价格
		return new ModelAndView("pay/alipay/directpay/alipayapi");
	}
	
	
	/**
	 * 即时到账支付回调
	 * @param directPaySyncBack
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/back",method = RequestMethod.POST)
	public void directPayback(DirectPaySyncBack directPaySyncBack,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("*************支付宝即时到账回调*********************");
		String ret = alipayService.directPayBack(directPaySyncBack, request);
        PrintWriter out;
		try {
			out = response.getWriter();
			out.print(ret); 
		    out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("************支付宝即时到账，回调通知支付宝出错！");
		}
	}

	/**
	 * 充值成功
	 * @param buserOrder
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/successPay", method = RequestMethod.GET)
	public ModelAndView test(DirectPaySyncBack directPaySyncBack,HttpServletRequest request){
		//BuserOrder buserOrder = systemService.getEntity(BuserOrder.class, getUser().getId());
		String webUrl = FileUtils.getWebRootURL(request);
		request.setAttribute("directPaySyncBack",directPaySyncBack );
		request.setAttribute("webUrl", webUrl);
		return new ModelAndView("pay/alipay/directpay/success");
	}
	
	
}
