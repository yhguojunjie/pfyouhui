package com.yoxi.pfhudongtui.vo.plugin;

public class PluginVO {

	private String icon;
	private String name;

	private Integer objId;

	private String onlineState;

	private Integer price;
	private String realname;
	private Integer salePrice;
	public String getIcon() {
		return icon;
	}
	public String getName() {
		return name;
	}

	public Integer getObjId() {
		return objId;
	}

	public String getOnlineState() {
		return onlineState;
	}

	public Integer getPrice() {
		return price;
	}

	public String getRealname() {
		return realname;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setObjId(Integer objId) {
		this.objId = objId;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}

}
