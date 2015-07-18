package com.yoxi.pfhudongtui.plugin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
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
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.pfhudongtui.plugin.entity.AgentBanner;
import com.yoxi.pfhudongtui.plugin.entity.AgentContSwitch;
import com.yoxi.pfhudongtui.plugin.entity.Banner;
import com.yoxi.pfhudongtui.plugin.entity.Plugin;
import com.yoxi.pfhudongtui.plugin.service.AgentBannerService;
import com.yoxi.pfhudongtui.plugin.service.AgentContSwitchService;
import com.yoxi.pfhudongtui.plugin.service.BannerService;
import com.yoxi.pfhudongtui.plugin.service.PluginService;
import com.yoxi.pfhudongtui.user.service.AgentInfoService;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;
import com.yoxi.pfhudongtui.vo.plugin.PluginVO;

/**
 * @Title: Controller
 * @Description: Banner
 * @author jwhat generate
 * @date 2015-04-20 11:28:06
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/bannerController")
public class BannerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BannerController.class);

	@Autowired
	private BannerService bannerService;

	@Autowired
	private AgentInfoService agentInfoService;

	@Autowired
	private AgentBannerService agentBannerService;
	@Autowired
	private AgentContSwitchService agentContSwitchService;

	@Autowired
	private PluginService pluginService;

	/**
	 * Banner列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "banner")
	public ModelAndView banner(HttpServletRequest request) {
		return new ModelAndView("plugin/banner/bannerList");
	}

	/**
	 * 跳转所有模版列表
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "pluginList")
	public String pluginList(HttpServletRequest req) {
		return "plugin/banner/pluginList";
	}

	/**
	 * 选择模版列表
	 * 
	 * @param pluginAct
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "pluginGrid")
	public void pluginGrid(Plugin plugin, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(Plugin.class, dataGrid);
		// cq.createAlias("user", "ts");
		List<Integer> id = systemService.findListbySql("select objId from t_banner where where objId <> 0 ");
		// cq.createAlias("agentClassic", "ts");
		// cq.add(Restrictions.isEmpty("agentClassic"));

		if (id.size() > 0) {
			cq.add(Restrictions.not(Restrictions.in("id", id)));
		}

		// 查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, plugin);
		this.pluginService.getDataGridReturn(cq, true);
		List<Plugin> list = dataGrid.getReaults();
		List<PluginVO> pluginList = new ArrayList<PluginVO>();
		for (Plugin l : list) {
			PluginVO pluginVO = new PluginVO();
			pluginVO.setObjId(l.getId());
			pluginVO.setName(l.getName());
			pluginList.add(pluginVO);
		}
		dataGrid.setReaults(pluginList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "bannerGrid")
	public void bannerGrid(Banner banner, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		String ptpath = PropertiesUtils.getPara("ptpath") + PropertiesUtils.getPara("ptplugindetail");

		CriteriaQuery cq = new CriteriaQuery(Banner.class, dataGrid);
		// 查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, banner);
		this.bannerService.getDataGridReturn(cq, true);
		List<Banner> list = dataGrid.getReaults();
		List<Banner> bannerList = new ArrayList<Banner>();
		for (Banner l : list) {
			l.setPcLogo(server + "/" + l.getPcLogo());
			l.setWxLogo(server + "/" + l.getWxLogo());
			if (l.getLinkType().equals("1")) {
				l.setPcLink(ptpath + "/" + l.getObjId());
			}
			bannerList.add(l);
		}
		dataGrid.setReaults(bannerList);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Banner
	 * 
	 * @return
	 */
	@RequestMapping(params = "delBanner")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delBanner(Banner banner, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		banner = systemService.getEntity(Banner.class, banner.getId());
		message = "删除成功";
		AgentContSwitch t = agentContSwitchService.get(AgentContSwitch.class, getUser().getId());
		if (t.getBannerState().equals("0")) {
			AgentBanner agentBanner = agentBannerService.findUniqueByProperty(AgentBanner.class, "sbannerId", banner.getId());
			agentBannerService.delete(agentBanner);
		}
		bannerService.delete(banner);
		// systemService.addLog(message, Globals.Log_Type_DEL,
		// Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加Banner
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveBanner")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveBanner(Banner banner, HttpServletRequest request, DataGrid dataGrid) {
		String ptpath = PropertiesUtils.getPara("ptpath") + PropertiesUtils.getPara("ptplugindetail");
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(banner.getId())) {
			message = "更新成功";
			this.isNew = false;
			Banner t = bannerService.get(Banner.class, banner.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(banner, t);
				t.setUpdateTime(new Date());
				bannerService.saveOrUpdate(t);
				AgentBanner agentBanner = agentBannerService.findUniqueByProperty(AgentBanner.class, "sbannerId", banner.getId());

				String sqlQuery = "select * from t_agent_cont_switch ";
				List<AgentContSwitch> list = this.agentContSwitchService.findListbySql(sqlQuery);

				for (int i = 0; i < list.size(); i++) {
					AgentContSwitch ttt = new AgentContSwitch();
					ttt = list.get(i);
					AgentContSwitch tt = agentContSwitchService.get(AgentContSwitch.class, ttt.getAgentId());
					if (tt == null) {
						agentBanner.setSeq(banner.getSeq());
						agentBanner.setAgentId(ttt.getAgentId());
						agentBanner.setSbannerId(banner.getId());
						if (banner.getPcLogo() != null) {
							agentBanner.setPcLogo(banner.getPcLogo());
						}
						if (banner.getPcLink() != null) {
							agentBanner.setPcLink(banner.getPcLink());
						}
						agentBanner.setAuditstate(" ");
						agentBanner.setHideState("0");
						if (banner.getWxLogo() != null) {
							agentBanner.setWxLogo(banner.getWxLogo());
						}
						if (banner.getPcLink() != null) {
							agentBanner.setWxLink(banner.getPcLink());
						}
						agentBanner.setUpdateTime(banner.getUpdateTime());
						agentBannerService.saveOrUpdate(agentBanner);
					} else {
						if (tt.getBannerState().equals("0")) {
							agentBanner.setSeq(banner.getSeq());
							agentBanner.setAgentId(ttt.getAgentId());
							agentBanner.setSbannerId(banner.getId());
							if (banner.getPcLogo() != null) {
								agentBanner.setPcLogo(banner.getPcLogo());
							}
							if (banner.getPcLink() != null) {
								agentBanner.setPcLink(banner.getPcLink());
							}
							agentBanner.setAuditstate(" ");
							agentBanner.setHideState("0");
							if (banner.getWxLogo() != null) {
								agentBanner.setWxLogo(banner.getWxLogo());
							}
							if (banner.getPcLink() != null) {
								agentBanner.setWxLink(banner.getPcLink());
							}
							agentBanner.setUpdateTime(banner.getUpdateTime());
							agentBannerService.saveOrUpdate(agentBanner);
						}

					}
				}

				/*
				 * CriteriaQuery cq = new CriteriaQuery(AgentContSwitch.class,
				 * dataGrid); AgentContSwitch agentContSwitch = new
				 * AgentContSwitch(); // 查询条件组装器
				 * com.yoxi.jgframework.extend.hqlsearch
				 * .HqlGenerateUtil.installHql(cq, agentContSwitch);
				 * this.agentContSwitchService.getDataGridReturn(cq, true);
				 * List<AgentContSwitch> list = dataGrid.getReaults();
				 */

				// systemService.addLog(message, Globals.Log_Type_UPDATE,
				// Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			banner.setCreateTime(new Date());
			banner.setUpdateTime(new Date());
			bannerService.save(banner);
			AgentBanner agentBanner = new AgentBanner();

			AgentContSwitch tttt = new AgentContSwitch();

			String sqlQuery = "select f.agentId from ((select agentId from t_agent_cont_switch where bannerState='0') union "
					+ " (select id as agentId from t_s_base_user where departid='01000000'))f ";

			/*
			 * String sqlQuery =
			 * "select agentId from t_agent_cont_switch where bannerState='0'";
			 */
			List list = systemService.findListbySql(sqlQuery);

			for (int i = 0; i < list.size(); i++) {
				String ttt = null;
				int obj = (Integer) list.get(i);

				AgentContSwitch t = agentContSwitchService.get(AgentContSwitch.class, obj);
				if (t == null) {
					agentBanner.setSeq(banner.getSeq());
					agentBanner.setAgentId(obj);
					agentBanner.setSbannerId(banner.getId());
					if (banner.getPcLogo() != null) {
						agentBanner.setPcLogo(banner.getPcLogo());
					}
					if (banner.getPcLink() != null) {
						agentBanner.setPcLink(banner.getPcLink());
					}

					agentBanner.setAuditstate(" ");
					agentBanner.setHideState("0");
					if (banner.getWxLogo() != null) {
						agentBanner.setWxLogo(banner.getWxLogo());
					}
					if (banner.getPcLink() != null) {
						agentBanner.setWxLink(banner.getPcLink());
					}
					agentBanner.setCreateTime(banner.getCreateTime());
					agentBanner.setUpdateTime(banner.getUpdateTime());
					agentBannerService.save(agentBanner);
					if (i % 1 == 0) {
						agentBannerService.getSession().flush();
						agentBannerService.getSession().clear();
					}
				} else {
					if (t.getBannerState().equals("0")) {
						agentBanner.setSeq(banner.getSeq());
						agentBanner.setAgentId(obj);
						agentBanner.setSbannerId(banner.getId());
						if (banner.getLinkType().equals("1")) {
							agentBanner.setPcLink(ptpath + "/" + banner.getObjId());
						} else {
							if (banner.getPcLink() != null) {
								agentBanner.setPcLink(banner.getPcLink());
							}
						}
						if (banner.getPcLogo() != null) {
							agentBanner.setPcLogo(banner.getPcLogo());
						}
						agentBanner.setType("0");
						agentBanner.setAuditstate(" ");
						agentBanner.setHideState("0");
						if (banner.getWxLogo() != null) {
							agentBanner.setWxLogo(banner.getWxLogo());
						}
						if (banner.getPcLink() != null) {
							agentBanner.setWxLink(banner.getPcLink());
						}
						agentBanner.setCreateTime(banner.getCreateTime());
						agentBanner.setUpdateTime(banner.getUpdateTime());
						agentBannerService.save(agentBanner);
						// agentBannerService.openSession();
						// 批插入的对象立即写入数据库并释放内存

						if (i % 1 == 0) {
							agentBannerService.getSession().flush();
							agentBannerService.getSession().clear();
						}

					}

				}
			}
			// agentBannerService.getSession().getTransaction().commit(); //
			// 提交事物
		}
		/*
		 * if (agentBannerService.getSession() != null) {
		 * agentBannerService.getSession().close(); }
		 */

		return j;
	}

	/**
	 * Banner列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateBanner")
	public ModelAndView addorupdateBanner(Banner banner, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(banner.getId())) {
			banner = bannerService.getEntity(Banner.class, banner.getId());
			req.setAttribute("bannerPage", banner);
		}
		return new ModelAndView("plugin/banner/bannerPage").addObject("server", server);
	}
}
