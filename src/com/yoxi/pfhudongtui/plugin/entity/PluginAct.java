package com.yoxi.pfhudongtui.plugin.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;

/**   
 * @Title: Entity
 * @Description: PluginAct
 * @author jwhat generate
 * @date 2015-03-25 16:50:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_plugin_act", schema = "")
@SuppressWarnings("serial")
public class PluginAct extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**插件用户表Id*/
	private java.lang.Integer userPluginId;
	/**活动名称*/
	private java.lang.String title;
	/**活动图标*/
	private java.lang.String icon;
	/**参与人数*/
	private java.lang.Integer joinNum;
	/**浏览数*/
	private java.lang.Integer browseNum;
	/**开始时间*/
	private java.util.Date startTime;
	/**结束时间*/
	private java.util.Date endTime;
	/**活动访问页面地址*/
	private java.lang.String accessUrl;
	/**二维码图片地址*/
	private java.lang.String qrcodeUrl;
	/**是否在平台公开(0公开,1不公开)*/
	private java.lang.String actOpen;
	/**是否在代理商平台下公开(0公开,1不公开)*/
	private java.lang.String agentActOpen;
	/**统计数据是否公开(0公开,1不公开)*/
	private java.lang.String countOpen;
	/**活动类型(0站内,1站外)*/
	private java.lang.String type;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	/**排序*/
	private java.lang.Integer sequence;
	/**是否申请推广*/
	private java.lang.String aplyRecommend;
	/**数据状态 0正常，1删除*/
	private java.lang.String dataStatus;
	
	private PlatformUser platformUser;
	private AgentInfo agentInfo;
	private Plugin plugin;
	
	private List<ActClassic> actClassic = new ArrayList<ActClassic>();  
	
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
	 *@return: java.lang.Integer  插件用户表Id
	 */
	@Column(name ="userPluginId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getUserPluginId(){
		return this.userPluginId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  插件用户表Id
	 */
	public void setUserPluginId(java.lang.Integer userPluginId){
		this.userPluginId = userPluginId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动名称
	 */
	@Column(name ="title",nullable=true,length=150)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动名称
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动图标
	 */
	@Column(name ="icon",nullable=true,length=100)
	public java.lang.String getIcon(){
		return this.icon;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动图标
	 */
	public void setIcon(java.lang.String icon){
		this.icon = icon;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  参与人数
	 */
	@Column(name ="joinNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getJoinNum(){
		return this.joinNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  参与人数
	 */
	public void setJoinNum(java.lang.Integer joinNum){
		this.joinNum = joinNum;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  浏览数
	 */
	@Column(name ="browseNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBrowseNum(){
		return this.browseNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  浏览数
	 */
	public void setBrowseNum(java.lang.Integer browseNum){
		this.browseNum = browseNum;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="startTime",nullable=true)
	public java.util.Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="endTime",nullable=true)
	public java.util.Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动访问页面地址
	 */
	@Column(name ="accessUrl",nullable=true,length=200)
	public java.lang.String getAccessUrl(){
		return this.accessUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动访问页面地址
	 */
	public void setAccessUrl(java.lang.String accessUrl){
		this.accessUrl = accessUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二维码图片地址
	 */
	@Column(name ="qrcodeUrl",nullable=true,length=100)
	public java.lang.String getQrcodeUrl(){
		return this.qrcodeUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二维码图片地址
	 */
	public void setQrcodeUrl(java.lang.String qrcodeUrl){
		this.qrcodeUrl = qrcodeUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否在平台公开(0公开,1不公开)
	 */
	@Column(name ="actOpen",nullable=true,length=1)
	public java.lang.String getActOpen(){
		return this.actOpen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否在平台公开(0公开,1不公开)
	 */
	public void setActOpen(java.lang.String actOpen){
		this.actOpen = actOpen;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否在代理商平台下公开(0公开,1不公开)
	 */
	@Column(name ="agentActOpen",nullable=true,length=1)
	public java.lang.String getAgentActOpen(){
		return this.agentActOpen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否在代理商平台下公开(0公开,1不公开)
	 */
	public void setAgentActOpen(java.lang.String agentActOpen){
		this.agentActOpen = agentActOpen;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  统计数据是否公开(0公开,1不公开)
	 */
	@Column(name ="countOpen",nullable=true,length=1)
	public java.lang.String getCountOpen(){
		return this.countOpen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  统计数据是否公开(0公开,1不公开)
	 */
	public void setCountOpen(java.lang.String countOpen){
		this.countOpen = countOpen;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  活动类型(0站内,1站外)
	 */
	@Column(name ="type",nullable=true,length=2)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  活动类型(0站内,1站外)
	 */
	public void setType(java.lang.String type){
		this.type = type;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排序
	 */
	@Column(name ="sequence",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSequence(){
		return this.sequence;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排序
	 */
	public void setSequence(java.lang.Integer sequence){
		this.sequence = sequence;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否申请推广
	 */
	@Column(name ="aplyRecommend",nullable=true,length=1)
	public java.lang.String getAplyRecommend(){
		return this.aplyRecommend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否申请推广
	 */
	public void setAplyRecommend(java.lang.String aplyRecommend){
		this.aplyRecommend = aplyRecommend;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数据状态 0正常，1删除
	 */
	@Column(name ="dataStatus",nullable=true,length=1)
	public java.lang.String getDataStatus(){
		return this.dataStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数据状态 0正常，1删除
	 */
	public void setDataStatus(java.lang.String dataStatus){
		this.dataStatus = dataStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public PlatformUser getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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
	
	@OneToMany(mappedBy="pluginAct",fetch = FetchType.EAGER)//以“多”一方为主导管理，级联用ALL  
	public List<ActClassic> getActClassic() {
		return actClassic;
	}

	public void setActClassic(List<ActClassic> actClassic) {
		this.actClassic = actClassic;
	}

	
	
	
	

	
	
	
	
	
	
	
}
