package com.yoxi.jgframework.common.model.json;

import java.util.Comparator;

public class ComparatorTreeGrid implements Comparator {

	public int compare(Object arg0, Object arg1) {
		TreeGrid user0 = (TreeGrid) arg0;
		TreeGrid user1 = (TreeGrid) arg1;

		// 首先比较年龄，如果年龄相同，则比较名字

		int flag = user0.getOrder().compareTo(user1.getOrder());		
		
		if (flag == 0 && user0.getCode()!= null) {
			return user0.getCode().compareTo(user1.getCode());
		} else {
			return flag;
		}
	}

}
