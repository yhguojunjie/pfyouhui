package com.yoxi.pfhudongtui.plugin.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.yoxi.pfhudongtui.content.entity.AgentClassic;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;

/**   
 * @Title: Entity
 * @Description: ActClassic
 * @author jwhat generate
 * @date 2015-03-25 20:27:53
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_act_classic", schema = "")
@SuppressWarnings("serial")
public class ActClassic extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**顺序*/
	private java.lang.Integer seq;
	/**品牌名(此字段为空，则使用活动默认)*/
	private java.lang.String bannerName;
	/**品牌logo(此字段为空，则使用活动默认)*/
	private java.lang.String bannerLogo;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
	private java.util.Date updateTime;
	/**插件id*/
	private java.lang.Integer pluginId;
	/**1：选择活动，2手动添加**/
	private java.lang.String type;
	/**活动名称**/
	private java.lang.String title;
	/**活动开始时间**/
	private java.util.Date startTime;
	/**活动结束时间**/
	private java.util.Date endTime;
	/***浏览量**/
	private java.lang.Integer browseNum;
	/***访问量**/
	private java.lang.Integer joinNum;
	/**隐藏状态 0显示 ，1隐藏**/
	private java.lang.String hideState;
	/**审核状态 0 未审核，1通过,2:不通过**/
	private java.lang.String auditState;
	/**审核人**/
	private java.lang.Integer auditorId;
	/**审核时间**/
	private java.util.Date auditTime;

	private PluginAct pluginAct;
	
	private PlatformUser platformUser;
	
	private AgentInfo agentInfo;
	
	private List<ActClassicPic> actClassicPic = new ArrayList<ActClassicPic>();  
	
	private List<AgentClassic> agentClassic = new  ArrayList<AgentClassic>();  
	
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌名(此字段为空，则使用活动默认)
	 */
	@Column(name ="bannerName",nullable=true,length=50)
	public java.lang.String getBannerName(){
		return this.bannerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌名(此字段为空，则使用活动默认)
	 */
	public void setBannerName(java.lang.String bannerName){
		this.bannerName = bannerName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌logo(此字段为空，则使用活动默认)
	 */
	@Column(name ="bannerLogo",nullable=true,length=150)
	public java.lang.String getBannerLogo(){
		return this.bannerLogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌logo(此字段为空，则使用活动默认)
	 */
	public void setBannerLogo(java.lang.String bannerLogo){
		this.bannerLogo = bannerLogo;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  插件id
	 */
	@Column(name ="pluginId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getPluginId(){
		return this.pluginId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  插件id
	 */
	public void setPluginId(java.lang.Integer pluginId){
		this.pluginId = pluginId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actId",nullable=true)
	public PluginAct getPluginAct() {
		return pluginAct;
	}

	public void setPluginAct(PluginAct pluginAct) {
		this.pluginAct = pluginAct;
	}
	
	@OneToMany(mappedBy="actClassic",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)//以“多”一方为主导管理，级联用ALL  
	public List<ActClassicPic> getActClassicPic() {
		return actClassicPic;
	}

	public void setActClassicPic(List<ActClassicPic> actClassicPic) {
		this.actClassicPic = actClassicPic;
	}
	@Column(name ="type",nullable=true,length=1)
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}
	@Column(name ="title",nullable=true,length=50)
	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	@Column(name ="startTime",nullable=true)
	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	@Column(name ="endTime",nullable=true)
	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}
	@Column(name ="browseNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(java.lang.Integer browseNum) {
		this.browseNum = browseNum;
	}
	@Column(name ="joinNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(java.lang.Integer joinNum) {
		this.joinNum = joinNum;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public PlatformUser getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
	}

	@Column(name ="hideState",nullable=true,length=1)
	public java.lang.String getHideState() {
		return hideState;
	}

	public void setHideState(java.lang.String hideState) {
		this.hideState = hideState;
	}
	@Column(name ="auditState",nullable=true,length=1)
	public java.lang.String getAuditState() {
		return auditState;
	}

	public void setAuditState(java.lang.String auditState) {
		this.auditState = auditState;
	}
	@Column(name ="auditorId",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(java.lang.Integer auditorId) {
		this.auditorId = auditorId;
	}
	@Column(name ="auditTime",nullable=true)
	public java.util.Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}

	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JoinColumn(name = "agentId",nullable=true)
	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}

	@OneToMany(mappedBy="actClassic",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)//以“多”一方为主导管理，级联用ALL  
	public List<AgentClassic> getAgentClassic() {
		return agentClassic;
	}

	public void setAgentClassic(List<AgentClassic> agentClassic) {
		this.agentClassic = agentClassic;
	}
	
	
}
