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
import com.yoxi.jgframework.system.entity.TSBaseUser;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;

/**   
 * @Title: Entity
 * @Description: PluginAgent
 * @author jwhat generate
 * @date 2015-03-20 12:17:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_plugin_agent", schema = "")
@SuppressWarnings("serial")
public class PluginAgent extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**销售价格*/
	private java.lang.Double salePrice;
	/**0 上架，1下架*/
	private java.lang.String onlineState;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
	private java.util.Date updateTime;
	
	private Plugin plugin;
	private AgentInfo agentInfo;
	
	
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "agentId")
	public AgentInfo getAgentInfo() {
		return agentInfo;
	}


	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pluginId")
	public Plugin getPlugin() {
		return plugin;
	}



	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
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
	 *@return: java.lang.Double  销售价格
	 */
	@Column(name ="salePrice",nullable=true,precision=10,scale=2)
	public java.lang.Double getSalePrice(){
		return this.salePrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  销售价格
	 */
	public void setSalePrice(java.lang.Double salePrice){
		this.salePrice = salePrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0 上架，1下架
	 */
	@Column(name ="onlineState",nullable=true,length=1)
	public java.lang.String getOnlineState(){
		return this.onlineState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0 上架，1下架
	 */
	public void setOnlineState(java.lang.String onlineState){
		this.onlineState = onlineState;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="createTime",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="updateTime",nullable=true)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	
}
