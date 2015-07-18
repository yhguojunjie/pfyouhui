package com.yoxi.pfhudongtui.pay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.pay.entity.BuserOrder;
import com.yoxi.pfhudongtui.pay.entity.DirectPaySyncBack;
import com.yoxi.pfhudongtui.pay.service.AlipayService;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.utils.BeanConverter;
import com.yoxi.pfhudongtui.utils.pay.alipay.util.AlipayNotify;


@Service("alipayService")
@Transactional
public class AlipayServiceImpl extends CommonServiceImpl implements AlipayService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AlipayServiceImpl.class);
	
	@Override
	public String directPayBack(DirectPaySyncBack directPaySyncBack,
			HttpServletRequest request) {
		//1.根据返回判断是否接收过通知，directPaySyncBack.getTrade_no()
		//TODO
		BuserOrder buser = findEntityBySQL("select * from t_buser_order where purchaseType='2' and payTradeNo=?", BuserOrder.class, directPaySyncBack.getTrade_no());
		if(buser != null){
			return "success";
		}
		String ret = "fail";
		//2.签名验证
		Map<String,String> checkParams = new HashMap<String,String>();
		checkParams = BeanConverter.toMap(directPaySyncBack);
		if(AlipayNotify.verify(checkParams) == true){
			String tradeNo = directPaySyncBack.getOut_trade_no();
			Integer id = Integer.parseInt(tradeNo.split("_")[1]);
			BuserOrder entity = this.getEntity(BuserOrder.class, id);
			entity.setPayOrderStatus("1");
			entity.setPayTradeNo(directPaySyncBack.getTrade_no());
			entity.setPayNoticeTime(new Date());
			saveOrUpdate(entity);
			
			AgentInfo agentInfo = this.getEntity(AgentInfo.class, entity.getBuserId());
			double blance = entity.getCharge()+agentInfo.getBlance();
			agentInfo.setBlance(blance);
			if(blance >= 0){
				agentInfo.setDebtTime(null);
			}
			saveOrUpdate(agentInfo);
			ret="success";
		}else{
			logger.info("**********支付宝即时到账回调签名失败!***********");
			ret = "fail";
		}		
		return ret;
	}

}
