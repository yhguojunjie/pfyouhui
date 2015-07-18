package com.yoxi.pfhudongtui.content.entity;

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
import com.yoxi.pfhudongtui.plugin.entity.ActClassic;

/**   
 * @Title: Entity
 * @Description: AgentClassic
 * @author jwhat generate
 * @date 2015-04-27 10:30:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_agent_classic", schema = "")
@SuppressWarnings("serial")
public class AgentClassic extends IdUserDefinedEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**顺序*/
	private java.lang.Integer seq;
	/**代理商id*/
	private java.lang.Integer agentId;
	/**隐藏状态 0显示 ，1隐藏*/
	private java.lang.String hideState;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
	private java.util.Date updateTime;
	
	private ActClassic actClassic;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  隐藏状态 0显示 ，1隐藏
	 */
	@Column(name ="hideState",nullable=true,length=1)
	public java.lang.String getHideState(){
		return this.hideState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  隐藏状态 0显示 ，1隐藏
	 */
	public void setHideState(java.lang.String hideState){
		this.hideState = hideState;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "classicId")
	public ActClassic getActClassic() {
		return actClassic;
	}

	public void setActClassic(ActClassic actClassic) {
		this.actClassic = actClassic;
	}
	
	
}
