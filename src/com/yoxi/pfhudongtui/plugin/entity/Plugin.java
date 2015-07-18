package com.yoxi.pfhudongtui.plugin.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;
import com.yoxi.jgframework.system.entity.TSCodeDetail;

/**   
 * @Title: Entity
 * @Description: Plugin
 * @author jwhat generate
 * @date 2015-03-18 15:48:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_plugin", schema = "")
@SuppressWarnings("serial")
public class Plugin extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**插件名称*/
	private java.lang.String name;
	/**开发者用户id(为平台用户id)*/
	private java.lang.Integer userId;
	/**插件图标*/
	private java.lang.String icon;
	/**插件描述*/
	private java.lang.String description;
	/**演示地址*/
	private java.lang.String showUrl;
	/**版本信息*/
	private java.lang.String version;
	/**版权信息*/
	private java.lang.String copyright;
	/**服务器路径未带参数*/
	private java.lang.String serverUrl;
	/**已购买人数*/
	private java.lang.Integer buyNum;
	/**试用总人数*/
	private java.lang.Integer tryoutNum;
	/**价格*/
	private java.lang.Double price;
	/**优惠价格*/
	private java.lang.Double promPrice;
	/**插件类型(1 即插即用型，2用户定制)*/
	private java.lang.String type;
	/**使用说明*/
	private java.lang.String guide;
	/**有效期(以月为单位)*/
	private java.lang.Integer valid;
	/**源代码文件地址*/
	private java.lang.String filePath;
	/**视频录像地址*/
	private java.lang.String videoUrl;
	/**新增活动地址*/
	private java.lang.String actAddUrl;
	/**编辑活动地址*/
	private java.lang.String actEditUrl;
	/**活动访问地址*/
	private java.lang.String actAccessUrl;
	/**演示活动id*/
	private java.lang.Integer showActId;
	/**状态值(0 下线，1上线)*/
	private java.lang.String status;
	/**发布人userId(后台用户Id)*/
	private java.lang.Integer publisherId;
	/**发布时间*/
	private java.util.Date publishTime;
	/**审核人用户id*/
	private java.lang.Integer auditUserId;
	/**(0 待审核，1审核通过，2审核不通过)*/
	private java.lang.String auditState;
	/**审核意见*/
	private java.lang.String auditDesc;
	/**审核时间*/
	private java.util.Date auditTime;
	/**上传时间*/
	private java.util.Date uploadTime;
	/**修改时间*/
	private java.util.Date updateTime;
	
