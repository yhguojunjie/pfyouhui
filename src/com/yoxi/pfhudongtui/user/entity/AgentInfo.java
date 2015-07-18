package com.yoxi.pfhudongtui.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;


/**   
 * @Title: Entity
 * @Description: AgentInfo
 * @author jwhat generate
 * @date 2015-02-05 17:06:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_agent_info", schema = "")
@SuppressWarnings("serial")
public class AgentInfo extends IdUserDefinedEntity implements java.io.Serializable {
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
	/**上一次欠费时间***/
	private java.util.Date debtTime;
	/**0：否 1：是**/
	private java.lang.String isOfficial;
	/**网站icon**/
	private java.lang.String websiteIcon;
	/**网站描述**/
	private java.lang.String websiteDesc;
	/**网站标题**/
	private java.lang.String websiteTitle;
	/**网站关键字**/
	private java.lang.String websiteKeyword;
	/**分享跳转域名**/
	private java.lang.String redirecDomain;
	/**客服电话**/
	private java.lang.String servicePhone;
	/**客服邮箱**/
	private java.lang.String serviceEmail;
	/**客服QQ**/
	private java.lang.String serviceqq;
	/**客服QQ群**/
	private java.lang.String qqGroup;
	/**代理商QQ**/
	private java.lang.String qq;
	/**代理商email**/
	private java.lang.String email;
	/**网站备案**/
	private java.lang.String webRecord;
	/**转发域名**/
	private java.lang.String forwardDomain;
	/**支付宝-partner**/
	private java.lang.String aliPartner;
	/**支付宝-key**/
	private java.lang.String aliKey;
	/**支付宝-收款账号**/
	private java.lang.String aliSellerAccount;
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
	@Column(name ="isOfficial",nullable=true)
	public java.lang.String getIsOfficial() {
		return isOfficial;
	}

	public void setIsOfficial(java.lang.String isOfficial) {
		this.isOfficial = isOfficial;
	}

	@Column(name ="websiteIcon",nullable=true,length=100)
	public java.lang.String getWebsiteIcon() {
		return websiteIcon;
	}

	public void setWebsiteIcon(java.lang.String websiteIcon) {
		this.websiteIcon = websiteIcon;
	}
	@Column(name ="websiteDesc",nullable=true,length=200)
	public java.lang.String getWebsiteDesc() {
		return websiteDesc;
	}

	public void setWebsiteDesc(java.lang.String websiteDesc) {
		this.websiteDesc = websiteDesc;
	}
	@Column(name ="websiteTitle",nullable=true,length=30)
	public java.lang.String getWebsiteTitle() {
		return websiteTitle;
	}

	public void setWebsiteTitle(java.lang.String websiteTitle) {
		this.websiteTitle = websiteTitle;
	}
	@Column(name ="websiteKeyword",nullable=true,length=200)
	public java.lang.String getWebsiteKeyword() {
		return websiteKeyword;
	}

	public void setWebsiteKeyword(java.lang.String websiteKeyword) {
		this.websiteKeyword = websiteKeyword;
	}
	@Column(name ="redirecDomain",nullable=true,length=50)
	public java.lang.String getRedirecDomain() {
		return redirecDomain;
	}

	public void setRedirecDomain(java.lang.String redirecDomain) {
		this.redirecDomain = redirecDomain;
	}

	@Column(name ="servicePhone",nullable=true,length=14)
	public java.lang.String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(java.lang.String servicePhone) {
		this.servicePhone = servicePhone;
	}
	@Column(name ="serviceEmail",nullable=true,length=20)
	public java.lang.String getServiceEmail() {
		return serviceEmail;
	}

	public void setServiceEmail(java.lang.String serviceEmail) {
		this.serviceEmail = serviceEmail;
	}
	@Column(name ="serviceqq",nullable=true,length=12)
	public java.lang.String getServiceqq() {
		return serviceqq;
	}

	public void setServiceqq(java.lang.String serviceqq) {
		this.serviceqq = serviceqq;
	}
	@Column(name ="qqGroup",nullable=true,length=12)
	public java.lang.String getQqGroup() {
		return qqGroup;
	}

	public void setQqGroup(java.lang.String qqGroup) {
		this.qqGroup = qqGroup;
	}
	public java.lang.String getQq() {
		return qq;
	}
	@Column(name ="qq",nullable=true,length=12)
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}
	@Column(name ="email",nullable=true,length=20)
	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	@Column(name ="webRecord",nullable=true,length=250)
	public java.lang.String getWebRecord() {
		return webRecord;
	}

	public void setWebRecord(java.lang.String webRecord) {
		this.webRecord = webRecord;
	}
	@Column(name ="forwardDomain",nullable=true,length=50)
	public java.lang.String getForwardDomain() {
		return forwardDomain;
	}

	public void setForwardDomain(java.lang.String forwardDomain) {
		this.forwardDomain = forwardDomain;
	}
	@Column(name ="aliPartner",nullable=true,length=50)
	public java.lang.String getAliPartner() {
		return aliPartner;
	}

	public void setAliPartner(java.lang.String aliPartner) {
		this.aliPartner = aliPartner;
	}
	@Column(name ="aliKey",nullable=true,length=100)
	public java.lang.String getAliKey() {
		return aliKey;
	}

	public void setAliKey(java.lang.String aliKey) {
		this.aliKey = aliKey;
	}
	@Column(name ="aliSellerAccount",nullable=true,length=60)
	public java.lang.String getAliSellerAccount() {
		return aliSellerAccount;
	}

	public void setAliSellerAccount(java.lang.String aliSellerAccount) {
		this.aliSellerAccount = aliSellerAccount;
	}



	
	
}
