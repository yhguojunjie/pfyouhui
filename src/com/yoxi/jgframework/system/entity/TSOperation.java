package com.yoxi.jgframework.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdUUIDEntity;

/**
 * 权限操作表
 */
@Entity
@Table(name = "t_s_operation")
public class TSOperation extends IdUUIDEntity implements java.io.Serializable {
	private String operationName;
	private String operationCode;
	private String operationIcon;
	private Short status;
	private TSIcon TSIcon = new TSIcon();
	private TSFunction TSFunction = new TSFunction();

	@Column(name = "operationName", length = 50)
	public String getOperationName() {
		return this.operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	@Column(name = "operationCode", length = 50)
	public String getOperationCode() {
		return this.operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	@Column(name = "operationIcon", length = 100)
	public String getOperationIcon() {
		return this.operationIcon;
	}

	public void setOperationIcon(String operationIcon) {
		this.operationIcon = operationIcon;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iconid")
	public TSIcon getTSIcon() {
		return TSIcon;
	}

	public void setTSIcon(TSIcon tSIcon) {
		TSIcon = tSIcon;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "functionid")
	public TSFunction getTSFunction() {
		return TSFunction;
	}

	public void setTSFunction(TSFunction tSFunction) {
		TSFunction = tSFunction;
	}
}