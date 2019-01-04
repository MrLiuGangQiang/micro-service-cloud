package org.cloud.service.core.result;

import java.io.Serializable;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月10日
 * @description: 结果集实体类
 */
public class JsonApi<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * @type: {@link Integer}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 状态码
	 */
	private Integer code;
	/**
	 * @type: {@link Object}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 数据
	 */
	private T data;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 消息
	 */
	private String msg;

	public JsonApi() {
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月10日
	 * @param code 状态码
	 * @description: 根据状态码构造结果集
	 */
	public JsonApi(ApiCodeEnum code) {
		this.code = code.getValue();
		this.msg = code.getMessage();
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月10日
	 * @param code 状态码
	 * @param data 数据实体
	 * @description: 根据状态码和数据构造结果集
	 */
	public JsonApi(ApiCodeEnum code, T data) {
		this.data = data;
		this.code = code.getValue();
		this.msg = code.getMessage();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public JsonApi<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月10日
	 * @param code
	 * @return {@link Boolean}
	 * @description: 状态码比较
	 */
	public boolean compare(ApiCodeEnum code) {
		return getCode() == code.getValue();
	}
}
