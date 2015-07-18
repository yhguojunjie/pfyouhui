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
 * @Description: Contactus
 * @author jwhat generate
 * @date 2015-04-08 15:20:56
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_contactus", schema = "")
@SuppressWarnings("serial")
public class Contactus extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**客服**/
	private java.lang.String content;
	/**代理商id*/
	private java.lang.Integer agentId;
	/**提交时间*/
	private java.util.Date applyTime;
	/**0 未审核，1审核通过*/
	private java.lang.String auditState;
	/**审核时间*/
	private java.util.Date auditTime;
	/**0 未启用，1启用*/
	private java.lang.String status;
	
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
	@Column(name ="content",nullable=true,length=300)
	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  代理商id
	 */
	@Column(name ="agentId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAgentId(){
		return this.agentId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  代理商id
	 */
	public void setAgentId(java.lang.Integer agentId){
		this.agentId = agentId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  提交时间
	 */
	@Column(name ="applyTime",nullable=true)
	public java.util.Date getApplyTime(){
		return this.applyTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  提交时间
	 */
	public void setApplyTime(java.util.Date applyTime){
		this.applyTime = applyTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0 未审核，1审核通过
	 */
	@Column(name ="auditState",nullable=true,length=1)
	public java.lang.String getAuditState(){
		return this.auditState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0 未审核，1审核通过
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0 未启用，1启用
	 */
	@Column(name ="status",nullable=true,length=1)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0 未启用，1启用
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
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
