package com.yoxi.pfhudongtui.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;

/**   
 * @Title: Entity
 * @Description: Channel
 * @author jwhat generate
 * @date 2015-03-31 09:53:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_channel", schema = "")
@SuppressWarnings("serial")
public class Channel extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**用户id(平台用户)*/
	private java.lang.Integer userId;
	/**渠道类型*/
	private java.lang.String type;
	/**渠道名称*/
	private java.lang.String name;
	/**LOGO*/
	private java.lang.String logo;
	/**简介*/
	private java.lang.String introduce;
	/**二维码*/
	private java.lang.String qrcode;
	/**网址*/
	private java.lang.String website;
	/**粉丝数*/
	private java.lang.Integer fansNum;
	/**发布价位 元/条*/
	private java.lang.Double price;
	/**联系人QQ号*/
	private java.lang.String qq;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	/**审核状态 0 未审核，1已审核*/
	private java.lang.String auditState;
	/**审核人id*/
	private java.lang.Integer auditorId;
	/**代理商id*/
	private java.lang.Integer agentId;
	/**顺序*/
	private java.lang.Integer seq;
	/**手机号码**/
	private java.lang.String mobile;
	/***邮箱**/
	private java.lang.String email;
	/**下载链接**/
	private java.lang.String downloadLink;
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
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  用户id(平台用户)
	 */
	@Column(name ="userId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  用户id(平台用户)
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道类型
	 */
	@Column(name ="type",nullable=true,length=3)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道名称
	 */
	@Column(name ="name",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  LOGO
	 */
	@Column(name ="logo",nullable=true,length=100)
	public java.lang.String getLogo(){
		return this.logo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  LOGO
	 */
	public void setLogo(java.lang.String logo){
		this.logo = logo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简介
	 */
	@Column(name ="introduce",nullable=true,length=150)
	public java.lang.String getIntroduce(){
		return this.introduce;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简介
	 */
	public void setIntroduce(java.lang.String introduce){
		this.introduce = introduce;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二维码
	 */
	@Column(name ="qrcode",nullable=true,length=100)
	public java.lang.String getQrcode(){
		return this.qrcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二维码
	 */
	public void setQrcode(java.lang.String qrcode){
		this.qrcode = qrcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  网址
	 */
	@Column(name ="website",nullable=true,length=40)
	public java.lang.String getWebsite(){
		return this.website;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  网址
	 */
	public void setWebsite(java.lang.String website){
		this.website = website;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  粉丝数
	 */
	@Column(name ="fansNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getFansNum(){
		return this.fansNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  粉丝数
	 */
	public void setFansNum(java.lang.Integer fansNum){
		this.fansNum = fansNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  发布价位 元/条
	 */
	@Column(name ="price",nullable=true,precision=10,scale=2)
	public java.lang.Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  发布价位 元/条
	 */
	public void setPrice(java.lang.Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人QQ号
	 */
	@Column(name ="qq",nullable=true,length=14)
	public java.lang.String getQq(){
		return this.qq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人QQ号
	 */
	public void setQq(java.lang.String qq){
		this.qq = qq;
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
	 *@return: java.util.Date  更新时间
	 */
	@Column(name ="updateTime",nullable=true)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核状态 0 未审核，1已审核
	 */
	@Column(name ="auditState",nullable=true,length=1)
	public java.lang.String getAuditState(){
		return this.auditState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核状态 0 未审核，1已审核
	 */
	public void setAuditState(java.lang.String auditState){
		this.auditState = auditState;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  审核人id
	 */
	@Column(name ="auditorId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAuditorId(){
		return this.auditorId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  审核人id
	 */
	public void setAuditorId(java.lang.Integer auditorId){
		this.auditorId = auditorId;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  顺序
	 */
	@Column(name ="seq",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSeq(){
		return this.seq;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  顺序
	 */
	public void setSeq(java.lang.Integer seq){
		this.seq = seq;
	}
	@Column(name ="mobile",nullable=true,length=20)
	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	@Column(name ="email",nullable=true,length=20)
	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	@Column(name ="downloadLink",nullable=true,length=50)
	public java.lang.String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(java.lang.String downloadLink) {
		this.downloadLink = downloadLink;
	}
	
	
}
