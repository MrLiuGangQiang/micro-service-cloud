package org.cloud.service.core.result;

import java.io.Serializable;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.result
 * @remark json api result set
 */
public class JsonApi<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * request result code
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link Integer}
	 */
	private Integer code;

	/**
	 * request result data type is {@link T}
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link T}
	 */
	private T data;

	/**
	 * requset result messgae
	 * 
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link String}
	 */
	private String msg;

	public JsonApi() {
	}

	/**
	 * constructor by {@link ApiCodeEnum}
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param code
	 */
	public JsonApi(ApiCodeEnum code) {
		this.data = null;
		this.code = code.getValue();
		this.msg = code.getMessage();
	}

	/**
	 * constructor by {@link ApiCodeEnum} and {@link T}
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param code
	 * @param data
	 */
	public JsonApi(ApiCodeEnum code, T data) {
		this.data = data;
		this.code = code.getValue();
		this.msg = code.getMessage();
	}

	/**
	 * constructor by code and message
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param code
	 * @param message
	 */
	public JsonApi(int code, String message) {
		this.data = null;
		this.code = code;
		this.msg = message;
	}

	/**
	 * constructor by code and message and data
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param code
	 * @param message
	 * @param data
	 */
	public JsonApi(int code, String message, T data) {
		this.data = null;
		this.code = code;
		this.msg = message;
	}

	public Integer getCode() {
		return code;
	}

	public JsonApi<T> setCode(Integer code) {
		this.code = code;
		return this;
	}

	public T getData() {
		return data;
	}

	public JsonApi<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public JsonApi<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * compare code
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @param code
	 * @return {@link Boolean}
	 */
	public boolean compare(ApiCodeEnum code) {
		return getCode() == code.getValue();
	}
}
