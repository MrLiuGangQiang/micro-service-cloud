package org.redis.delay.service.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: RedisListenerConfig
 */
@Configuration
public class RedisListenerConfig {
	/**
	 * @type: {@link Integer}
	 * @author: LiuGangQiang
	 * @date: 2018年12月14日
	 * @description: redis数据库下标
	 */
	@Value("${spring.redis.database}")
	private Integer db;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月20日
	 * @param connectionFactory
	 * @return {@link RedisMessageListenerContainer}
	 * @description: 监听器配置 __keyevent@0__:expired 监听?号数据库的过期事件
	 */
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(new RedisExpiredListener(), new PatternTopic("__keyevent@" + db + "__:expired"));
		return container;
	}
}
