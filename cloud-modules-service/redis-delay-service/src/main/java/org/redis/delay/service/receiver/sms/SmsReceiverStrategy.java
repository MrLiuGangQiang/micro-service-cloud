package org.redis.delay.service.receiver.sms;

import javax.annotation.Resource;

import org.cloud.service.core.redis.RedisLockConfigure;
import org.cloud.service.core.redis.RedisLockKey;
import org.cloud.service.redis.cache.RedisCacheManager;
import org.redis.delay.service.config.CacheConfig;
import org.redis.delay.service.receiver.ReceiverStrategy;
import org.redis.delay.service.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: SmsReceiverStrategy
 */
@Component
public class SmsReceiverStrategy implements ReceiverStrategy {
	private final static Logger log = LoggerFactory.getLogger(SmsReceiverStrategy.class);
	@Resource
	private RedisCacheManager manager;
	@Resource
	private SmsService smsService;

	@Override
	@RedisLockConfigure(prefix = CacheConfig.LOCK_PREFIX)
	public void run(@RedisLockKey(name = "key") String key) {
		log.warn("A.根据key获取对象");
		log.warn("B.消费对象");
		log.warn("C.消费完成之后删除缓存");
		manager.getCache(CacheConfig.SMS_CACHE).evict(key);
	}
}