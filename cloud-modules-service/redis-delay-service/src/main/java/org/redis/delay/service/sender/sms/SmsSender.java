package org.redis.delay.service.sender.sms;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.cloud.service.redis.cache.RedisCacheManager;
import org.redis.delay.service.config.CacheConfig;
import org.redis.delay.service.entity.sms.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月3日
 * @description: SmsSender
 */
@Component
public class SmsSender {

	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	private RedisCacheManager manager;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @param sms
	 * @description: 发送短信
	 */
	public void send(Sms sms) {
		/* 生成唯一key */
		String key = CacheConfig.SMS_PREFIX + UUID.randomUUID();
		/* 把key+过期时间存入redis */
		redisTemplate.opsForValue().set(key, "", sms.getTime(), TimeUnit.MILLISECONDS);
		/* 把短信实体存入redis */
		manager.getCache(CacheConfig.SMS_CACHE).put(key, sms);
	}
}