package com.yoxi.pfhudongtui.vo.user;

/**
 * 
 * 用户VO
 * 
 * @author gjj
 * 
 *         2015-4-27
 */
public class UserVO {
	/** account */
	private java.lang.String account;
	/** 联系地址 */
	private java.lang.String address;
	/** 所属代理商id */
	private java.lang.Integer agentId;
	/** 市 */
	private java.lang.String city;
	/** 插件数（在用） */
	private java.lang.Integer countNum;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 成交金额 */
	private java.lang.Double dealAmount;
	/** 成交订单数 */
	private java.lang.Integer dealOrederNum;
	/** 区 */
	private java.lang.String district;
	/** email */
	private java.lang.String email;
	/** 朋友数 */
	private java.lang.Integer friendNum;
	/** 头像url */
	private java.lang.String headimgUrl;
	/** 简介描述 */
	private java.lang.String introduce;
	/** 手机号 */
	private java.lang.String mobile;
	/** 手机号是否公众(0不公开,1公开) */
	private java.lang.String mobileOpen;
	/** 呢称 */
	private java.lang.String nickName;
	/** 密码 */
	private java.lang.String password;
	/** 身份证号 */
	private java.lang.String personIdCard;
	/** 已拥有插件数 */
	private java.lang.Integer pluginNum;
	/** 省 */
	private java.lang.String province;
	/** 二维码地址 */
	private java.lang.String qcode;
	/** qq号码 */
	private java.lang.String qqAccount;
	/** qq号是否公众(0不公开,1公开) */
	private java.lang.String qqOpen;
	/** 真实姓名 */
	private java.lang.String realName;
	/** 代币 */
	private java.lang.Double repreCoin;
	/** 1普通商家用户，2开发者 */
	private java.lang.String role;

	/** 性别(0,男性 1 女性) */
	private java.lang.String sex;

	/** 来源(1,用户自行注册，2 QQ，3微信 4新浪微博 5腾讯微博) */
	private java.lang.String source;

	/** 0 正常使用，1禁用 */
	private java.lang.String state;
	/** 修改时间 */
	private java.util.Date updateTime;
	/** userId */
	private java.lang.Integer userId;
	/** 微信账号 */
	private java.lang.String weixinAccount;
	/** 微信号是否公众(0不公开,1公开) */
	private java.lang.String weixinOpen;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String account
	 */
	public java.lang.String getAccount() {
		return this.account;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 联系地址
	 */
	public java.lang.String getAddress() {
		return this.address;
	}

	public java.lang.Integer getAgentId() {
		return agentId;
	}

	public java.lang.String getCity() {
		return city;
	}

	public java.lang.Integer getCountNum() {
		return countNum;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public java.lang.Double getDealAmount() {
		return dealAmount;
	}

	public java.lang.Integer getDealOrederNum() {
		return dealOrederNum;
	}

	public java.lang.String getDistrict() {
		return district;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String email
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 朋友数
	 */
	public java.lang.Integer getFriendNum() {
		return this.friendNum;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 头像url
	 */
	public java.lang.String getHeadimgUrl() {
		return this.headimgUrl;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 简介描述
	 */
	public java.lang.String getIntroduce() {
		return this.introduce;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 手机号
	 */
	public java.lang.String getMobile() {
		return this.mobile;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 手机号是否公众(0不公开,1公开)
	 */
	public java.lang.String getMobileOpen() {
		return this.mobileOpen;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 呢称
	 */
	public java.lang.String getNickName() {
		return this.nickName;
	}

	public java.lang.String getPassword() {
		return password;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 身份证号
	 */
	public java.lang.String getPersonIdCard() {
		return this.personIdCard;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 已拥有插件数
	 */
	public java.lang.Integer getPluginNum() {
		return this.pluginNum;
	}

	public java.lang.String getProvince() {
		return province;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 二维码地址
	 */
	public java.lang.String getQcode() {
		return this.qcode;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String qq号码
	 */
	public java.lang.String getQqAccount() {
		return this.qqAccount;
	}

	public java.lang.String getQqOpen() {
		return qqOpen;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 真实姓名
	 */
	public java.lang.String getRealName() {
		return this.realName;
	}

	/**
	 * 方法: 取得java.lang.Double
	 * 
	 * @return: java.lang.Double 代币
	 */
	public java.lang.Double getRepreCoin() {
		return this.repreCoin;
	}

	public java.lang.String getRole() {
		return role;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 性别(0,男性 1 女性)
	 */
	public java.lang.String getSex() {
		return this.sex;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 来源(1,用户自行注册，2 QQ，3微信 4新浪微博 5腾讯微博)
	 */
	public java.lang.String getSource() {
		return this.source;
	}

	public java.lang.String getState() {
		return state;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 修改时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer userId
	 */
	public java.lang.Integer getUserId() {
		return this.userId;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 微信账号
	 */
	public java.lang.String getWeixinAccount() {
		return this.weixinAccount;
	}

	public java.lang.String getWeixinOpen() {
		return weixinOpen;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String account
	 */
	public void setAccount(java.lang.String account) {
		this.account = account;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 联系地址
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public void setAgentId(java.lang.Integer agentId) {
		this.agentId = agentId;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public void setCountNum(java.lang.Integer countNum) {
		this.countNum = countNum;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public void setDealAmount(java.lang.Double dealAmount) {
		this.dealAmount = dealAmount;
	}

	public void setDealOrederNum(java.lang.Integer dealOrederNum) {
		this.dealOrederNum = dealOrederNum;
	}

	public void setDistrict(java.lang.String district) {
		this.district = district;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String email
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 朋友数
	 */
	public void setFriendNum(java.lang.Integer friendNum) {
		this.friendNum = friendNum;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 头像url
	 */
	public void setHeadimgUrl(java.lang.String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 简介描述
	 */
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 手机号
	 */
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 手机号是否公众(0不公开,1公开)
	 */
	public void setMobileOpen(java.lang.String mobileOpen) {
		this.mobileOpen = mobileOpen;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 呢称
	 */
	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 身份证号
	 */
	public void setPersonIdCard(java.lang.String personIdCard) {
		this.personIdCard = personIdCard;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer 已拥有插件数
	 */
	public void setPluginNum(java.lang.Integer pluginNum) {
		this.pluginNum = pluginNum;
	}

	public void setProvince(java.lang.String province) {
		this.province = province;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 二维码地址
	 */
	public void setQcode(java.lang.String qcode) {
		this.qcode = qcode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String qq号码
	 */
	public void setQqAccount(java.lang.String qqAccount) {
		this.qqAccount = qqAccount;
	}

	public void setQqOpen(java.lang.String qqOpen) {
		this.qqOpen = qqOpen;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 真实姓名
	 */
	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}

	/**
	 * 方法: 设置java.lang.Double
	 * 
	 * @param: java.lang.Double 代币
	 */
	public void setRepreCoin(java.lang.Double repreCoin) {
		this.repreCoin = repreCoin;
	}

	public void setRole(java.lang.String role) {
		this.role = role;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 性别(0,男性 1 女性)
	 */
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 来源(1,用户自行注册，2 QQ，3微信 4新浪微博 5腾讯微博)
	 */
	public void setSource(java.lang.String source) {
		this.source = source;
	}

	public void setState(java.lang.String state) {
		this.state = state;
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
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer userId
	 */
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 微信账号
	 */
	public void setWeixinAccount(java.lang.String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}

	public void setWeixinOpen(java.lang.String weixinOpen) {
		this.weixinOpen = weixinOpen;
	}
}
