package org.cloud.service.core.redis;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.redis
 * @remark distributed redis lock config
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLockConfigure {

	/**
	 * lock prefix
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @return {@link String}
	 */
	String prefix() default "";

	/**
	 * lock expire default 5
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @return {@link Integer}
	 */
	int expire() default 5;

	/**
	 * lock expire time unit default {@link TimeUnit#SECONDS}
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @return {@link TimeUnit}
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;

	/**
	 * lock delimiter default ":"
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @return {@link String}
	 */
	String delimiter() default ":";
}