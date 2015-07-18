package com.yoxi.pfhudongtui.plugin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoxi.jgframework.common.controller.BaseController;

/**
 * 管理员首页数据统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/adminPageController")
public class AdminPageController extends BaseController {

	   //模板总数
		@RequestMapping(params = "pluginNum")
		@ResponseBody
	   public Integer pluginNum(HttpServletRequest request){
			List<Integer> list = systemService.findListbySql("select id from t_plugin");
			return list.size();
	   }
		
		 //代理商数
		@RequestMapping(params = "agentNum")
		@ResponseBody
	   public Integer agentNum(HttpServletRequest request){
			List<Integer> list = systemService.findListbySql("select id from t_agent_info");
			return list.size();
	   }
		 //提现审核未处理
		@RequestMapping(params = "agentWithdraw")
		@ResponseBody
	   public Integer agentWithdraw(HttpServletRequest request){
			List<Integer> list = systemService.findListbySql("select id from t_agent_withdraw where auditState='0'");
			return list.size();
	   }
		
		 //经典案例数
		@RequestMapping(params = "actClassic")
		@ResponseBody
	   public Integer actClassic(HttpServletRequest request){
			List<Integer> list = systemService.findListbySql("select id from t_act_classic ");
			return list.size();
	   }
}
