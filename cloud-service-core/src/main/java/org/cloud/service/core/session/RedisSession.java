package org.cloud.service.core.session;

import java.io.Serializable;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.session
 * @remark redis session
 */
public class RedisSession<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * unique identification
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link String}
	 */
	private String identify;
	/**
	 * @type: {@link Object}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 用户信息
	 */
	private T data;

	public RedisSession() {
	}

	public RedisSession(String identify, T data) {
		this.identify = identify;
		this.data = data;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
