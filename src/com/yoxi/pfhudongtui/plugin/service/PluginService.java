package com.yoxi.pfhudongtui.plugin.service;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.pfhudongtui.plugin.entity.Plugin;

public interface PluginService extends CommonService{

	/**
	 * 保存插件图片
	 * @param plugin
	 * @param request
	 */
	public void savePluginImg(Plugin plugin, HttpServletRequest request);
	/**
	 * 插件上架
	 * @param plugin
	 */
	public void online(Plugin plugin);
	/**
	 * 插件下架
	 * @param plugin
	 */
	public void offline(Plugin plugin);
}
