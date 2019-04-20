package org.cloud.service.core.result;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/20
 * @author LiuGangQiang
 * @project cloud-service-core
 * @package org.cloud.service.core.result
 * @remark api code enum
 */
public enum ApiCodeEnum {

	/**
	 * request execution successful
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	OK(200, "request execution successful"),
	/**
	 * request execution failed
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	FAIL(202, "request execution failed"),
	/**
	 * request parameter error
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	BAD_REQUEST(400, "request parameter error"),
	/**
	 * request not authorized
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	UNAUTHORIZED(401, "request not authorized"),
	/**
	 * request no authority
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	FORBIDDEN(403, "request no authority"),
	/**
	 * data not found
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	NOT_FOUND(404, "data not found"),
	/**
	 * data conflict
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	CONFLICT(409, "data conflict"),
	/**
	 * internal server error
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	ERROR(500, "internal server error"),
	/**
	 * interface not implemented
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	UNIMPLEMENTED(501, "interface not implemented"),
	/**
	 * service gateway timeout
	 *
	 * @since 2019/04/20
	 * @author LiuGangQiang
	 * @type {@link ApiCodeEnum}
	 */
	TIMEOUT(504, "service gateway timeout");

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