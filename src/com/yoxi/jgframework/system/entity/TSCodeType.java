package com.yoxi.jgframework.system.entity;
// default package

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.yoxi.jgframework.common.entity.IdStringAssignedEntity;

/**
 * TTypegroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_s_code_type")
public class TSCodeType extends IdStringAssignedEntity implements java.io.Serializable {

	public static Map<String, TSCodeType> allTSCodeType = new HashMap<String,TSCodeType>();
	public static Map<String, List<TSCodeDetail>> allTSCodeDetail = new HashMap<String,List<TSCodeDetail>>();
	
	private String typeName;
	private String codeName1Label;
	private String codeName2Label;
	private String codeName3Label;
	private String codeName4Label;
	private Boolean allowAdd;
	private Boolean allowModify;
	private Boolean allowDelete;
	private String typeMemo;
	private List<TSCodeDetail> TSCodeDetails = new ArrayList<TSCodeDetail>();
	
	@Column(name = "typeName", length = 100)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "codeName1Label", length = 50)
	public String getCodeName1Label() {
		return this.codeName1Label;
	}

	public void setCodeName1Label(String codeName1Label) {
		this.codeName1Label = codeName1Label;
	}
	
	@Column(name = "codeName2Label", length = 50)
	public String getCodeName2Label() {
		return codeName2Label;
	}

	public void setCodeName2Label(String codeName2Label) {
		this.codeName2Label = codeName2Label;
	}
	@Column(name = "codeName3Label", length = 50)
	public String getCodeName3Label() {
		return codeName3Label;
	}

	public void setCodeName3Label(String codeName3Label) {
		this.codeName3Label = codeName3Label;
	}
	@Column(name = "codeName4Label", length = 50)
	public String getCodeName4Label() {
		return codeName4Label;
	}

	public void setCodeName4Label(String codeName4Label) {
		this.codeName4Label = codeName4Label;
	}
	@Column(name = "allowAdd")
	public Boolean getAllowAdd() {
		return allowAdd;
	}

	public void setAllowAdd(Boolean allowAdd) {
		this.allowAdd = allowAdd;
	}
	@Column(name = "allowModify")
	public Boolean getAllowModify() {
		return allowModify;
	}

	public void setAllowModify(Boolean allowModify) {
		this.allowModify = allowModify;
	}
	@Column(name = "allowDelete")
	public Boolean getAllowDelete() {
		return allowDelete;
	}

	public void setAllowDelete(Boolean allowDelete) {
		this.allowDelete = allowDelete;
	}
	@Column(name = "typeMemo", length = 200)
	public String getTypeMemo() {
		return typeMemo;
	}
	/**
	 * 注意数据库存储为<br/>作为换行，否则生成json有误
	 * @param note
	 */
	public void setTypeMemo(String typeMemo) {
		if (typeMemo != null)
			this.typeMemo = typeMemo.replaceAll("\r\n", "<br/>");
		else
			this.typeMemo = typeMemo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSCodeType")
	public List<TSCodeDetail> getTSCodeDetails() {
		return this.TSCodeDetails;
	}

	public void setTSCodeDetails(List<TSCodeDetail> TSTypes) {
		this.TSCodeDetails = TSTypes;
	}

}