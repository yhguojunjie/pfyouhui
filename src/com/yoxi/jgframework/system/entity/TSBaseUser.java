package com.yoxi.jgframework.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdIntegerIdentityAutoUserEntity;

/**
 * 系统用户父类表
 * 
 */
@Entity
@Table(name = "t_s_base_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class TSBaseUser extends IdIntegerIdentityAutoUserEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;// 用户名
	private String realName;// 真实姓名
	private String browser;// 用户使用浏览器类型
	private String userKey;// 用户验证唯一标示
	private String password;//用户密码
	private Short activitiSync;//是否同步工作流引擎
	private Short status;// 0：禁用,1启用
	private byte[] signature;// 签名文件
	private String pycode;//拼音码
	
	private TSDepart TSDepart = new TSDepart();// 部门
	
	@Column(name = "signature")
	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	@Column(name = "browser", length = 20)
	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Column(name = "userkey", length = 200)
	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	public Short getActivitiSync() {
		return activitiSync;
	}
	@Column(name = "activitisync")
	public void setActivitiSync(Short activitiSync) {
		this.activitiSync = activitiSync;
	}
	@Column(name = "password", length = 10)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departid")
	public TSDepart getTSDepart() {
		return this.TSDepart;
	}

	public void setTSDepart(TSDepart TSDepart) {
		this.TSDepart = TSDepart;
	}
	@Column(name = "username", nullable = false, length = 10)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "realname", length = 50)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Column(name = "pycode", length = 20)
	public String getPycode() {
		return pycode;
	}
	public void setPycode(String pycode) {
		this.pycode = pycode;
	}

}