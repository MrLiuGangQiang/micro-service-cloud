package org.cloud.service.core.session;

import java.io.Serializable;
import java.util.Map;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月11日
 * @description: AuthenticationSession
 */
public class RedisSession implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 唯一区别串
	 */
	private String identify;
	/**
	 * @type: {@link Object}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 用户信息
	 */
	private Map<String, Object> data;

	public RedisSession() {
	}

	public RedisSession(String identify, Map<String, Object> data) {
		this.identify = identify;
		this.data = data;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
