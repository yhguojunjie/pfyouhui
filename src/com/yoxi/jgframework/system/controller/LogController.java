package com.yoxi.jgframework.system.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.common.model.json.Highchart;
import com.yoxi.jgframework.system.entity.TSLog;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.DateUtils;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * 日志处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/logController")
public class LogController extends BaseServiceController {

	/**
	 * 日志列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "log")	
	public ModelAndView log() {
		return new ModelAndView("system/log/logList");
	}

	/**
	 * easyuiAJAX请求数据 ，获取操作日志Grid数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "logGrid")
	public void logGrid(TSLog aTSLog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSLog.class, dataGrid);
		String satrTime = request.getParameter("beginOperateTime");
		String endTime = request.getParameter("endOperateTime");
		if (oConvertUtils.isEmpty(satrTime) == false){
			cq.ge("operateTime", DateUtils.str2Date(satrTime, DateUtils.date_sdf));
		}
		if (oConvertUtils.isEmpty(endTime) == false){
			Date endDate = DateUtils.str2Date(endTime, DateUtils.date_sdf);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			//增加一天
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			endDate = calendar.getTime();
			cq.le("operateTime",endDate);
		}
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aTSLog);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 用户分析页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "statisticTabs")
	public ModelAndView statisticTabs(HttpServletRequest request) {
		return new ModelAndView("system/log/statisticTabs");
	}
	/**
	 * 用户浏览器使用统计图
	 * 
	 * @return
	 */
	@RequestMapping(params = "userBroswer")
	public ModelAndView userBroswer(String reportType, HttpServletRequest request) {
		request.setAttribute("reportType", reportType);
		if("pie".equals(reportType)){
			return new ModelAndView("system/log/userBroswerPie");
		}else if("line".equals(reportType)) {
			return new ModelAndView("system/log/userBroswerLine");
		}
		return new ModelAndView("system/log/userBroswer");
	}

	/**
	 * 报表数据生成
	 * 
	 * @return
	 */
	@RequestMapping(params = "getBroswerBar")
	@ResponseBody
	public List<Highchart> getBroswerBar(HttpServletRequest request,String reportType, HttpServletResponse response) {
		List<Highchart> list = new ArrayList<Highchart>();
		Highchart hc = new Highchart();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT broswer ,count(broswer) FROM TSLog group by broswer");
		List userBroswerList = systemService.findByQueryString(sb.toString());
		Long count = systemService.getCountForJdbc("SELECT COUNT(1) FROM t_s_log WHERE 1=1");
		List lt = new ArrayList();
		hc = new Highchart();
		hc.setName("用户浏览器统计分析");
		hc.setType(reportType);
		Map<String, Object> map;
		if (userBroswerList.size() > 0) {
			for (Object object : userBroswerList) {
				map = new HashMap<String, Object>();
				Object[] obj = (Object[]) object;
				map.put("name", obj[0]);
				map.put("y", obj[1]);
				Long groupCount = (Long) obj[1];
				Double  percentage = 0.0;
				if (count != null && count.intValue() != 0) {
					percentage = new Double(groupCount)/count;
				}
				map.put("percentage", percentage*100);
				lt.add(map);
			}
		}
		hc.setData(lt);
		list.add(hc);
		return list;
	}
	
	/**
	 * hightchart导出图片
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
/*	@RequestMapping(params = "export")
	public void export(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		String svg = request.getParameter("svg");
		String filename = request.getParameter("filename");

		filename = filename == null ? "chart" : filename;
		ServletOutputStream out = response.getOutputStream();
		try {
			if (null != type && null != svg) {
				svg = svg.replaceAll(":rect", "rect");
				String ext = "";
				Transcoder t = null;
				if (type.equals("image/png")) {
					ext = "png";
					t = new PNGTranscoder();
				} else if (type.equals("image/jpeg")) {
					ext = "jpg";
					t = new JPEGTranscoder();
				} else if (type.equals("application/pdf")) {
					ext = "pdf";
					t = (Transcoder) new PDFTranscoder();
				} else if (type.equals("image/svg+xml"))
					ext = "svg";
				response.addHeader("Content-Disposition",
						"attachment; filename=" + new String(filename.getBytes("GBK"),"ISO-8859-1") + "." + ext);
				response.addHeader("Content-Type", type);

				if (null != t) {
					TranscoderInput input = new TranscoderInput(
							new StringReader(svg));
					TranscoderOutput output = new TranscoderOutput(out);

					try {
						t.transcode(input, output);
					} catch (TranscoderException e) {
						out
								.print("Problem transcoding stream. See the web logs for more details.");
						e.printStackTrace();
					}
				} else if (ext.equals("svg")) {
					// out.print(svg);
					OutputStreamWriter writer = new OutputStreamWriter(out,
							"UTF-8");
					writer.append(svg);
					writer.close();
				} else
					out.print("Invalid type: " + type);
			} else {
				response.addHeader("Content-Type", "text/html");
				out
						.println("Usage:\n\tParameter [svg]: The DOM Element to be converted."
								+ "\n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
			}
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}*/

}
