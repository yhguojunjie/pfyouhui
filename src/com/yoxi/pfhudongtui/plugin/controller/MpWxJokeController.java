package com.yoxi.pfhudongtui.plugin.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.ReadablePartialPrinter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.util.UploadUtils;
import com.yoxi.jgframework.util.oConvertUtils;
import com.yoxi.pfhudongtui.utils.FastDFSUtils;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: MpWxJoke
 * @author jwhat generate
 * @date 2014-06-26 17:05:03
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/mpWxJokeController")
public class MpWxJokeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MpWxJokeController.class);
	/**
	 * MpWxJoke列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mpWxJoke")
	public ModelAndView mpWxJoke(HttpServletRequest request) {
		return new ModelAndView("mp/weixin/joke/mpWxJokeList");
	}
	/**
	 * 保存富文本编辑上传图片文件
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
			String local = PropertiesUtils.getPara("fileAccessPath");
			obj.put("url", local+"/"+showUrl);
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
