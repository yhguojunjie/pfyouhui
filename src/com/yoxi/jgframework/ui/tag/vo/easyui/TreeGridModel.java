package com.yoxi.jgframework.ui.tag.vo.easyui;

 /**
 * 
 * @ClassName: TreeGridModel 
 * @Description: TODO(树形列表模型设置类) 
 * @author com.yoxi.jgframework 
 * @date 2013-1-6 下午07:24:22 
 *
  */
public class TreeGridModel implements java.io.Serializable {
	private static final long serialVersionUID = 1809363432244235004L;
	/**
	 * 主键ID 字段映射
	 */
	private String idField;//主键ID 字段映射
	/**
	 * 主键对应的文本   字段映射
	 */
	private String textField; //主键对应的文本   字段映射
	/**
	 * 代码  字段映射
	 */
	private String code; //代码  字段映射
	/**
	 * 说明  字段映射
	 */
 	private String src;//说明  字段映射
 	/**
 	 * 图标  字段映射
 	 */
 	private String icon;//图标  字段映射
 	/**
 	 * 顺序  字段映射
 	 */
 	private String order;//顺序  字段映射
 	
 	/**
 	 * 子节点内容 字段映射
 	 */
	private String childList; //子节点内容 字段映射
	/**
	 * 父ID  字段映射
	 */
 	private String parentId;  //父ID  字段映射
 	/**
 	 * 父ID对应的文本  字段映射
 	 */
 	private String parentText; //父ID对应的文本  字段映射
 
 	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
	 
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}	
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getTextField() {
		return textField;
	}
	public void setTextField(String textField) {
		this.textField = textField;
	}
	public String getChildList() {
		return childList;
	}
	public void setChildList(String childList) {
		this.childList = childList;
	}
}
