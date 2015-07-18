package com.yoxi.pfhudongtui.plugin.entity;

import java.util.Date;

public class AgentOrderEntity {

	/**订单ID**/
	private Integer id;
	/**插件名称**/
	private String pluginName;
	/**用户**/
	private String userName;
	/**代理商定价*/
	private Integer salePrice;
	/**购买数量**/
	private String  buyNum;
	/**交易金额**/
	private String amount;
	/**订单时间**/
	private Date orderTime;
	/**用户id**/
	private Integer userId;
	/**插件图标**/
	private String pluginIcon;
	/**交易状态**/
	private String tradeState;
	/**支付状态**/
	private String payOrderStatus;
	/**代理商**/
	private String companyName;
	/**1:购买新模板2模板续费购买**/
	private String productType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public Integer getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPluginIcon() {
		return pluginIcon;
	}
	public void setPluginIcon(String pluginIcon) {
		this.pluginIcon = pluginIcon;
	}
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	public String getPayOrderStatus() {
		return payOrderStatus;
	}
	public void setPayOrderStatus(String payOrderStatus) {
		this.payOrderStatus = payOrderStatus;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}

	
	
}
