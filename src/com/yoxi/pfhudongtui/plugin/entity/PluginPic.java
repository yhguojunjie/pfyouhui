package com.yoxi.pfhudongtui.plugin.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
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
 * @Description: PluginPic
 * @author jwhat generate
 * @date 2015-03-21 20:28:03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_plugin_pic", schema = "")
@SuppressWarnings("serial")
public class PluginPic extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**插件id*/
	//private java.lang.Integer pluginId;
	/**图片url*/
	private java.lang.String url;
	/**上传时间*/
	private java.util.Date uploadTime;
	/**上传人userId*/
	private java.lang.Integer uploadUser;
	
	private Plugin plugin;
	
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
	 *@return: java.lang.String  图片url
	 */
	@Column(name ="url",nullable=true,length=100)
	public java.lang.String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片url
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  上传人userId
	 */
	@Column(name ="uploadUser",nullable=true,precision=10,scale=0)
	public java.lang.Integer getUploadUser(){
		return this.uploadUser;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  上传人userId
	 */
	public void setUploadUser(java.lang.Integer uploadUser){
		this.uploadUser = uploadUser;
	}
	//,cascade={CascadeType.REFRESH,CascadeType.MERGE}
	@ManyToOne(fetch=FetchType.LAZY)//解决1+N,级联用ALL  
	@JoinColumn(name = "pluginId")
	public Plugin getPlugin() {
		return plugin;
	}

	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}
	
	
}
