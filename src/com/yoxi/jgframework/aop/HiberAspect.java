package com.yoxi.jgframework.aop;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.util.oConvertUtils;

/**
 * Hiberate拦截器：实现创建人，创建时间，创建人名称自动注入; 修改人,修改时间,修改人名自动注入;
 */
@Component
public class HiberAspect extends EmptyInterceptor {
	private static final Logger logger = Logger.getLogger(HiberAspect.class);
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// update-begin--Author:zhaojunfu Date:20130414
		// for：没有session的时候会抛异常，这里把异常吞掉
		TSUser currentUser = null;
		try {
			currentUser = ResourceUtil.getSessionUserName();
		} catch (RuntimeException e1) {
			e1.printStackTrace();
		}
		if (currentUser == null) {
			return true;
		}
		// update-end--Author:zhaojunfu Date:20130414
		// for：没有session的时候会抛异常，这里把异常吞掉
		try {
			// 添加数据
			for (int index = 0; index < propertyNames.length; index++) {
				/* 找到名为"创建时间"的属性 */
				if ("createDate".equals(propertyNames[index])) {
					/* 使用拦截器将对象的"创建时间"属性赋上值 */
					if (oConvertUtils.isEmpty(state[index])) {
						state[index] = new Date();
					}
					continue;
				}
				/* 找到名为"创建人"的属性 */
				else if ("createBy".equals(propertyNames[index])) {
					/* 使用拦截器将对象的"创建人"属性赋上值 */
					if (oConvertUtils.isEmpty(state[index])) {
						state[index] = currentUser.getId();
					}
					continue;
				}
				/* 找到名为"创建人名称"的属性 */
				else if ("createName".equals(propertyNames[index])) {
					/* 使用拦截器将对象的"创建人名称"属性赋上值 */
					if (oConvertUtils.isEmpty(state[index])) {
						state[index] = currentUser.getRealName();
					}
					continue;
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		// update-begin--Author:zhaojunfu Date:20130414
		// for：没有session的时候会抛异常，这里把异常吞掉
		TSUser currentUser = null;
		try {
			currentUser = ResourceUtil.getSessionUserName();
		} catch (RuntimeException e1) {
			e1.printStackTrace();
		}
		if (currentUser == null) {
			return true;
		}
		// update-end--Author:zhaojunfu Date:20130414
		// for：没有session的时候会抛异常，这里把异常吞掉
		// 添加数据
		for (int index = 0; index < propertyNames.length; index++) {
			/* 找到名为"修改时间"的属性 */
			if ("updateDate".equals(propertyNames[index])) {
				/* 使用拦截器将对象的"修改时间"属性赋上值 */
				currentState[index] = new Date();
				continue;
			}
			/* 找到名为"修改人"的属性 */
			else if ("updateBy".equals(propertyNames[index])) {
				/* 使用拦截器将对象的"修改人"属性赋上值 */
				currentState[index] = currentUser.getId();
				continue;
			}
			/* 找到名为"修改人名称"的属性 */
			else if ("updateName".equals(propertyNames[index])) {
				/* 使用拦截器将对象的"修改人名称"属性赋上值 */
				currentState[index] = currentUser.getRealName();
				continue;
			}
		}
		return true;
	}
}
