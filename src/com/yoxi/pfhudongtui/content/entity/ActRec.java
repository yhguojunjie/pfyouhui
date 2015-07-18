package com.yoxi.pfhudongtui.content.entity;

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
 * @Description: ActRec
 * @author jwhat generate
 * @date 2015-04-23 15:40:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_act_rec", schema = "")
@SuppressWarnings("serial")
public class ActRec extends IdUserDefinedEntity implements java.io.Serializable {
	/**推荐ID*/
	private java.lang.Integer id;
	/**活动id*/
	private java.lang.Integer actId;
	/**排列顺序*/
	private java.lang.Integer seq;
	/**创建时间*/
	private java.util.Date createTime;
	/**修改时间*/
	private java.util.Date updateTime;
	/**隐藏状态 0显示 ，1隐藏*/
	private java.lang.String hideState;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  推荐ID
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id",nullable=false,precision=10,scale=0)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  推荐ID
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  活动id
	 */
	@Column(name ="actId",nullable=false,precision=10,scale=0)
	public java.lang.Integer getActId(){
		return this.actId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  活动id
	 */
	public void setActId(java.lang.Integer actId){
		this.actId = actId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排列顺序
	 */
	@Column(name ="seq",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSeq(){
		return this.seq;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排列顺序
	 */
	public void setSeq(java.lang.Integer seq){
		this.seq = seq;
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
}
