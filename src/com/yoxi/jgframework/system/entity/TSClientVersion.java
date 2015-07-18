package com.yoxi.jgframework.system.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;

/**   
 * @Title: Entity
 * @Description: ClientVersion
 * @author jwhat generate
 * @date 2013-08-09 16:53:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_client_version", schema = "")
@SuppressWarnings("serial")
public class TSClientVersion extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**versionCode*/
	private java.lang.String versionCode;
	/**versionNum*/
	private java.lang.Integer versionNum;
	/**versionIconUrl*/
	private java.lang.String versionIconUrl;
	/**versionMemo*/
	private java.lang.String versionMemo;
	/**isForce*/
	private Boolean isForce;
	/**forceVersionArrang*/
	private java.lang.String forceVersionArrang;
	/**forceVersionCode*/
	private java.lang.String forceVersionCode;
	/**creatBy*/
	private java.lang.Integer creatBy;
	/**createName*/
	private java.lang.String createName;
	/**publishStatus*/
	private Boolean publishStatus;
	/**createDate*/
	private java.util.Date createDate;
	/**updateDate*/
	private java.util.Date updateDate;
	
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
	 *@return: java.lang.String  versionCode
	 */
	@Column(name ="versionCode",nullable=true,length=30)
	public java.lang.String getVersionCode(){
		return this.versionCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  versionCode
	 */
	public void setVersionCode(java.lang.String versionCode){
		this.versionCode = versionCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  versionNum
	 */
	@Column(name ="versionNum",nullable=true,precision=10,scale=0)
	public java.lang.Integer getVersionNum(){
		return this.versionNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  versionNum
	 */
	public void setVersionNum(java.lang.Integer versionNum){
		this.versionNum = versionNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  versionIconUrl
	 */
	@Column(name ="versionIconUrl",nullable=true,length=200)
	public java.lang.String getVersionIconUrl(){
		return this.versionIconUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  versionIconUrl
	 */
	public void setVersionIconUrl(java.lang.String versionIconUrl){
		this.versionIconUrl = versionIconUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  versionMemo
	 */
	@Column(name ="versionMemo",nullable=true,length=500)
	public java.lang.String getVersionMemo(){
		return this.versionMemo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  versionMemo
	 */
	public void setVersionMemo(java.lang.String versionMemo){
		this.versionMemo = versionMemo;
	}
	@Column(name = "isForce")
	public Boolean getIsForce() {
		return isForce;
	}
	public void setIsForce(Boolean isForce) {
		this.isForce = isForce;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  forceVersionArrang
	 */
	@Column(name ="forceVersionArrang",nullable=true,length=100)
	public java.lang.String getForceVersionArrang(){
		return this.forceVersionArrang;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  forceVersionArrang
	 */
	public void setForceVersionArrang(java.lang.String forceVersionArrang){
		this.forceVersionArrang = forceVersionArrang;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  forceVersionCode
	 */
	@Column(name ="forceVersionCode",nullable=true,length=300)
	public java.lang.String getForceVersionCode(){
		return this.forceVersionCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  forceVersionCode
	 */
	public void setForceVersionCode(java.lang.String forceVersionCode){
		this.forceVersionCode = forceVersionCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  creatBy
	 */
	@Column(name ="creatBy",nullable=true,precision=10,scale=0)
	public java.lang.Integer getCreatBy(){
		return this.creatBy;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  creatBy
	 */
	public void setCreatBy(java.lang.Integer creatBy){
		this.creatBy = creatBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  createName
	 */
	@Column(name ="createName",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  createName
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	@Column(name = "publishStatus")
	public Boolean getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(Boolean publishStatus) {
		this.publishStatus = publishStatus;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createDate
	 */
	@Column(name ="createDate",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createDate
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  updateDate
	 */
	@Column(name ="updateDate",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  updateDate
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
}
