package org.redis.delay.service.entity.sms;

import org.cloud.service.core.entity.BaseEntity;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月17日
 * @description: 短信实体对象
 */
public class Sms extends BaseEntity {
	private static final long serialVersionUID = 5941799152716866611L;
	private Long time;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}
