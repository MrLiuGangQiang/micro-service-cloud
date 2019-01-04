package org.redis.delay.service.tools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月18日
 * @description: SpringContextUtils
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringContextUtils.applicationContext == null) {
			SpringContextUtils.applicationContext = applicationContext;
		}
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @return {@link ApplicationContext}
	 * @description: 获取ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @param name
	 * @return {@link Object}
	 * @description: 通过名字获取bean
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @param clazz
	 * @return {@link T}
	 * @description: 通过class获取bean
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @param name
	 * @param clazz
	 * @return {@link T}
	 * @description: 通过name和class获取制定bean
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
}
