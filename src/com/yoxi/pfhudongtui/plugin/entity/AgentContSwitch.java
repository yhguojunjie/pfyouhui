package com.yoxi.pfhudongtui.plugin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;

/**
 * @Title: Entity
 * @Description: AgentContSwitch
 * @author jwhat generate
 * @date 2015-04-21 17:54:10
 * @version V1.0
 * 
 */
@Entity
@Table(name = "t_agent_cont_switch", schema = "")
@SuppressWarnings("serial")
public class AgentContSwitch extends IdUserDefinedEntity implements java.io.Serializable {
	/** 活动推荐开关 0开启 1关闭 */
	private java.lang.String actRecState;
	/** agentId */
	private java.lang.Integer agentId;
	/* private AgentInfo agentInfo; */
	/** banner推荐开关 0 开启 1关闭 */
	private java.lang.String bannerState;
	/** 经典案例开关 0开启 1关闭 */
	private java.lang.String caseState;
	/** 渠道推荐开关 0开启 1关闭 */
	private java.lang.String channelState;
	/** 常见问题开关 0开启 1关闭 */
	private java.lang.String faqState;

	/** 模板推荐开启状态 0 开启，1关闭 */
	private java.lang.String pluginRecState;

	/** 经典案例内容设置 0使用平台数据，1自己管理数据 **/
	private java.lang.String caseConType;
	/** 渠道内容设置 0使用平台数据，1自己管理数据 **/
	private java.lang.String channelConType;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 活动推荐开关 0开启 1关闭
	 */
	@Column(name = "actRecState", nullable = true, length = 1)
	public java.lang.String getActRecState() {
		return this.actRecState;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer agentId
	 */
	/*
	 * @Id
	 * 
	 * @GenericGenerator(name = "pkGenerator", strategy = "foreign", parameters
	 * = { @Parameter(name = "property", value = "agentInfo") })
	 * 
	 * @GeneratedValue(generator = "pkGenerator")
	 */
	@Id
	@Column(name = "agentId", nullable = false, precision = 10, scale = 0)
	public java.lang.Integer getAgentId() {
		return this.agentId;
	}

	/*
	 * @OneToOne(mappedBy = "agentContSwitch") public AgentInfo getAgentInfo() {
	 * return agentInfo; }
	 */
	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String banner推荐开关 0 开启 1关闭
	 */
	@Column(name = "bannerState", nullable = true, length = 1)
	public java.lang.String getBannerState() {
		return this.bannerState;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 经典案例开关 0开启 1关闭
	 */
	@Column(name = "caseState", nullable = true, length = 1)
	public java.lang.String getCaseState() {
		return this.caseState;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 渠道推荐开关 0开启 1关闭
	 */
	@Column(name = "channelState", nullable = true, length = 1)
	public java.lang.String getChannelState() {
		return this.channelState;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 常见问题开关 0开启 1关闭
	 */
	@Column(name = "faqState", nullable = true, length = 1)
	public java.lang.String getFaqState() {
		return this.faqState;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 模板推荐开启状态 0 开启，1关闭
	 */
	@Column(name = "pluginRecState", nullable = true, length = 1)
	public java.lang.String getPluginRecState() {
		return this.pluginRecState;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 活动推荐开关 0开启 1关闭
	 */
	public void setActRecState(java.lang.String actRecState) {
		this.actRecState = actRecState;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer agentId
	 */
	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	/*
	 * public void setAgentInfo(AgentInfo agentInfo) { this.agentInfo =
	 * agentInfo; }
	 */

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String banner推荐开关 0 开启 1关闭
	 */
	public void setBannerState(java.lang.String bannerState) {
		this.bannerState = bannerState;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 经典案例开关 0开启 1关闭
	 */
	public void setCaseState(java.lang.String caseState) {
		this.caseState = caseState;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 渠道推荐开关 0开启 1关闭
	 */
	public void setChannelState(java.lang.String channelState) {
		this.channelState = channelState;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 常见问题开关 0开启 1关闭
	 */
	public void setFaqState(java.lang.String faqState) {
		this.faqState = faqState;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 模板推荐开启状态 0 开启，1关闭
	 */
	public void setPluginRecState(java.lang.String pluginRecState) {
		this.pluginRecState = pluginRecState;
	}

	@Column(name = "caseConType", nullable = true, length = 1)
	public java.lang.String getCaseConType() {
		return caseConType;
	}

	public void setCaseConType(java.lang.String caseConType) {
		this.caseConType = caseConType;
	}

	@Column(name = "channelConType", nullable = true, length = 1)
	public java.lang.String getChannelConType() {
		return channelConType;
	}

	public void setChannelConType(java.lang.String channelConType) {
		this.channelConType = channelConType;
	}

}
