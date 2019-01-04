package org.cloud.service.core.redis;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: 分布式锁
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLockConfigure {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月21日
	 * @return {@link String}
	 * @description: 前缀
	 */
	String prefix() default "";

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月21日
	 * @return {@link Integer}
	 * @description: 过期时间 默认五秒
	 */
	int expire() default 5;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月21日
	 * @return {@link TimeUnit}
	 * @description: 时间单位 默认秒
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月21日
	 * @return {@link String}
	 * @description: 分隔符
	 */
	String delimiter() default ":";
}