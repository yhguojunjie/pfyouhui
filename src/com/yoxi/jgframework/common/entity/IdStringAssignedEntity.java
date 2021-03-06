package com.yoxi.jgframework.common.entity;

import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@MappedSuperclass
public abstract class IdStringAssignedEntity {
	protected String id;

	@Id
	@GeneratedValue(generator = "StringAssigned")
	@GenericGenerator(name = "StringAssigned", strategy = "assigned") 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private Map<String, Object> conditionMap;

	@Transient
	public Map<String, Object> getConditionMap() {
		return conditionMap;
	}

	public void setConditionMap(Map<String, Object> conditionMap) {
		this.conditionMap = conditionMap;
	}
	
	

}
