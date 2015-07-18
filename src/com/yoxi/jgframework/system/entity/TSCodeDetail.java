package com.yoxi.jgframework.system.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdIntegerIdentityAutoUserEntity;
/**
 * 通用类型字典表
 */
@Entity
@Table(name = "t_s_code_detail")
public class TSCodeDetail extends IdIntegerIdentityAutoUserEntity implements java.io.Serializable {

	private TSCodeType TSCodeType;//类型分组
	private Integer codeOrder;
	private String codeName;//类型名称
	private String code;//类型编码
	private String codeMemo;//说明
	private String codeName1;//类型名称1
	private String codeName2;//类型名称2
	private String codeName3;//类型名称3
	private String codeName4;//类型名称4
	private Boolean deleteFlag = false;//删除标记

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typegroupid")
	public TSCodeType getTSCodeType() {
		return this.TSCodeType;
	}

	public void setTSCodeType(TSCodeType TSTypegroup) {
		this.TSCodeType = TSTypegroup;
	}
	
	@Column(name = "codeOrder")
	public Integer getCodeOrder() {
		return this.codeOrder;
	}

	public void setCodeOrder(Integer codeOrder) {
		this.codeOrder = codeOrder;
	}
	

	@Column(name = "codeName", length = 100)
	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@Column(name = "code", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "codeMemo", length = 200)
	public String getCodeMemo() {
		return codeMemo;
	}

	public void setCodeMemo(String codeMemo) {
		this.codeMemo = codeMemo;
	}
	@Column(name = "codeName1", length = 100)
	public String getCodeName1() {
		return codeName1;
	}

	public void setCodeName1(String codeName1) {
		this.codeName1 = codeName1;
	}
	@Column(name = "codeName2", length = 100)
	public String getCodeName2() {
		return codeName2;
	}

	public void setCodeName2(String codeName2) {
		this.codeName2 = codeName2;
	}
	@Column(name = "codeName3", length = 100)
	public String getCodeName3() {
		return codeName3;
	}

	public void setCodeName3(String codeName3) {
		this.codeName3 = codeName3;
	}
	@Column(name = "codeName4", length = 100)
	public String getCodeName4() {
		return codeName4;
	}

	public void setCodeName4(String codeName4) {
		this.codeName4 = codeName4;
	}
	@Column(name = "deleteFlag")
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	


}