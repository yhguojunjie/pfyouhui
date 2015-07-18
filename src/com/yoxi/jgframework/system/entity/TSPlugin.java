package com.yoxi.jgframework.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdIntegerIdentityAutoUserEntity;
/**
 * 客户端插件升级
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_s_plugin")
public class TSPlugin extends IdIntegerIdentityAutoUserEntity implements java.io.Serializable {
	private String clientName;//客户端名称
	private String clientVersion;//客服端版本
	private String pluginName;//插件名称
	private String pluginVersion;//插件版本
	private String pluginPackName;//插件包名
	private String pluginDesc;//插件描述
	private String pluginIconUrl;//插件图片地址
	private String pluginFileUrl;//插件文件地址
	
	@Column(name = "clientName", length = 50)	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Column(name = "clientVersion", length = 30)	
	public String getClientVersion() {
		return clientVersion;
	}
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
	@Column(name = "pluginName", length = 50)	
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	@Column(name = "pluginVersion", length = 30)
	public String getPluginVersion() {
		return pluginVersion;
	}
	public void setPluginVersion(String pluginVersion) {
		this.pluginVersion = pluginVersion;
	}
	@Column(name = "pluginPackName", length = 100)
	public String getPluginPackName() {
		return pluginPackName;
	}
	public void setPluginPackName(String pluginPackName) {
		this.pluginPackName = pluginPackName;
	}
	@Column(name = "pluginDesc", length = 100)
	public String getPluginDesc() {
		return pluginDesc;
	}
	public void setPluginDesc(String pluginDesc) {
		if (pluginDesc != null)
			this.pluginDesc = pluginDesc.replaceAll("\r\n", "<br/>");
		else
			this.pluginDesc = pluginDesc;
	}
	@Column(name = "pluginIconUrl", length = 100)
	public String getPluginIconUrl() {
		return pluginIconUrl;
	}
	public void setPluginIconUrl(String pluginIconUrl) {
		this.pluginIconUrl = pluginIconUrl;
	}
	@Column(name = "pluginFileUrl", length = 100)
	public String getPluginFileUrl() {
		return pluginFileUrl;
	}
	public void setPluginFileUrl(String pluginFileUrl) {
		this.pluginFileUrl = pluginFileUrl;
	}

}
