package org.cloud.service.core.redis;

import java.lang.annotation.*;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: 锁定的key配置
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLockKey {
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月21日
	 * @return {@link String}
	 * @description: 参数名
	 */
	String name() default "";
}