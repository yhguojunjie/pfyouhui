package com.yoxi.jgframework.ui.tag.core.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yoxi.jgframework.core.tag.UploadTagUtils;

/**
 * 
 * 类描述：上传标签
 * 
 * @author: com.yoxi.jgframework
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class UploadTag extends TagSupport {
	private static final long serialVersionUID = 8707903803936858259L;
	/**
	 * html 控件 ID
	 */
	protected String id;// ID
	/**
	 * 上传URL
	 */
	protected String uploader;// url
	/**
	 * html 控件名称
	 */
	protected String name;// 控件名称
	/**
	 * 参数名称
	 */
	protected String formData;// 参数名称
	/**
	 * 限制上传文件的扩展名
	 */
	protected String extend = "";// 上传文件的扩展名
	/**
	 * 限制文件上传大大小
	 */
	protected Integer fileSizeLimit = 50;
	/**
	 * 限制文件上传大大小的单位（默认MB）
	 */
	protected String fileSizeLimitUnit ="MB";
	/**
	 * 浏览本地文件按钮，显示的文字
	 */
	protected String buttonText = "浏览";// 按钮文本
	/**
	 * 是否允许选择上传都个文件
	 */
	protected boolean multi = true;// 是否多文件
	/**
	 * 文件容器ID，是父Html控件ID
	 */
	protected String queueID = "filediv";// 文件容器ID
	/**
	 * 是否弹出窗口选择模式
	 */
	protected boolean dialog = true;// 是否是弹出窗口模式
	/**
	 * 回调函数
	 */
	protected String callback;
	/**
	 * 是否允许自动上传
	 */
	protected boolean auto = false;// 是否自动上传
	/**
	 * 上传成功处理函数,比如保存数据库 functionName() or functionName(d,file,response)
	 * d返回的提示数据，file文件相关信息
	 */
	protected String onUploadSuccess;// 上传成功处理函数
	/**
	 * 是否生成查看删除链接
	 */
	protected boolean view = false;// 生成查看删除链接
	/**
	 * 返回url展示控件Id
	 */
	protected String showUrlId;

	public void setShowUrlId(String showUrlId) {
		this.showUrlId = showUrlId;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public void setOnUploadSuccess(String onUploadSuccess) {
		this.onUploadSuccess = onUploadSuccess;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}

	public void setQueueID(String queueID) {
		this.queueID = queueID;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public void setMulti(boolean multi) {
		this.multi = multi;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	/**
	 * 形成标签元素内容
	 */
	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * 形成标签元素内容
	 * 
	 * @return 返回Html标签字符串
	 */
	public StringBuffer end() {
		return UploadTagUtils.end(pageContext, id, uploader, name, formData, extend, fileSizeLimit,fileSizeLimitUnit, buttonText, multi, queueID, dialog, callback, auto, onUploadSuccess, view, showUrlId);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public void setFileSizeLimit(Integer fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}

	public String getFileSizeLimitUnit() {
		return fileSizeLimitUnit;
	}

	public void setFileSizeLimitUnit(String fileSizeLimitUnit) {
		this.fileSizeLimitUnit = fileSizeLimitUnit;
	}

	
}
