package com.yoxi.pfhudongtui.pay.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;

/**   
 * @Title: Entity
 * @Description: BuserOrder
 * @author jwhat generate
 * @date 2015-04-01 11:37:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_buser_order", schema = "")
@SuppressWarnings("serial")
public class BuserOrder extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**用户id，这里指后台用户即代理商用户id*/
	private java.lang.Integer buserId;
	/**支付金额*/
	private java.lang.Double charge;
	/**支付类型(1 代币，2支付宝WEB，3支付宝WAP，4银联WEB，5银联WAP，6微信支付，7代码与第三支付方式)*/
	private java.lang.String purchaseType;
	/**支付状态(0：未支付、1：已支付、2：支付失败)*/
	private java.lang.String payOrderStatus;
	/**支付交易号(第三方订单号)*/
	private java.lang.String payTradeNo;
	/**支付通知时间*/
	private java.util.Date payNoticeTime;
	/**下单时间*/
	private java.util.Date orderTime;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id",nullable=false,precision=10,scale=0)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户id，这里指后台用户即代理商用户id
	 */
	@Column(name ="buserId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBuserId(){
		return this.buserId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户id，这里指后台用户即代理商用户id
	 */
	public void setBuserId(java.lang.Integer buserId){
		this.buserId = buserId;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  支付金额
	 */
	@Column(name ="charge",nullable=true,precision=10,scale=2)
	public java.lang.Double getCharge(){
		return this.charge;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  支付金额
	 */
	public void setCharge(java.lang.Double charge){
		this.charge = charge;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下单时间
	 */
	@Column(name ="orderTime",nullable=true)
	public java.util.Date getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下单时间
	 */
	public void setOrderTime(java.util.Date orderTime){
		this.orderTime = orderTime;
	}
}
