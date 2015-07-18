package com.yoxi.jgframework.core.tag;

import com.yoxi.jgframework.core.utils.ConvertUtils;

/**
 * 
 * 加载js、css等资源工具
 *
 */
public class BaseTagUtils {
	
	public static StringBuffer getjs(String type) {
		StringBuffer sb = new StringBuffer();
		String types[] = type.split(",");
		if (ConvertUtils.isIn("jquery", types)) {
			sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
		}
		if (ConvertUtils.isIn("easyui", types)) {
			// sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/jquery.cookie.js\"></script>");
			// sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/changeEasyuiTheme.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
			sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/bootstrap/easyui.css\" type=\"text/css\"></link>");
			sb.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
			sb.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/color.css\" type=\"text/css\"></link>");
//			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\">");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.4.1.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/easyui-lang-zh_CN.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
		}
		if (ConvertUtils.isIn("DatePicker", types)) {
			sb.append("<script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script>");
		}
		if (ConvertUtils.isIn("jqueryui", types)) {
			// ----------------------------------------------------------------
			// update-begin--Author:zhangdaihao Date:20130205 for：自动补全
			// sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/jquery-1.8.3.js\"></script>");
			sb.append("<link rel=\"stylesheet\" href=\"plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.min.css\" type=\"text/css\"></link>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-ui/js/jquery-ui-1.9.2.custom.min.js\"></script>");
		}
		if (ConvertUtils.isIn("prohibit", types)) {
			sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/prohibitutil.js\"></script>");
		}
		if (ConvertUtils.isIn("designer", types)) {
			sb.append("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/jquery-1.7.2.min.js\"></script>");
			sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/designer/easyui/easyui.css\" type=\"text/css\"></link>");
			sb.append("<link rel=\"stylesheet\" href=\"plug-in/designer/easyui/icon.css\" type=\"text/css\"></link>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/jquery.easyui.min.1.3.0.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/designer/easyui/locale/easyui-lang-zh_CN.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
			sb.append("<script type=\'text/javascript\' src=\'plug-in/jquery/jquery-autocomplete/lib/jquery.bgiframe.min.js\'></script>");
			sb.append("<script type=\'text/javascript\' src=\'plug-in/jquery/jquery-autocomplete/lib/jquery.ajaxQueue.js\'></script>");
			sb.append("<script type=\'text/javascript\' src=\'plug-in/jquery/jquery-autocomplete/jquery.autocomplete.min.js\'></script>");
			sb.append("<link href=\"plug-in/designer/designer.css\" type=\"text/css\" rel=\"stylesheet\" />");
			sb.append("<script src=\"plug-in/designer/draw2d/wz_jsgraphics.js\"></script>");
			sb.append("<script src=\'plug-in/designer/draw2d/mootools.js\'></script>");
			sb.append("<script src=\'plug-in/designer/draw2d/moocanvas.js\'></script>");
			sb.append("<script src=\'plug-in/designer/draw2d/draw2d.js\'></script>");
			sb.append("<script src=\"plug-in/designer/MyCanvas.js\"></script>");
			sb.append("<script src=\"plug-in/designer/ResizeImage.js\"></script>");
			sb.append("<script src=\"plug-in/designer/event/Start.js\"></script>");
			sb.append("<script src=\"plug-in/designer/event/End.js\"></script>");
			sb.append("<script src=\"plug-in/designer/connection/MyInputPort.js\"></script>");
			sb.append("<script src=\"plug-in/designer/connection/MyOutputPort.js\"></script>");
			sb.append("<script src=\"plug-in/designer/connection/DecoratedConnection.js\"></script>");
			sb.append("<script src=\"plug-in/designer/task/Task.js\"></script>");
			sb.append("<script src=\"plug-in/designer/task/UserTask.js\"></script>");
			sb.append("<script src=\"plug-in/designer/task/ManualTask.js\"></script>");
			sb.append("<script src=\"plug-in/designer/task/ServiceTask.js\"></script>");
			sb.append("<script src=\"plug-in/designer/gateway/ExclusiveGateway.js\"></script>");
			sb.append("<script src=\"plug-in/designer/gateway/ParallelGateway.js\"></script>");
			sb.append("<script src=\"plug-in/designer/boundaryevent/TimerBoundary.js\"></script>");
			sb.append("<script src=\"plug-in/designer/boundaryevent/ErrorBoundary.js\"></script>");
			sb.append("<script src=\"plug-in/designer/subprocess/CallActivity.js\"></script>");
			sb.append("<script src=\"plug-in/designer/task/ScriptTask.js\"></script>");
			sb.append("<script src=\"plug-in/designer/task/MailTask.js\"></script>");
			sb.append("<script src=\"plug-in/designer/task/ReceiveTask.js\"></script>");
			sb.append("<script src=\"plug-in/designer/task/BusinessRuleTask.js\"></script>");
			sb.append("<script src=\"plug-in/designer/designer.js\"></script>");
			sb.append("<script src=\"plug-in/designer/mydesigner.js\"></script>");
		}
		if (ConvertUtils.isIn("tools", types)) {
			// ----begin -----Author:邢双阳
			// ---日期：2013-5-14----for：取消lhgDiaglog的风格设置参数，增加页面加载性能-----
			sb.append("<link rel=\"stylesheet\" href=\"plug-in/lhgDialog/skins/jtop.css\" type=\"text/css\"></link>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script>");
			// ----end -----Author:邢双阳
			// ---日期：2013-5-14----for：取消lhgDiaglog的风格设置参数，增加页面加载性能-----
			// sb.append("<script type=\"text/javascript\" src=\"plug-in/artDiglog/plugins/iframeTools.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/curdtools.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>");
		}
		if (ConvertUtils.isIn("toptip", types)) {
			sb.append("<link rel=\"stylesheet\" href=\"plug-in/toptip/css/css.css\" type=\"text/css\"></link>");
			sb.append("<script type=\"text/javascript\" src=\"plug-in/toptip/manhua_msgTips.js\"></script>");
		}
		// sb.append("<link rel=\"stylesheet\" href=\"resources/css/base.css\" type=\"text/css\"></link>");
		// sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/myplug/easycurd.js\"></script>");
		// sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/myplug/mask.js\"></script>");
		// sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/myplug/windowControl.js\"></script>");
		return sb;
	}
}
