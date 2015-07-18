package com.yoxi.jgframework.system.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdUUIDEntity;


/**
 * TLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_s_log")
public class TSLog extends IdUUIDEntity implements java.io.Serializable {
	private TSUser TSUser;
	private Short logLevel;
	private Timestamp operateTime;
	private Short operateType;
	private String logContent;
	private String broswer;//用户浏览器类型
	private String note;
	private String objectType; //对象类型
	private String objectId;//对象ID
	private int takeTime;//耗时

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public TSUser getTSUser() {
		return this.TSUser;
	}

	public void setTSUser(TSUser TSUser) {
		this.TSUser = TSUser;
	}

	@Column(name = "logLevel")
	public Short getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(Short logLevel) {
		this.logLevel = logLevel;
	}

	@Column(name = "operateTime", nullable = false, length = 35)
	public Timestamp getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "operateType")
	public Short getOperateType() {
		return this.operateType;
	}

	public void setOperateType(Short operateType) {
		this.operateType = operateType;
	}

	@Column(name = "logContent", nullable = false, length = 2000)
	public String getLogContent() {
		return this.logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	@Column(name = "note", length = 300)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Column(name = "broswer", length = 100)
	public String getBroswer() {
		return broswer;
	}

	public void setBroswer(String broswer) {
		this.broswer = broswer;
	}
	@Column(name = "objectType", length = 100)
	public String getObjectType() {
		return objectType;
	}
	@Column(name = "objectId", length = 50)
	public String getObjectId() {
		return objectId;
	}
	@Column(name = "takeTime")
	public int getTakeTime() {
		return takeTime;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public void setTakeTime(int takeTime) {
		this.takeTime = takeTime;
	}

}