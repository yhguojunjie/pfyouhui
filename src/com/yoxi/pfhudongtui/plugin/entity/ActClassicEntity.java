package com.yoxi.pfhudongtui.plugin.entity;

public class ActClassicEntity {
	/**经典活动Id**/
	private Integer id;
	/***排序**/
	private Integer seq;
	/**插件活动图标**/
	private String actIcon;
	/**活动名称**/
	private String actTitle;
	/**品牌logo**/
	private String bannerLogo;
	/**品牌名称**/
	private String bannerName;
	/**类型1选择活动2手动设置**/
	private String type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActIcon() {
		return actIcon;
	}
	public void setActIcon(String actIcon) {
		this.actIcon = actIcon;
	}
	public String getActTitle() {
		return actTitle;
	}
	public void setActTitle(String actTitle) {
		this.actTitle = actTitle;
	}
	public String getBannerLogo() {
		return bannerLogo;
	}
	public void setBannerLogo(String bannerLogo) {
		this.bannerLogo = bannerLogo;
	}
	public String getBannerName() {
		return bannerName;
	}
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
