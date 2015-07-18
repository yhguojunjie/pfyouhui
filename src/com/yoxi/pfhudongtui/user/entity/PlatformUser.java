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

/**   
 * @Title: Entity
 * @Description: PlatformUser
 * @author jwhat generate
 * @date 2015-03-25 19:28:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_user", schema = "")
@SuppressWarnings("serial")
public class PlatformUser extends IdUserDefinedEntity implements java.io.Serializable {
	/**userId*/
	private java.lang.Integer userId;
	/**account*/
	private java.lang.String account;
	/**email*/
	private java.lang.String email;
	/**手机号*/
	private java.lang.String mobile;
	/**手机号是否公众(0不公开,1公开)*/
	private java.lang.String mobileOpen;
	/**qq号码*/
	private java.lang.String qqAccount;
	/**QQ号是否公众(0不公开,1公开)*/
	private java.lang.String qqOpen;
	/**微信账号*/
	private java.lang.String weixinAccount;
	/**微信号是否公众(0不公开,1公开)*/
	private java.lang.String weixinOpen;
	/**呢称*/
	private java.lang.String nickName;
	/**头像url*/
	private java.lang.String headimgUrl;
	/**1普通商家用户，2开发者*/
	private java.lang.String role;
	/**性别(0,男性 1 女性)*/
	private java.lang.String sex;
	/**真实姓名*/
	private java.lang.String realName;
	/**联系地址*/
	private java.lang.String address;
	/**身份证号*/
	private java.lang.String personIdCard;
	/**来源(1,用户自行注册，2 QQ，3微信 4新浪微博 5腾讯微博)*/
	private java.lang.String source;
	/**代币*/
	private java.lang.Double repreCoin;
	/**朋友数*/
	private java.lang.Integer friendNum;
	/**二维码地址*/
	private java.lang.String qcode;
	/**简介描述*/
	private java.lang.String introduce;
	/**已拥有插件数*/
	private java.lang.Integer pluginNum;
	/**省*/
	private java.lang.String province;
	/**市*/
	private java.lang.String city;
	/**区*/
	private java.lang.String district;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
	private java.util.Date updateTime;
	/**密码*/
	private java.lang.String password;
	/**0 正常使用，1禁用*/
	private java.lang.String state;
	/**成交订单数*/
	private java.lang.Integer dealOrederNum;
	/**成交金额*/
	private java.lang.Double dealAmount;
	
	private AgentInfo agentInfo;
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  userId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="userId",nullable=false,precision=10,scale=0)
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  userId
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  account
	 */
	@Column(name ="account",nullable=true,length=150)
	public java.lang.String getAccount(){
		return this.account;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  account
	 */
	public void setAccount(java.lang.String account){
		this.account = account;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  email
	 */
	@Column(name ="email",nullable=true,length=150)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  email
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */
	@Column(name ="mobile",nullable=true,length=15)
	public java.lang.String getMobile(){
		return this.mobile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setMobile(java.lang.String mobile){
		this.mobile = mobile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号是否公众(0不公开,1公开)
	 */
	@Column(name ="mobileOpen",nullable=true,length=1)
	public java.lang.String getMobileOpen(){
		return this.mobileOpen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号是否公众(0不公开,1公开)
	 */
	public void setMobileOpen(java.lang.String mobileOpen){
		this.mobileOpen = mobileOpen;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  qq号码
	 */
	@Column(name ="qqAccount",nullable=true,length=15)
	public java.lang.String getQqAccount(){
		return this.qqAccount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  qq号码
	 */
	public void setQqAccount(java.lang.String qqAccount){
		this.qqAccount = qqAccount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  QQ号是否公众(0不公开,1公开)
	 */
	@Column(name ="qqOpen",nullable=true,length=1)
	public java.lang.String getQqOpen(){
		return this.qqOpen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  QQ号是否公众(0不公开,1公开)
	 */
	public void setQqOpen(java.lang.String qqOpen){
		this.qqOpen = qqOpen;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信账号
	 */
	@Column(name ="weixinAccount",nullable=true,length=20)
	public java.lang.String getWeixinAccount(){
		return this.weixinAccount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信账号
	 */
	public void setWeixinAccount(java.lang.String weixinAccount){
		this.weixinAccount = weixinAccount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信号是否公众(0不公开,1公开)
	 */
	@Column(name ="weixinOpen",nullable=true,length=1)
	public java.lang.String getWeixinOpen(){
		return this.weixinOpen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信号是否公众(0不公开,1公开)
	 */
	public void setWeixinOpen(java.lang.String weixinOpen){
		this.weixinOpen = weixinOpen;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  呢称
	 */
	@Column(name ="nickName",nullable=true,length=100)
	public java.lang.String getNickName(){
		return this.nickName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  呢称
	 */
	public void setNickName(java.lang.String nickName){
		this.nickName = nickName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像url
	 */
	@Column(name ="headimgUrl",nullable=true,length=200)
	public java.lang.String getHeadimgUrl(){
		return this.headimgUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像url
	 */
	public void setHeadimgUrl(java.lang.String headimgUrl){
		this.headimgUrl = headimgUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  1普通商家用户，2开发者
	 */
	@Column(name ="role",nullable=true,length=2)
	public java.lang.String getRole(){
		return this.role;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  1普通商家用户，2开发者
	 */
	public void setRole(java.lang.String role){
		this.role = role;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别(0,男性 1 女性)
	 */
	@Column(name ="sex",nullable=true,length=1)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别(0,男性 1 女性)
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  真实姓名
	 */
	@Column(name ="realName",nullable=true,length=150)
	public java.lang.String getRealName(){
		return this.realName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  真实姓名
	 */
	public void setRealName(java.lang.String realName){
		this.realName = realName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系地址
	 */
	@Column(name ="address",nullable=true,length=150)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号
	 */
	@Column(name ="personIdCard",nullable=true,length=20)
	public java.lang.String getPersonIdCard(){
		return this.personIdCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号
	 */
	public void setPersonIdCard(java.lang.String personIdCard){
		this.personIdCard = personIdCard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  来源(1,用户自行注册，2 QQ，3微信 4新浪微博 5腾讯微博)
	 */
	@Column(name ="source",nullable=true,length=2)
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  来源(1,用户自行注册，2 QQ，3微信 4新浪微博 5腾讯微博)
	 */
	public void setSource(java.lang.String source){
		this.source = source;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  代币
	 */
	@Column(name ="repreCoin",nullable=true,precision=10,scale=2)
	public java.lang.Double getRepreCoin(){
		return this.repreCoin;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  代币
	 */
	public void setRepreCoin(java.lang.Double repreCoin){
		this.repreCoin = repreCoin;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  朋友数
	 */
	@Column(name ="friendNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getFriendNum(){
		return this.friendNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  朋友数
	 */
	public void setFriendNum(java.lang.Integer friendNum){
		this.friendNum = friendNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二维码地址
	 */
	@Column(name ="qcode",nullable=true,length=100)
	public java.lang.String getQcode(){
		return this.qcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二维码地址
	 */
	public void setQcode(java.lang.String qcode){
		this.qcode = qcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简介描述
	 */
	@Column(name ="introduce",nullable=true,length=500)
	public java.lang.String getIntroduce(){
		return this.introduce;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简介描述
	 */
	public void setIntroduce(java.lang.String introduce){
		this.introduce = introduce;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  已拥有插件数
	 */
	@Column(name ="pluginNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getPluginNum(){
		return this.pluginNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  已拥有插件数
	 */
	public void setPluginNum(java.lang.Integer pluginNum){
		this.pluginNum = pluginNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省
	 */
	@Column(name ="province",nullable=true,length=20)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  市
	 */
	@Column(name ="city",nullable=true,length=30)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区
	 */
	@Column(name ="district",nullable=true,length=30)
	public java.lang.String getDistrict(){
		return this.district;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  区
	 */
	public void setDistrict(java.lang.String district){
		this.district = district;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  密码
	 */
	@Column(name ="password",nullable=true,length=150)
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  0 正常使用，1禁用
	 */
	@Column(name ="state",nullable=true,length=1)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  0 正常使用，1禁用
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  成交订单数
	 */
	@Column(name ="dealOrederNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getDealOrederNum(){
		return this.dealOrederNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  成交订单数
	 */
	public void setDealOrederNum(java.lang.Integer dealOrederNum){
		this.dealOrederNum = dealOrederNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  成交金额
	 */
	@Column(name ="dealAmount",nullable=true,precision=10,scale=2)
	public java.lang.Double getDealAmount(){
		return this.dealAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  成交金额
	 */
	public void setDealAmount(java.lang.Double dealAmount){
		this.dealAmount = dealAmount;
	}

	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JoinColumn(name = "agentId",nullable=true)
	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}
	
	
}
