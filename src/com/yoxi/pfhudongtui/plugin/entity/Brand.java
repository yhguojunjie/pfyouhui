package com.yoxi.pfhudongtui.plugin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;

/**
 * @Title: Entity
 * @Description: Brand
 * @author jwhat generate
 * @date 2015-04-23 16:29:07
 * @version V1.0
 * 
 */
@Entity
@Table(name = "t_brand", schema = "")
@SuppressWarnings("serial")
public class Brand extends IdUserDefinedEntity implements java.io.Serializable {
	/** 创建时间 */
	private java.util.Date createTime;
	/** 隐藏状态 0显示 ，1隐藏 */
	private java.lang.String hideState;
	/** id */
	private java.lang.Integer id;
	/** logo */
	private java.lang.String logo;
	private PlatformUser platformUser;
	/** 顺序 */
	private java.lang.Integer seq;
	/** 更新时间 */
	private java.util.Date updateTime;
	/** 用户id */
	private java.lang.Integer userId;

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	@Column(name = "createTime", nullable = true)
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 隐藏状态 0显示 ，1隐藏
	 */
	@Column(name = "hideState", nullable = true, length = 1)
	public java.lang.String getHideState() {
		return this.hideState;
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
	 * @return: java.lang.String logo
	 */
	@Column(name = "logo", nullable = true, length = 100)
	public java.lang.String getLogo() {
		return this.logo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	public PlatformUser getPlatformUser() {
		return platformUser;
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
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 更新时间
	 */
	@Column(name = "updateTime", nullable = true)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 用户id
	 */
	@Column(name = "userId", nullable = true, precision = 10, scale = 0)
	public java.lang.Integer getUserId() {
		return this.userId;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 隐藏状态 0显示 ，1隐藏
	 */
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
	 * @param: java.lang.String logo
	 */
	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
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
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 用户id
	 */
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
}
