package com.yoxi.pfhudongtui.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;

/**
 * 代理商VO
 * 
 * @author wql
 *
 */
@Entity
@Table(name = "v_agentinfo")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class VAgentInfo extends IdUserDefinedEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = -8822471793953601531L;
	
	private String userName;
	private Short status;// 状态1：在线,2：离线,0：禁用
	private String departid;
	
	/**代理商用id*/
	private java.lang.Integer id;
	/**公司名称*/
	private java.lang.String companyName;
	/**省*/
	private java.lang.String province;
	/**市*/
	private java.lang.String city;
	/**区*/
	private java.lang.String district;
	/**地址*/
	private java.lang.String address;
	/**办公电话*/
	private java.lang.String telephone;
	/**传真号码*/
	private java.lang.String fax;
	/**手机号*/
	private java.lang.String mobile;
	/**联系人*/
	private java.lang.String contract;
	/**logoURL*/
	private java.lang.String logo;
	/**logo描述*/
	private java.lang.String logoDesc;
	/**公众号二维码URL*/
	private java.lang.String wxqrcode;
	/**关注地址*/
	private java.lang.String focusAdd;
	/**指向域名*/
	private java.lang.String mydomain;
	/**版权信息*/
	private java.lang.String version;
	/**其它资料文件Url*/
	private java.lang.String filePath;
	/**支付宝账号*/
	private java.lang.String alipay;
	/**当前账户余额*/
	private java.lang.Double blance;
	/**累计收益*/
	private java.lang.Double totalIncome;
	/**累计提现*/
	private java.lang.Double totalCash;
	/**银行账号*/
	private java.lang.String bankAccount;
	/**银行类型*/
	private java.lang.String bankType;
	/**创建时间*/
	private java.util.Date createTime;
	/**更新时间*/
	private java.util.Date updateTime;
	/**上次欠费时间*/
	private java.util.Date debtTime;
	
	
	@Column(name = "userName", nullable = true, length = 50)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "status", nullable = true, length = 6)
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	@Column(name = "departid", nullable = true, length = 8)
	public String getDepartid() {
		return departid;
	}
	public void setDepartid(String departid) {
		this.departid = departid;
	}
	
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  代理商用id
	 */
	
	@Id
	@Column(name ="id",nullable=false,precision=10,scale=0)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  代理商用id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司名称
	 */
	@Column(name ="companyName",nullable=true,length=100)
	public java.lang.String getCompanyName(){
		return this.companyName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司名称
	 */
	public void setCompanyName(java.lang.String companyName){
		this.companyName = companyName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省
	 */
	@Column(name ="province",nullable=true,length=20)
	public java.lang.String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省
	 */
	public void setProvince(java.lang.String province){
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  市
	 */
	@Column(name ="city",nullable=true,length=20)
	public java.lang.String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区
	 */
	@Column(name ="district",nullable=true,length=20)
	public java.lang.String getDistrict(){
		return this.district;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  区
	 */
	public void setDistrict(java.lang.String district){
		this.district = district;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="address",nullable=true,length=100)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  办公电话
	 */
	@Column(name ="telephone",nullable=true,length=15)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  办公电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  传真号码
	 */
	@Column(name ="fax",nullable=true,length=15)
	public java.lang.String getFax(){
		return this.fax;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  传真号码
	 */
	public void setFax(java.lang.String fax){
		this.fax = fax;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */
	@Column(name ="mobile",nullable=true,length=15)
	public java.lang.String getMobile(){
		return this.mobile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setMobile(java.lang.String mobile){
		this.mobile = mobile;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */
	@Column(name ="contract",nullable=true,length=30)
	public java.lang.String getContract(){
		return this.contract;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人
	 */
	public void setContract(java.lang.String contract){
		this.contract = contract;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  logoURL
	 */
	@Column(name ="logo",nullable=true,length=100)
	public java.lang.String getLogo() {
		return logo;
	}
	
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  logoURL
	 */
	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}
	

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  logo描述
	 */
	@Column(name ="logoDesc",nullable=true,length=50)
	public java.lang.String getLogoDesc(){
		return this.logoDesc;
	}

	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  logo描述
	 */
	public void setLogoDesc(java.lang.String logoDesc){
		this.logoDesc = logoDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众号二维码URL
	 */
	@Column(name ="wxqrcode",nullable=true,length=100)
	public java.lang.String getWxqrcode(){
		return this.wxqrcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众号二维码URL
	 */
	public void setWxqrcode(java.lang.String wxqrcode){
		this.wxqrcode = wxqrcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关注地址
	 */
	@Column(name ="focusAdd",nullable=true,length=100)
	public java.lang.String getFocusAdd(){
		return this.focusAdd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关注地址
	 */
	public void setFocusAdd(java.lang.String focusAdd){
		this.focusAdd = focusAdd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  指向域名
	 */
	@Column(name ="mydomain",nullable=true,length=100)
	public java.lang.String getMydomain(){
		return this.mydomain;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  指向域名
	 */
	public void setMydomain(java.lang.String mydomain){
		this.mydomain = mydomain;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版权信息
	 */
	@Column(name ="version",nullable=true,length=250)
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版权信息
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其它资料文件Url
	 */
	@Column(name ="filePath",nullable=true,length=150)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其它资料文件Url
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付宝账号
	 */
	@Column(name ="alipay",nullable=true,length=30)
	public java.lang.String getAlipay(){
		return this.alipay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付宝账号
	 */
	public void setAlipay(java.lang.String alipay){
		this.alipay = alipay;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  当前账户余额
	 */
	@Column(name ="blance",nullable=true,precision=12,scale=2)
	public java.lang.Double getBlance(){
		return this.blance;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  当前账户余额
	 */
	public void setBlance(java.lang.Double blance){
		this.blance = blance;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  累计收益
	 */
	@Column(name ="totalIncome",nullable=true,precision=12,scale=2)
	public java.lang.Double getTotalIncome(){
		return this.totalIncome;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  累计收益
	 */
	public void setTotalIncome(java.lang.Double totalIncome){
		this.totalIncome = totalIncome;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  累计提现
	 */
	@Column(name ="totalCash",nullable=true,precision=12,scale=2)
	public java.lang.Double getTotalCash(){
		return this.totalCash;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  累计提现
	 */
	public void setTotalCash(java.lang.Double totalCash){
		this.totalCash = totalCash;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行账号
	 */
	@Column(name ="bankAccount",nullable=true,length=30)
	public java.lang.String getBankAccount(){
		return this.bankAccount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行账号
	 */
	public void setBankAccount(java.lang.String bankAccount){
		this.bankAccount = bankAccount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行类型
	 */
	@Column(name ="bankType",nullable=true,length=2)
	public java.lang.String getBankType(){
		return this.bankType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行类型
	 */
	public void setBankType(java.lang.String bankType){
		this.bankType = bankType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="createTime",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */
	@Column(name ="updateTime",nullable=true)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	
	@Column(name ="debtTime",nullable=true)
	public java.util.Date getDebtTime() {
		return debtTime;
	}
	public void setDebtTime(java.util.Date debtTime) {
		this.debtTime = debtTime;
	}
	
	
	
}
