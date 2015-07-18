package com.yoxi.pfhudongtui.vo.plugin;

/**
 * 
 * 品牌VO
 * 
 * @author gjj
 * 
 *         2015-4-27
 */
public class BrandVO {
	/** 创建时间 */
	private java.util.Date createTime;
	/** 隐藏状态 0显示 ，1隐藏 */
	private java.lang.String hideState;
	/** id */
	private java.lang.Integer id;
	/** logo */
	private java.lang.String logo;
	/** nickName */
	private java.lang.String nickName;
	/** 顺序 */
	private java.lang.Integer seq;
	/** 更新时间 */
	private java.util.Date updateTime;
	/** 用户id */
	private java.lang.Integer userId;

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public java.lang.String getHideState() {
		return hideState;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public java.lang.String getLogo() {
		return logo;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public java.lang.Integer getSeq() {
		return seq;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setHideState(java.lang.String hideState) {
		this.hideState = hideState;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
}
