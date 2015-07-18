package com.yoxi.pfhudongtui.user.controller;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.user.entity.Channel;
import com.yoxi.pfhudongtui.user.service.ChannelService;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: Channel
 * @author jwhat generate
 * @date 2015-03-31 09:53:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/channelController")
public class ChannelController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelController.class);

	@Autowired
	private ChannelService channelService;

	/**
	 * Channel列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channel")
	public ModelAndView channel(HttpServletRequest request) {
		return new ModelAndView("user/channel/channelList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "channelGrid")
	public void channelGrid(Channel channel,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Channel.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channel);
		this.channelService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Channel
	 * 
	 * @return
	 */
	@RequestMapping(params = "delChannel")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delChannel(Channel channel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channel = systemService.getEntity(Channel.class, channel.getId());
		message = "删除成功";
		channelService.delete(channel);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Channel
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveChannel")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveChannel(Channel channel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channel.getId())) {
			message = "更新成功";
			this.isNew = false;
			Channel t = channelService.get(Channel.class, channel.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channel, t);
				t.setAuditorId(getUser().getId());
				t.setUpdateTime(new Date());
				channelService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			channelService.save(channel);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * Channel列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateChannel")
	public ModelAndView addorupdateChannel(Channel channel, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(channel.getId())) {
			channel = channelService.getEntity(Channel.class, channel.getId());
			req.setAttribute("channelPage", channel);
		}
		return new ModelAndView("user/channel/channelPage").addObject("server", server);
	}
}
