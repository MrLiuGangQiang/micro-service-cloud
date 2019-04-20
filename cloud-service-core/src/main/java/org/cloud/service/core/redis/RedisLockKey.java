package org.cloud.service.core.redis;

import java.lang.annotation.*;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.redis
 * @remark distributed redis lock key
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLockKey {
	/**
	 * lock paramter name
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @return {@link String}
	 */
	String name() default "";
}