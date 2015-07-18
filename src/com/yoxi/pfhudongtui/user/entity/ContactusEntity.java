package com.yoxi.pfhudongtui.user.entity;

public class ContactusEntity {

	/**id*/
	private java.lang.Integer id;
	/**客服电话*/
	private java.lang.String servicePhone;
	/**粉丝QQ群*/
	private java.lang.String qqGroup;
	/**客服QQ*/
	private java.lang.String serviceqq;
	/**邮箱*/
	private java.lang.String email;
	/**提交时间*/
	private java.util.Date applyTime;
	/**0 未审核，1审核通过*/
	private java.lang.String auditState;
	/**审核时间*/
	private java.util.Date auditTime;
	/**0 未启用，1启用*/
	private java.lang.String status;
	/***审核人**/
	private java.lang.String auditUserName;
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(java.lang.String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public java.lang.String getQqGroup() {
		return qqGroup;
	}
	public void setQqGroup(java.lang.String qqGroup) {
		this.qqGroup = qqGroup;
	}
	public java.lang.String getServiceqq() {
		return serviceqq;
	}
	public void setServiceqq(java.lang.String serviceqq) {
		this.serviceqq = serviceqq;
	}
	public java.lang.String getEmail() {
		return email;
	}
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	public java.util.Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
	}
	public java.lang.String getAuditState() {
		return auditState;
	}
	public void setAuditState(java.lang.String auditState) {
		this.auditState = auditState;
	}
	public java.util.Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public java.lang.String getAuditUserName() {
		return auditUserName;
	}
	public void setAuditUserName(java.lang.String auditUserName) {
		this.auditUserName = auditUserName;
	}
	
	
	
}
