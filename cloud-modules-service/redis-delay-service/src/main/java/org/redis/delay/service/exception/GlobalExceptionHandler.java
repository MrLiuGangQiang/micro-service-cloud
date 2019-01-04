package org.redis.delay.service.exception;

import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月3日
 * @description: 全局异常拦截器
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public JsonApi<?> defaultErrorHandler(Exception e) {
		if (logger.isErrorEnabled()) {
			logger.error("system appear error msg:{}", e.getMessage());
		}
		return new JsonApi<>(ApiCodeEnum.ERROR).setMsg(e.getMessage());
	}
}
