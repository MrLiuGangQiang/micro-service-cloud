package org.redis.delay.service.config;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月13日
 * @description: 缓存配置
 */
public class CacheConfig {
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @description: 分布式锁前缀
	 */
	public final static String LOCK_PREFIX = "lock";
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @description: 短信缓存前缀
	 */
	public final static String SMS_PREFIX = "sms.cache.";
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @description: 短信缓存空间
	 */
	public final static String SMS_CACHE = "sms_cache";
}
