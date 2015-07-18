package com.yoxi.pfhudongtui.user.entity;

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
import com.yoxi.jgframework.system.entity.TSBaseUser;

/**   
 * @Title: Entity
 * @Description: AgentWithdraw
 * @author jwhat generate
 * @date 2015-03-28 17:36:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_agent_withdraw", schema = "")
@SuppressWarnings("serial")
public class AgentWithdraw extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**提现金额*/
	private java.lang.Double cash;
	/**0 支付宝，1银行账号*/
	private java.lang.String cashType;
	/**申请时间*/
	private java.util.Date applyTime;
	/**审核状态(0未处理，1处理)*/
	private java.lang.String auditState;
	/**审核时间*/
	private java.util.Date auditTime;
	
	private AgentInfo agentInfo;
	
	private TSBaseUser tSBaseUser;
	
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  提现金额
	 */
	@Column(name ="cash",nullable=true,precision=10,scale=2)
	public java.lang.Double getCash(){
		return this.cash;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  提现金额
	 */
	public void setCash(java.lang.Double cash){
		this.cash = cash;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0 支付宝，1银行账号
	 */
	@Column(name ="cashType",nullable=true,length=1)
	public java.lang.String getCashType(){
		return this.cashType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0 支付宝，1银行账号
	 */
	public void setCashType(java.lang.String cashType){
		this.cashType = cashType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请时间
	 */
	@Column(name ="applyTime",nullable=true)
	public java.util.Date getApplyTime(){
		return this.applyTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请时间
	 */
	public void setApplyTime(java.util.Date applyTime){
		this.applyTime = applyTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核状态(0未处理，1处理)
	 */
	@Column(name ="auditState",nullable=true,length=1)
	public java.lang.String getAuditState(){
		return this.auditState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核状态(0未处理，1处理)
	 */
	public void setAuditState(java.lang.String auditState){
		this.auditState = auditState;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审核时间
	 */
	@Column(name ="auditTime",nullable=true)
	public java.util.Date getAuditTime(){
		return this.auditTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审核时间
	 */
	public void setAuditTime(java.util.Date auditTime){
		this.auditTime = auditTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "applyUserId")
	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER,optional=true)
	@JoinColumn(name = "auditUserId",nullable=true)
	public TSBaseUser gettSBaseUser() {
		return tSBaseUser;
	}

	public void settSBaseUser(TSBaseUser tSBaseUser) {
		this.tSBaseUser = tSBaseUser;
	}
	
	
}
