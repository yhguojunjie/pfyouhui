package com.yoxi.jgframework.system.entity;
// default package

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yoxi.jgframework.common.entity.IdUUIDEntity;

/**
 * TConfig entity. @author MyEclipse Persistence Tools
 * 系统配置类
 */
@Entity
@Table(name = "t_s_config")
public class TSConfig extends IdUUIDEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public static Map<String,String> someTSConfig = new ConcurrentHashMap<String, String>();

	private TSUser TSUser;
	private String code;
	private String name;
	private String contents;
	private String note;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public TSUser getTSUser() {
		return this.TSUser;
	}

	public void setTSUser(TSUser TSUser) {
		this.TSUser = TSUser;
	}
	@Column(name = "code", length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "content", length = 300)
	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Column(name = "note", length = 300)
	public String getNote() {
		return this.note;
	}

	/**
	 * 注意数据库存储为<br/>作为换行，否则生成json有误
	 * @param note
	 */
	public void setNote(String note) {
		if (note != null)
			this.note = note.replaceAll("\r\n", "<br/>");
		else
			this.note = note;
	}

}