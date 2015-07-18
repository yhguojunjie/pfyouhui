package com.yoxi.jgframework.common.model.json;

import java.util.List;
import java.util.Map;

/**
 * 后台向前台返回JSON，用于easyui的datagrid
 * @author liyh
 * @Date 2014 2014年4月10日 上午8:44:35
 * @param <T>
 */
public class DataGridReturn{

	/**
	 * 总记录数
	 */
	private Integer total;
	/**
	 * 每行记录
	 */
	private List<?> rows;
	/**
	 * 底下统计
	 */
	private Map<String, String> footers;

	public DataGridReturn() {
	}

	public DataGridReturn(Integer total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Map<String, String> getFooters() {
		return footers;
	}

	public void setFooters(Map<String, String> footers) {
		this.footers = footers;
	}

}
