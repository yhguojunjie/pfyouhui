package com.yoxi.jgframework.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdIntegerIdentityEntity;

/**
 * 角色表
 */
@Entity
@Table(name = "t_s_role")
public class TSRole extends IdIntegerIdentityEntity implements java.io.Serializable {
	private String roleName;//角色名称
	private String roleCode;//角色编码
	@Column(name = "roleName", nullable = false, length = 100)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name = "roleCode", length = 10)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}