package org.redis.delay.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月13日
 * @description: 实体类校验切面
 */
@Component
@Aspect
public class ValidatorAop {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param point
	 * @return {@link Object}
	 * @throws Throwable
	 * @description: 环绕校验配置
	 */
	@Around("execution(public * org.redis.delay.service.controller..*.*(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object[] objects = point.getArgs();
		for (Object object : objects) {
			if (object instanceof BindingResult) {
				if (((BindingResult) object).hasErrors()) {
					return new JsonApi<>(ApiCodeEnum.BAD_REQUEST).setMsg(((BindingResult) object).getFieldError().getDefaultMessage());
				}
			}
		}
		return point.proceed();
	}
}