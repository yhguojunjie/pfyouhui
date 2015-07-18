package com.yoxi.pfhudongtui.vo.plugin;

/**
 * @Title: Entity
 * @Description: AgentBrand
 * @author jwhat generate
 * @date 2015-04-23 16:35:11
 * @version V1.0
 * 
 */
public class AgentBrandVO {
	/** 代理商id */
	private java.lang.Integer agentId;

	/** 创建时间 */
	private java.util.Date createTime;

	/** 隐藏状态 0显示 ，1隐藏 */
	private java.lang.String hideState;

	/** id */
	private java.lang.Integer id;

	/** 手动修改的品牌logo */
	private java.lang.String logo;

	/** 手动修改的品牌名称 */
	private java.lang.String nickName;

	/** 顺序 */
	private java.lang.Integer seq;

	/** 0 系统设置，1代理商设置 */
	private java.lang.String type;

	/** 更新时间 */
	private java.util.Date updateTime;

	/** 用户id(品牌用户id) */
	private java.lang.Integer userId;

	public java.lang.Integer getAgentId() {
		return agentId;
	}

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

	public java.lang.String getType() {
		return type;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
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
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
}
