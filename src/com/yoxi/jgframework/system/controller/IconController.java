package com.yoxi.jgframework.system.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.annotation.OtherAnnotation;
import com.yoxi.jgframework.annotation.UploadAnnotation;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.common.UploadFile;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.ComboBox;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.system.entity.TSIcon;
import com.yoxi.jgframework.system.entity.TSOperation;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * 图标信息处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/iconController")
public class IconController extends BaseServiceController {
	 

	/**
	 * 图标列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "icon")
	public ModelAndView icon() {
		return new ModelAndView("system/icon/iconList");
	}

	/**
	 * easyuiAJAX请求数据,获取图标Grid数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "iconGrid")
	public void iconGrid(TSIcon icon,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSIcon.class, dataGrid);
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, icon);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除图标
	 * 
	 * @param icon
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "delIcon")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delIcon(TSIcon icon, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		icon = systemService.getEntity(TSIcon.class, icon.getId());
		
		//----------------------------------------------------------------
		//update-begin--Author:panfugen  Date:20130319 for：删除图标时先检查该图标是否正在使用
		
		boolean isPermit=isPermitDel(icon);
		
		if(isPermit){
			this.upEntity(icon);
			
			systemService.delete(icon);
			
			message = "图标: " + icon.getIconName() + "被删除成功。";
			
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			
			return j;
		}
		
		message = "图标: " + icon.getIconName() + "正在使用，不允许删除。";

		j.setMsg(message);
		//update-end--Author:panfugen    Date:20130319 for：删除图标时先检查该图标是否正在使用
		//----------------------------------------------------------------
		
		return j;
	}
	
	/**
	 * 图标修改页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdateIcon")
	public ModelAndView addorupdateIcon(TSIcon icon, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(icon.getId())) {
			icon = systemService.getEntity(TSIcon.class, icon.getId());
			req.setAttribute("icon", icon);
		}
		return new ModelAndView("system/icon/icons");
	}

	/**
	 * 上传并保存图标
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveIcon", method = RequestMethod.POST)
	@ResponseBody
	@UploadAnnotation
	public AjaxJson saveIcon(HttpServletRequest request) throws Exception {
		AjaxJson j = new AjaxJson();
		
		TSIcon icon = new TSIcon();
		Short iconType = oConvertUtils.getShort(request.getParameter("iconType"));
		String iconName = oConvertUtils.getString(request.getParameter("iconName"));
		icon.setIconName(iconName);
		icon.setIconType(iconType);
		// uploadFile.setBasePath("images/accordion");
		UploadFile uploadFile = new UploadFile(request, icon);
		uploadFile.setCusPath("plug-in/accordion/images");
		uploadFile.setExtend("extend");
		uploadFile.setTitleField("iconclas");
		uploadFile.setRealPath("iconPath");
		uploadFile.setObject(icon);
		uploadFile.setByteField("iconContent");
		uploadFile.setRename(false);
		systemService.uploadFile(uploadFile);
		// 图标的css样式
		String css = "." + icon.getIconClas() + "{background:url('../images/" + icon.getIconClas() + "." + icon.getExtend() + "') no-repeat}";
		writeIconCss(request, css);
		message = "图标：" + iconName + "上传成功";
		j.setMsg(message);
		return j;
	}

	
	/**
	 * 根据数据库内容，生成图表样式
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "generateCss")
	@ResponseBody
	@OtherAnnotation
	public AjaxJson generateCss(HttpServletRequest request) throws Exception {
		AjaxJson json = new AjaxJson();
		List<TSIcon> icons = systemService.loadAll(TSIcon.class);
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		String csspath = request.getSession().getServletContext().getRealPath("/plug-in/accordion/css/icons.css");
		// 清空CSS文件内容
		clearFile(csspath);
		for (TSIcon c : icons) {
			File file = new File(rootpath + c.getIconPath());
			if (!file.exists()) {
				byte[] content = c.getIconContent();
				if (content != null) {
					BufferedImage imag = ImageIO.read(new ByteArrayInputStream(content));
					ImageIO.write(imag, "PNG", file);// 输出到 png 文件
				}
			}
			String css = "." + c.getIconClas() + "{background:url('../images/" + c.getIconClas() + "." + c.getExtend() + "') no-repeat}";
			writeIconCss(request, css);
			json.setMsg("样式表创建成功");
			this.message = "样式表创建成功";
		}
		this.message = "样式表创建失败";
		json.setSuccess(true);
		return json;
	}

	/**
	 * 写入css文件
	 * 
	 * @param request
	 * @param css
	 */
	private void writeIconCss(HttpServletRequest request, String css) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/plug-in/accordion/css/icons.css");
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter out = new FileWriter(file, true);
			out.write("\r\n");
			out.write(css);
			out.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 清空文件内容
	 * 
	 * @param request
	 * @param css
	 */
	private void clearFile(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			fos.write("".getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * 检查是否允许删除该图标。
	 * @param icon 图标。
	 * @return true允许；false不允许；
	 */
	private boolean isPermitDel(TSIcon icon) {
		List<TSFunction> functions = systemService.findByProperty(TSFunction.class, "TSIcon.id", icon.getId());
		if (functions==null||functions.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取菜单图标ComboBox数据
	 */
	@RequestMapping(params = "getComboxIcons")
	@ResponseBody
	public List<ComboBox> getComboxIcons(HttpServletRequest request){
		List<TSIcon> iconlist = null;
		Integer iconId = oConvertUtils.getInt(request.getParameter("id"));
		//图标类型
		Short iconType = oConvertUtils.getShort(request.getParameter("iconType"));
		if(StringUtil.isNotEmpty(iconType)){
			//根据图标类型获取数据
			iconlist = systemService.findByProperty(TSIcon.class,"iconType",iconType);
		}else{
			//返回所有图标数据
			iconlist =systemService.getList(TSIcon.class);
		}
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		for (TSIcon tsIcon : iconlist) {
			ComboBox comboBox = new ComboBox();
			comboBox.setId(tsIcon.getId().toString());
			comboBox.setText(tsIcon.getIconName());
			
			//编辑页面下根据iconId选择初始值
			if(StringUtil.isNotEmpty(iconId) && tsIcon.getId() == iconId){
				comboBox.setSelected(true);
			}
			comboBoxs.add(comboBox);
		}
		return comboBoxs;
	}
	
	

	public void upEntity(TSIcon icon) {
		List<TSFunction> functions = systemService.findByProperty(TSFunction.class, "TSIcon.id", icon.getId());
		if (functions.size() > 0) {
			for (TSFunction tsFunction : functions) {
				tsFunction.setTSIcon(null);
				systemService.saveOrUpdate(tsFunction);
			}
		}
		List<TSOperation> operations = systemService.findByProperty(TSOperation.class, "TSIcon.id", icon.getId());
		if (operations.size() > 0) {
			for (TSOperation tsOperation : operations) {
				tsOperation.setTSIcon(null);
				systemService.saveOrUpdate(tsOperation);
			}
		}
	}


	
}
