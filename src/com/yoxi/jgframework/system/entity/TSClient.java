package com.yoxi.jgframework.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdIntegerIdentityAutoUserEntity;
/**
 * 客户端升级
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_s_client")
public class TSClient extends IdIntegerIdentityAutoUserEntity implements java.io.Serializable {
	private String clientName;//客户端名称
	private String versionUrl;//版本文件路径
	private String versionIconUrl;//版本图片路径
	private String versionCode;//版本编码
	private String versionMemo;//版本说明
	private Integer versionNum;//版本号
	private String deviceType;//设备型号
	private Boolean isForce;//是否强制升级
	private String forceVersionArrang;//强制升级版本范围 1-100，有限获取这个值
	private String forceVersionCode;//强制升级版代码 2.3.*,2.2.* 可以这么表达
	private Boolean publishStatus;//发布状态，0正常，1发布
	@Column(name = "clientName", length = 100)
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Column(name = "versionUrl", length = 200)
	public String getVersionUrl() {
		return versionUrl;
	}
	public void setVersionUrl(String versionUrl) {
		this.versionUrl = versionUrl;
	}
	
	public String getVersionIconUrl() {
		return versionIconUrl;
	}
	public void setVersionIconUrl(String versionIconUrl) {
		this.versionIconUrl = versionIconUrl;
	}
	@Column(name = "versionCode", length = 30)
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	@Column(name = "versionMemo", length = 1000)
	public String getVersionMemo() {
		return versionMemo;
	}
	public void setVersionMemo(String versionMemo) {
		if (versionMemo != null)
			this.versionMemo = versionMemo.replaceAll("\r\n", "<br/>");
		else
			this.versionMemo = versionMemo;
		
	}
	@Column(name = "versionNum")
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	@Column(name = "deviceType", length = 50)
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@Column(name = "isForce")
	public Boolean getIsForce() {
		return isForce;
	}
	public void setIsForce(Boolean isForce) {
		this.isForce = isForce;
	}
	@Column(name = "forceVersionArrang", length = 50)
	public String getForceVersionArrang() {
		return forceVersionArrang;
	}
	public void setForceVersionArrang(String forceVersionArrang) {
		this.forceVersionArrang = forceVersionArrang;
	}
	@Column(name = "forceVersionCode", length = 500)
	public String getForceVersionCode() {
		return forceVersionCode;
	}
	public void setForceVersionCode(String forceVersionCode) {
		this.forceVersionCode = forceVersionCode;
	}
	@Column(name = "publishStatus")
	public Boolean getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(Boolean publishStatus) {
		this.publishStatus = publishStatus;
	}
}
