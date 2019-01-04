package org.redis.delay.service.listener;

import org.redis.delay.service.config.CacheConfig;
import org.redis.delay.service.receiver.sms.SmsReceiverStrategy;
import org.redis.delay.service.tools.SpringContextUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: RedisExpiredListener
 */
public class RedisExpiredListener implements MessageListener {
	@Override
	public void onMessage(Message message, byte[] bytes) {
		/* 获取缓存可以 */
		String key = new String(message.getBody());
		/* 获取缓存前缀 */
		String prefix = key.substring(0, key.lastIndexOf(".") + 1);
		/* 根据前缀判断是何种业务逻辑 */
		if (prefix.equals(CacheConfig.SMS_PREFIX)) {
			/* 短信业务 */
			SpringContextUtils.getBean(SmsReceiverStrategy.class).run(key);
		}
	}
}
