package com.yoxi.jgframework.system.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdUUIDEntity;

/**
 * TType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_s_opintemplate")
public class TSOpinTemplate extends IdUUIDEntity implements java.io.Serializable {
	private String descript;
	@Column(name = "descript", length = 100)
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
}