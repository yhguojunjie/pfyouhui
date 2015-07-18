package com.yoxi.pfhudongtui.plugin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.plugin.entity.Plugin;
import com.yoxi.pfhudongtui.plugin.entity.PluginAgent;
import com.yoxi.pfhudongtui.plugin.entity.PluginPic;
import com.yoxi.pfhudongtui.plugin.service.PluginPicService;
import com.yoxi.pfhudongtui.plugin.service.PluginService;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.jgframework.common.model.common.SessionInfo;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.system.service.SystemService;

@Service("pluginService")
@Transactional
public class PluginServiceImpl extends CommonServiceImpl implements PluginService {
	@Autowired
	private PluginPicService pluginPicService;
	@Autowired
	private SystemService systemService;
	
	@Override
	public void savePluginImg(Plugin plugin, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SessionInfo sessioninfo = (SessionInfo) session.getAttribute(Globals.USER_SESSION);
		String urls = request.getParameter("url");
		String[] url = urls.split(",");
		for(String l : url){
			if("".equals(l))continue;
			PluginPic pluginPic = new PluginPic();
			pluginPic.setPlugin(plugin);
			pluginPic.setUrl(l);
			pluginPic.setUploadTime(new Date());
			pluginPic.setUploadUser(sessioninfo.getUser().getId());
			pluginPicService.save(pluginPic);
		}
	}
	@Override
	public void online(Plugin plugin) {
		List<AgentInfo> agentInfo = systemService.loadAll(AgentInfo.class);
		for(AgentInfo a :agentInfo ){
			PluginAgent pluginAgent = new PluginAgent();
			pluginAgent.setPlugin(plugin);
			pluginAgent.setAgentInfo(a);
			pluginAgent.setSalePrice(0.00);
			pluginAgent.setOnlineState("0");
			pluginAgent.setCreateTime(new Date());
			this.save(pluginAgent);
		}
	}

	@Override
	public void offline(Plugin plugin) {
		String sql ="delete from t_plugin_agent where pluginId="+plugin.getId();
		systemService.executeSql(sql);
		
	}
	
}