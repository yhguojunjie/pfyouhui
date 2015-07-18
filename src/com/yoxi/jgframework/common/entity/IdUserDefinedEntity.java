package com.yoxi.jgframework.common.entity;

import java.util.Map;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class IdUserDefinedEntity {
	private Map<String, Object> conditionMap;

	@Transient
	public Map<String, Object> getConditionMap(){
		return conditionMap;
	}

	public void setConditionMap(Map<String, Object> conditionMap){
		this.conditionMap = conditionMap;
	}
}
