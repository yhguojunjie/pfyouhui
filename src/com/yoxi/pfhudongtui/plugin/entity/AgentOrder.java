package com.yoxi.pfhudongtui.plugin.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;

/**   
 * @Title: Entity
 * @Description: AgentOrder
 * @author jwhat generate
 * @date 2015-03-27 09:35:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_agent_order", schema = "")
@SuppressWarnings("serial")
public class AgentOrder extends IdUserDefinedEntity implements java.io.Serializable {
	/**定单Id*/
	private java.lang.Integer id;
	/**产品种类,比如插件、包月等 插件1*/
	private java.lang.String productType;
	/**实际支付金额*/
	private java.lang.Double charge;
	/**代币支付金额*/
	private java.lang.Double repreCoin;
	/**购买人userId*/
	private java.lang.Integer purchaserId;
	/**支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式)*/
	private java.lang.String purchaseType;
	/**下单时间(表记录生成时间)*/
	private java.util.Date orderTime;
	/**支付状态(0：未支付、1：已支付、2：支付失败)*/
	private java.lang.String payOrderStatus;
	/**支付交易号(第三方订单号)*/
	private java.lang.String payTradeNo;
	/**支付通知时间*/
	private java.util.Date payNoticeTime;
	/**平台来源(1PC,2,微信)*/
	private java.lang.String sourceType;
	/**产品使用开始时间*/
	private java.util.Date startDate;
	/**产品使用结束时间*/
	private java.util.Date endDate;
	/**代理商定价*/
	private java.lang.Double salePrice;
	/**成本价*/
	private java.lang.Double costPrice;
	/**购买数量*/
	private java.lang.Integer buyNum;
	/**0 进行中，1关闭*/
	private java.lang.String tradeState;
	/**用户插件id,续费购买时此字段有值**/
	private java.lang.Integer userPluginId;
	/**收款账号**/
	private java.lang.String sellerAccount;
	
	private Plugin plugin;
	
	private PlatformUser platformUser;
	
	private AgentInfo agentInfo;
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  定单Id
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id",nullable=false,precision=10,scale=0)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  定单Id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品种类,比如插件、包月等 插件1
	 */
	@Column(name ="productType",nullable=true,length=3)
	public java.lang.String getProductType(){
		return this.productType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品种类,比如插件、包月等 插件1
	 */
	public void setProductType(java.lang.String productType){
		this.productType = productType;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  实际支付金额
	 */
	@Column(name ="charge",nullable=true,precision=8,scale=2)
	public java.lang.Double getCharge(){
		return this.charge;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  实际支付金额
	 */
	public void setCharge(java.lang.Double charge){
		this.charge = charge;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  代币支付金额
	 */
	@Column(name ="repreCoin",nullable=true,precision=8,scale=2)
	public java.lang.Double getRepreCoin(){
		return this.repreCoin;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  代币支付金额
	 */
	public void setRepreCoin(java.lang.Double repreCoin){
		this.repreCoin = repreCoin;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  购买人userId
	 */
	@Column(name ="purchaserId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getPurchaserId(){
		return this.purchaserId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  购买人userId
	 */
	public void setPurchaserId(java.lang.Integer purchaserId){
		this.purchaserId = purchaserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式)
	 */
	@Column(name ="purchaseType",nullable=true,length=3)
	public java.lang.String getPurchaseType(){
		return this.purchaseType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式)
	 */
	public void setPurchaseType(java.lang.String purchaseType){
		this.purchaseType = purchaseType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下单时间(表记录生成时间)
	 */
	@Column(name ="orderTime",nullable=true)
	public java.util.Date getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下单时间(表记录生成时间)
	 */
	public void setOrderTime(java.util.Date orderTime){
		this.orderTime = orderTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付状态(0：未支付、1：已支付、2：支付失败)
	 */
	@Column(name ="payOrderStatus",nullable=true,length=1)
	public java.lang.String getPayOrderStatus(){
		return this.payOrderStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付状态(0：未支付、1：已支付、2：支付失败)
	 */
	public void setPayOrderStatus(java.lang.String payOrderStatus){
		this.payOrderStatus = payOrderStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付交易号(第三方订单号)
	 */
	@Column(name ="payTradeNo",nullable=true,length=50)
	public java.lang.String getPayTradeNo(){
		return this.payTradeNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付交易号(第三方订单号)
	 */
	public void setPayTradeNo(java.lang.String payTradeNo){
		this.payTradeNo = payTradeNo;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  支付通知时间
	 */
	@Column(name ="payNoticeTime",nullable=true)
	public java.util.Date getPayNoticeTime(){
		return this.payNoticeTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  支付通知时间
	 */
	public void setPayNoticeTime(java.util.Date payNoticeTime){
		this.payNoticeTime = payNoticeTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  平台来源(1PC,2,微信)
	 */
	@Column(name ="sourceType",nullable=true,length=2)
	public java.lang.String getSourceType(){
		return this.sourceType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  平台来源(1PC,2,微信)
	 */
	public void setSourceType(java.lang.String sourceType){
		this.sourceType = sourceType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  产品使用开始时间
	 */
	@Column(name ="startDate",nullable=true)
	public java.util.Date getStartDate(){
		return this.startDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  产品使用开始时间
	 */
	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  产品使用结束时间
	 */
	@Column(name ="endDate",nullable=true)
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  产品使用结束时间
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  代理商定价
	 */
	@Column(name ="salePrice",nullable=true,precision=8,scale=2)
	public java.lang.Double getSalePrice(){
		return this.salePrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  代理商定价
	 */
	public void setSalePrice(java.lang.Double salePrice){
		this.salePrice = salePrice;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  成本价
	 */
	@Column(name ="costPrice",nullable=true,precision=8,scale=2)
	public java.lang.Double getCostPrice(){
		return this.costPrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  成本价
	 */
	public void setCostPrice(java.lang.Double costPrice){
		this.costPrice = costPrice;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  购买数量
	 */
	@Column(name ="buyNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBuyNum(){
		return this.buyNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  购买数量
	 */
	public void setBuyNum(java.lang.Integer buyNum){
		this.buyNum = buyNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0 进行中，1关闭
	 */
	@Column(name ="tradeState",nullable=true,length=1)
	public java.lang.String getTradeState(){
		return this.tradeState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0 进行中，1关闭
	 */
	public void setTradeState(java.lang.String tradeState){
		this.tradeState = tradeState;
	}
	@ManyToOne(fetch = FetchType.EAGER,optional=true)
	@JoinColumn(name = "productId")
	public Plugin getPlugin() {
		return plugin;
	}

	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	@ManyToOne(fetch = FetchType.EAGER,optional=true)
	@JoinColumn(name = "userId")
	public PlatformUser getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
	}
	@Column(name ="userPluginId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getUserPluginId() {
		return userPluginId;
	}

	public void setUserPluginId(java.lang.Integer userPluginId) {
		this.userPluginId = userPluginId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agentId")
	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}
	
	@Column(name ="sellerAccount",nullable=true,length=60)
	public java.lang.String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(java.lang.String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}
	
	
}
