package com.yoxi.jgframework.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.pfhudongtui.utils.PropertiesUtils;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.util.FastDFSUtils;
import com.yoxi.jgframework.util.UploadUtils;
import com.yoxi.jgframework.util.oConvertUtils;



/**
 * 类型字段处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/systemController")
public class SystemController extends BaseServiceController {
	
	private static final Logger logger = Logger.getLogger(SystemController.class);
	
	
	/**
	 * 保存文件
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(@RequestParam("Filedata") final MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String uploadLocation = oConvertUtils.getString(request.getParameter("uploadLocation"));
		String showUrl="";
		if("server".equals(uploadLocation)){//上传到服务器
			try {
				long a=System.currentTimeMillis();
				showUrl = FastDFSUtils.uploadFile(file.getInputStream(), file.getOriginalFilename());
				long b=System.currentTimeMillis()-a;
				System.out.println("-----------"+b/1000);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{//上传到本机
			UploadUtils uploadUtils = new UploadUtils();
			showUrl = uploadUtils.autoUploadFile(file,request)[4];
		}
		Map<String, Object> attributes = new HashMap<String, Object>();
		logger.info(file.getOriginalFilename() + ",size=" + file.getSize() + ",contentType"
				+ file.getContentType());
		if(oConvertUtils.isEmpty(showUrl)){
			this.message = "文件添加失败";
			j.setSuccess(false);
		}else{
			this.message = "文件添加成功";
			j.setMsg("文件添加成功");
		}
		
		attributes.put("showUrlId",showUrl);//上传图片的url
		j.setAttributes(attributes);
		return j;
	}

	

	
/*	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));// 文件ID
		String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));// 文件标题
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			String fileName = file.getOriginalFilename();// 获取文件名
		}
		//attributes.put("fileKey", document.getId());
		//attributes.put("name", document.getAttachmenttitle());
		attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + fileKey);
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + fileKey);
		j.setMsg("文件添加成功");
		j.setAttributes(attributes);

		return j;
	}*/
	
	/**
	 * 保存kindEditor富文本编辑上传图片文件
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "editerImg", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView editerImg(@RequestParam("imgFile") final MultipartFile file,
			HttpServletRequest request, HttpServletResponse response){
		String uploadLocation = oConvertUtils.getString(request.getParameter("uploadLocation"));
		String showUrl="";
		if("server".equals(uploadLocation)){//上传到服务器
			try {
				long a=System.currentTimeMillis();
				showUrl = FastDFSUtils.uploadFile(file.getInputStream(), file.getOriginalFilename());
				long b=System.currentTimeMillis()-a;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{//上传到本机
			UploadUtils uploadUtils = new UploadUtils();
			showUrl = uploadUtils.autoUploadFile(file,request)[4];
		}
		Map<String, Object> attributes = new HashMap<String, Object>();
		logger.info(file.getOriginalFilename() + ",size=" + file.getSize() + ",contentType"
				+ file.getContentType());
		JSONObject obj = new JSONObject();
		if(oConvertUtils.isEmpty(showUrl)){
			this.message = "文件添加失败";
			obj.put("error", 1);
			obj.put("message", message);
		}else{
			obj.put("error", 0);
			obj.put("url", PropertiesUtils.getPara("fastDFS.server")+"/"+showUrl);
		}
		try {
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
