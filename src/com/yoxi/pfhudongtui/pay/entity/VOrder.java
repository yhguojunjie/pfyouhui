package com.yoxi.pfhudongtui.pay.entity;

import java.io.Serializable;

/**
 * 
 * 下单信息
 *
 * @author wql
 *
 * @Date 2015年4月1日
 *
 */
public class VOrder implements Serializable {

	private static final long serialVersionUID = -3165622472968735655L;
	/**定单id*/
	private Integer orderId;
	/**产品名称*/
	private String productName;
	/**用户id*/
	private Integer buserId;
	/**金额*/
	private Double charge;
	/**支付类型*/
	private String purchaseType;
	/**来源*/
	private String sourceType;
	/**鉴权串*/
	private String authStr;
	/**异步回调地址*/
	private String notifyUrl;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getBuserId() {
		return buserId;
	}
	public void setBuserId(Integer buserId) {
		this.buserId = buserId;
	}
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getAuthStr() {
		return authStr;
	}
	public void setAuthStr(String authStr) {
		this.authStr = authStr;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
}
