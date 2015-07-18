package com.yoxi.pfhudongtui.plugin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;

/**
 * @Title: Entity
 * @Description: AgentBanner
 * @author jwhat generate
 * @date 2015-04-21 11:53:23
 * @version V1.0
 * 
 */
@Entity
@Table(name = "t_agent_banner", schema = "")
@SuppressWarnings("serial")
public class AgentBanner extends IdUserDefinedEntity implements java.io.Serializable {
	/** 代理商id */
	private java.lang.Integer agentId;
	/** auditorId */
	private java.lang.Integer auditorId;
	/** auditstate */
	private java.lang.String auditstate;
	/** 审核时间 */
	private java.util.Date auditTime;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 隐藏状态 */
	private java.lang.String hideState;
	/** id */
	private java.lang.Integer id;
	/** pc版链接 */
	private java.lang.String pcLink;
	/** pc版图片 */
	private java.lang.String pcLogo;
	/** 平台设置的bannerId */
	private java.lang.Integer sbannerId;

	/** 顺序 */
	private java.lang.Integer seq;

	/** (0 系统同步，1代理商自己添加) */
	private java.lang.String type;
	/** 修改时间 */
	private java.util.Date updateTime;

	/** 微信版链接 */
	private java.lang.String wxLink;

	/** 微信版图片 */
	private java.lang.String wxLogo;

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 代理商id
	 */
	@Column(name = "agentId", nullable = true, precision = 10, scale = 0)
	public java.lang.Integer getAgentId() {
		return this.agentId;
	}

	public java.lang.Integer getAuditorId() {
		return auditorId;
	}

	public java.lang.String getAuditstate() {
		return auditstate;
	}

	public java.util.Date getAuditTime() {
		return auditTime;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	@Column(name = "createTime", nullable = true)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public java.lang.String getHideState() {
		return hideState;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer id
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, precision = 10, scale = 0)
	public java.lang.Integer getId() {
		return this.id;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String pc版链接
	 */
	@Column(name = "pcLink", nullable = true, length = 100)
	public java.lang.String getPcLink() {
		return this.pcLink;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String pc版图片
	 */
	@Column(name = "pcLogo", nullable = true, length = 100)
	public java.lang.String getPcLogo() {
		return this.pcLogo;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 平台设置的bannerId
	 */
	@Column(name = "sbannerId", nullable = true, precision = 10, scale = 0)
	public java.lang.Integer getSbannerId() {
		return this.sbannerId;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 顺序
	 */
	@Column(name = "seq", nullable = true, precision = 10, scale = 0)
	public java.lang.Integer getSeq() {
		return this.seq;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String (0 系统同步，1代理商自己添加)
	 */
	@Column(name = "type", nullable = true, length = 1)
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 修改时间
	 */
	@Column(name = "updateTime", nullable = true)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 微信版链接
	 */
	@Column(name = "wxLink", nullable = true, length = 150)
	public java.lang.String getWxLink() {
		return this.wxLink;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 微信版图片
	 */
	@Column(name = "wxLogo", nullable = true, length = 100)
	public java.lang.String getWxLogo() {
		return this.wxLogo;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 代理商id
	 */
	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setAuditorId(java.lang.Integer auditorId) {
		this.auditorId = auditorId;
	}

	public void setAuditstate(java.lang.String auditstate) {
		this.auditstate = auditstate;
	}

	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setHideState(java.lang.String hideState) {
		this.hideState = hideState;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String pc版链接
	 */
	public void setPcLink(java.lang.String pcLink) {
		this.pcLink = pcLink;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String pc版图片
	 */
	public void setPcLogo(java.lang.String pcLogo) {
		this.pcLogo = pcLogo;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 平台设置的bannerId
	 */
	public void setSbannerId(java.lang.Integer sbannerId) {
		this.sbannerId = sbannerId;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 顺序
	 */
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String (0 系统同步，1代理商自己添加)
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 修改时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 微信版链接
	 */
	public void setWxLink(java.lang.String wxLink) {
		this.wxLink = wxLink;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 微信版图片
	 */
	public void setWxLogo(java.lang.String wxLogo) {
		this.wxLogo = wxLogo;
	}
}
