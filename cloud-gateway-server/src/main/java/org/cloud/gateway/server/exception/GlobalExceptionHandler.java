package org.cloud.gateway.server.exception;

import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-gateway-server
 * @package org.cloud.gateway.server.exception
 * @remark gateway exception handler
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * intercepting exceptions and returning uniformly
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param e exception
	 * @return {@link JsonApi}
	 */
	@ExceptionHandler(Exception.class)
	public JsonApi<?> defaultErrorHandler(Exception e) {
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error("system appear error msg --> {}", e.toString());
		}
		return new JsonApi<>(ApiCodeEnum.ERROR).setMsg(e.toString());
	}
}
