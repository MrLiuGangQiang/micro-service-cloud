package org.cloud.service.core.result;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月10日
 * @description: 状态枚举类
 */
public enum ApiCodeEnum {

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 成功
	 */
	OK(200, "Request execution successful"),

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 失败
	 */
	FAIL(202, "Request execution failed"),
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 请求参数有误
	 */
	BAD_REQUEST(400, "Request parameter error"),
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 未授权
	 */
	UNAUTHORIZED(401, "Request not authorized"),

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 权限不足
	 */
	FORBIDDEN(403, "Request no authority"),

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 未找到满足条件的数据
	 */
	NOT_FOUND(404, "Data not found"),
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 数据冲突
	 */
	CONFLICT(409, "Data conflict"),
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 系统异常
	 */
	ERROR(500, "Internal Server Error"),
	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 未定义
	 */
	UNIMPLEMENTED(501, "Not implemented"),

	/**
	 * @type: {@link ApiCodeEnum}
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @description: 响应超时
	 */
	TIMEOUT(504, "Service gateway timeout");

	private int value;
	private String message;

	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	ApiCodeEnum(int value, String message) {
		this.value = value;
		this.message = message;
	}
}