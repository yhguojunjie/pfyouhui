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
 * @Description: Banner
 * @author jwhat generate
 * @date 2015-04-20 11:28:07
 * @version V1.0
 * 
 */
@Entity
@Table(name = "t_banner", schema = "")
@SuppressWarnings("serial")
public class Banner extends IdUserDefinedEntity implements java.io.Serializable {
	/** 创建时间 */
	private java.util.Date createTime;
	/** id */
	private java.lang.Integer id;
	/** 链接类型 */
	private java.lang.String linkType;

	/** 对象id */
	private java.lang.Integer objId;
	/** pc版链接 */
	private java.lang.String pcLink;
	/** pc版本Logo */
	private java.lang.String pcLogo;

	/** 顺序 */
	private java.lang.Integer seq;
	/** 修改时间 */
	private java.util.Date updateTime;

	/** wx版链接 */
	private java.lang.String wxLink;
	/** wx版Logo */
	private java.lang.String wxLogo;

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

	public java.lang.String getLinkType() {
		return linkType;
	}

	public java.lang.Integer getObjId() {
		return objId;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String pc版链接
	 */
	@Column(name = "pcLink", nullable = true, length = 150)
	public java.lang.String getPcLink() {
		return this.pcLink;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String pc版本Logo
	 */
	@Column(name = "pcLogo", nullable = true, length = 150)
	public java.lang.String getPcLogo() {
		return this.pcLogo;
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
	 * @return: java.util.Date 修改时间
	 */
	@Column(name = "updateTime", nullable = true)
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String wx版链接
	 */
	@Column(name = "wxLink", nullable = true, length = 150)
	public java.lang.String getWxLink() {
		return this.wxLink;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String wx版Logo
	 */
	@Column(name = "wxLogo", nullable = true, length = 150)
	public java.lang.String getWxLogo() {
		return this.wxLogo;
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
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setLinkType(java.lang.String linkType) {
		this.linkType = linkType;
	}

	public void setObjId(java.lang.Integer objId) {
		this.objId = objId;
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
	 * @param: java.lang.String pc版本Logo
	 */
	public void setPcLogo(java.lang.String pcLogo) {
		this.pcLogo = pcLogo;
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
	 * @param: java.util.Date 修改时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String wx版链接
	 */
	public void setWxLink(java.lang.String wxLink) {
		this.wxLink = wxLink;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String wx版Logo
	 */
	public void setWxLogo(java.lang.String wxLogo) {
		this.wxLogo = wxLogo;
	}
}