//	private List<PluginPic> pluginPic = new ArrayList<PluginPic>();
	private List<PluginPic> pluginPic = new ArrayList<PluginPic>();  
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  插件名称
	 */
	@Column(name ="name",nullable=true,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  插件名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  开发者用户id(为平台用户id)
	 */
	@Column(name ="userId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  开发者用户id(为平台用户id)
	 */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  插件图标
	 */
	@Column(name ="icon",nullable=true,length=100)
	public java.lang.String getIcon(){
		return this.icon;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  插件图标
	 */
	public void setIcon(java.lang.String icon){
		this.icon = icon;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  插件描述
	 */
	@Column(name ="description",nullable=true,length=250)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  插件描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  演示地址
	 */
	@Column(name ="showUrl",nullable=true,length=150)
	public java.lang.String getShowUrl(){
		return this.showUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  演示地址
	 */
	public void setShowUrl(java.lang.String showUrl){
		this.showUrl = showUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本信息
	 */
	@Column(name ="version",nullable=true,length=20)
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本信息
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版权信息
	 */
	@Column(name ="copyright",nullable=true,length=50)
	public java.lang.String getCopyright(){
		return this.copyright;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版权信息
	 */
	public void setCopyright(java.lang.String copyright){
		this.copyright = copyright;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务器路径未带参数
	 */
	@Column(name ="serverUrl",nullable=true,length=100)
	public java.lang.String getServerUrl(){
		return this.serverUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务器路径未带参数
	 */
	public void setServerUrl(java.lang.String serverUrl){
		this.serverUrl = serverUrl;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  已购买人数
	 */
	@Column(name ="buyNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBuyNum(){
		return this.buyNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  已购买人数
	 */
	public void setBuyNum(java.lang.Integer buyNum){
		this.buyNum = buyNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  试用总人数
	 */
	@Column(name ="tryoutNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getTryoutNum(){
		return this.tryoutNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  试用总人数
	 */
	public void setTryoutNum(java.lang.Integer tryoutNum){
		this.tryoutNum = tryoutNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  价格
	 */
	@Column(name ="price",nullable=true,precision=10,scale=2)
	public java.lang.Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  价格
	 */
	public void setPrice(java.lang.Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  优惠价格
	 */
	@Column(name ="promPrice",nullable=true,precision=10,scale=2)
	public java.lang.Double getPromPrice(){
		return this.promPrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  优惠价格
	 */
	public void setPromPrice(java.lang.Double promPrice){
		this.promPrice = promPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  插件类型(1 即插即用型，2用户定制)
	 */
	@Column(name ="type",nullable=true,length=2)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  插件类型(1 即插即用型，2用户定制)
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	
	
	/**
	 * 方法: 取得java.lang.Object
	 * @return: java.lang.Object  使用说明
	 */
	@Column(name ="guide",nullable=true)
	public java.lang.String getGuide() {
		return guide;
	}

	public void setGuide(java.lang.String guide) {
		this.guide = guide;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  有效期(以月为单位)
	 */
	@Column(name ="valid",nullable=true,precision=10,scale=0)
	public java.lang.Integer getValid(){
		return this.valid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  有效期(以月为单位)
	 */
	public void setValid(java.lang.Integer valid){
		this.valid = valid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  源代码文件地址
	 */
	@Column(name ="filePath",nullable=true,length=200)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  源代码文件地址
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  视频录像地址
	 */
	@Column(name ="videoUrl",nullable=true,length=200)
	public java.lang.String getVideoUrl(){
		return this.videoUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  视频录像地址
	 */
	public void setVideoUrl(java.lang.String videoUrl){
		this.videoUrl = videoUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  新增活动地址
	 */
	@Column(name ="actAddUrl",nullable=true,length=100)
	public java.lang.String getActAddUrl(){
		return this.actAddUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  新增活动地址
	 */
	public void setActAddUrl(java.lang.String actAddUrl){
		this.actAddUrl = actAddUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编辑活动地址
	 */
	@Column(name ="actEditUrl",nullable=true,length=100)
	public java.lang.String getActEditUrl(){
		return this.actEditUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编辑活动地址
	 */
	public void setActEditUrl(java.lang.String actEditUrl){
		this.actEditUrl = actEditUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动访问地址
	 */
	@Column(name ="actAccessUrl",nullable=true,length=100)
	public java.lang.String getActAccessUrl(){
		return this.actAccessUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动访问地址
	 */
	public void setActAccessUrl(java.lang.String actAccessUrl){
		this.actAccessUrl = actAccessUrl;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  演示活动id
	 */
	@Column(name ="showActId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getShowActId(){
		return this.showActId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  演示活动id
	 */
	public void setShowActId(java.lang.Integer showActId){
		this.showActId = showActId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态值(0 下线，1上线)
	 */
	@Column(name ="status",nullable=true,length=1)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态值(0 下线，1上线)
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  发布人userId(后台用户Id)
	 */
	@Column(name ="publisherId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getPublisherId(){
		return this.publisherId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  发布人userId(后台用户Id)
	 */
	public void setPublisherId(java.lang.Integer publisherId){
		this.publisherId = publisherId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发布时间
	 */
	@Column(name ="publishTime",nullable=true)
	public java.util.Date getPublishTime(){
		return this.publishTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发布时间
	 */
	public void setPublishTime(java.util.Date publishTime){
		this.publishTime = publishTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  审核人用户id
	 */
	@Column(name ="auditUserId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAuditUserId(){
		return this.auditUserId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  审核人用户id
	 */
	public void setAuditUserId(java.lang.Integer auditUserId){
		this.auditUserId = auditUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  (0 待审核，1审核通过，2审核不通过)
	 */
	@Column(name ="auditState",nullable=true,length=2)
	public java.lang.String getAuditState(){
		return this.auditState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  (0 待审核，1审核通过，2审核不通过)
	 */
	public void setAuditState(java.lang.String auditState){
		this.auditState = auditState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核意见
	 */
	@Column(name ="auditDesc",nullable=true,length=250)
	public java.lang.String getAuditDesc(){
		return this.auditDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核意见
	 */
	public void setAuditDesc(java.lang.String auditDesc){
		this.auditDesc = auditDesc;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审核时间
	 */
	@Column(name ="auditTime",nullable=true)
	public java.util.Date getAuditTime(){
		return this.auditTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审核时间
	 */
	public void setAuditTime(java.util.Date auditTime){
		this.auditTime = auditTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上传时间
	 */
	@Column(name ="uploadTime",nullable=true)
	public java.util.Date getUploadTime(){
		return this.uploadTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上传时间
	 */
	public void setUploadTime(java.util.Date uploadTime){
		this.uploadTime = uploadTime;
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
	
	@OneToMany(mappedBy="plugin",fetch = FetchType.LAZY)//以“多”一方为主导管理，级联用ALL  
	public List<PluginPic> getPluginPic() {
		return pluginPic;
	}

	public void setPluginPic(List<PluginPic> pluginPic) {
		this.pluginPic = pluginPic;
	}
	
	
	
	
	
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "Plugin")
//	public List<PluginPic> getPluginPic() {
//		return pluginPic;
//	}
//
//	public void setPluginPic(List<PluginPic> pluginPic) {
//		this.pluginPic = pluginPic;
//	}
	
	
}
