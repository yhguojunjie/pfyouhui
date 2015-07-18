package com.yoxi.jgframework.common.hibernate.dialect;

import org.hibernate.dialect.PostgreSQLDialect;

@SuppressWarnings("deprecation")
public class MyPostgreSQLDialect extends PostgreSQLDialect {

	@Override
	public boolean useInputStreamToInsertBlob() {
		// TODO Auto-generated method stub
		return true;
	}

}
