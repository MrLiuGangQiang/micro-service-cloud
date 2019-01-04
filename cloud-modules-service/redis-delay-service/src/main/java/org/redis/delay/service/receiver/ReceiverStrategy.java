package org.redis.delay.service.receiver;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: ReceiverStrategy
 */
public interface ReceiverStrategy {
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月20日
	 * @description: 执行方法
	 */
	public void run(String key);
}