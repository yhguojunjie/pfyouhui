package com.yoxi.jgframework.system.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdUUIDEntity;

/**
 * TSRoleUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_s_role_user")
public class TSRoleUser extends IdUUIDEntity implements java.io.Serializable {
	private TSUser TSUser;
	private TSRole TSRole;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public TSUser getTSUser() {
		return this.TSUser;
	}

	public void setTSUser(TSUser TSUser) {
		this.TSUser = TSUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid")
	public TSRole getTSRole() {
		return this.TSRole;
	}

	public void setTSRole(TSRole TSRole) {
		this.TSRole = TSRole;
	}

}