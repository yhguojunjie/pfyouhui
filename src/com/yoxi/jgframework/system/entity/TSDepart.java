
package com.yoxi.jgframework.system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.yoxi.jgframework.common.entity.IdStringAssignedEntity;

/**
 * 部门机构表
 */
@Entity
@Table(name = "t_s_depart")
public class TSDepart extends IdStringAssignedEntity implements java.io.Serializable {
	private TSDepart TSPDepart;//上级部门
	private String departName;//部门名称
	private String departCode;//部门编码
	private String description;//部门描述
	private Integer departOrder;
	private Integer departType;
	@JsonBackReference
	private List<TSDepart> TSDeparts = new ArrayList<TSDepart>();//下属部门

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentDepartid")
	public TSDepart getTSPDepart() {
		return this.TSPDepart;
	}

	public void setTSPDepart(TSDepart TSPDepart) {
		this.TSPDepart = TSPDepart;
	}

	@Column(name = "departName", nullable = false, length = 100)
	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}
	@Column(name = "departCode", length = 100)
	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "departOrder")
	public Integer getDepartOrder() {
		return departOrder;
	}

	public void setDepartOrder(Integer departOrder) {
		this.departOrder = departOrder;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSPDepart")
	public List<TSDepart> getTSDeparts() {
		return TSDeparts;
	}

	public void setTSDeparts(List<TSDepart> tSDeparts) {
		TSDeparts = tSDeparts;
	}
	/*
	 * 0-部门，1-CP，2-渠道，3-运营
	 */
	@Column(name = "departType")
	public Integer getDepartType() {
		return departType;
	}

	public void setDepartType(Integer departType) {
		this.departType = departType;
	}
}