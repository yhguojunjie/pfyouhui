package com.yoxi.jgframework.common.model.json;

import java.util.Map;

/**
 * 树形结构数据源 元素
 * @author william
 *
 */
public class TreeGrid implements java.io.Serializable {
	/**
	 * 主键ＩＤ
	 */
	private String id;
	/**
	 * 主键文本
	 */
	private String text;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 图标
	 */
 	private String icon;
 	/**
 	 * 说明
 	 */
 	private String src;
 	/**
 	 * 排序
 	 */
 	private Integer order;//排序
 	
 	/**
 	 * 父ID
 	 */
 	private String parentId;
 	/**
 	 * 父ID对应的文本
 	 */
 	private String parentText;
 	
 	/*
 	 * 预留
 	 */
 	private String note;
 	/**
 	 * 其他参数
 	 */
	private Map<String,String> attributes;// 其他参数
	/**
	 * 其他参数
	 */
 	private String  operations;// 其他参数
 	/**
 	 * 是否展开(open,closed)
 	 */
 	private String state = "open";// 是否展开(open,closed)
 	
 	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getOperations() {
		return operations;
	}
	public void setOperations(String operations) {
		this.operations = operations;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public String getParentText() {
		return parentText;
	}
	public void setParentText(String parentText) {
		this.parentText = parentText;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	 
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}	 
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
 
}
