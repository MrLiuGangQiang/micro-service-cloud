package org.redis.delay.service.lock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.cloud.service.core.redis.RedisLockConfigure;
import org.cloud.service.core.redis.RedisLockKey;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;


/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月14日
 * @description: LockKeyGenerator
 */
public class LockKeyGenerator implements CacheKeyGenerator {

	@Override
	public String getLockKey(ProceedingJoinPoint pjp) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		RedisLockConfigure redisLockConfigure = method.getAnnotation(RedisLockConfigure.class);
		final Object[] args = pjp.getArgs();
		final Parameter[] parameters = method.getParameters();
		StringBuilder builder = new StringBuilder();
		/*默认解析方法里面带 CacheParam 注解的属性,如果没有尝试着解析实体对象中的*/
		for (int i = 0; i < parameters.length; i++) {
			final RedisLockKey lockKey = parameters[i].getAnnotation(RedisLockKey.class);
			if (lockKey == null) {
				continue;
			}
			builder.append(redisLockConfigure.delimiter()).append(args[i]);
		}
		if (StringUtils.isEmpty(builder.toString())) {
			final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				final Object object = args[i];
				final Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					final RedisLockKey lockKey = field.getAnnotation(RedisLockKey.class);
					if (lockKey == null) {
						continue;
					}
					field.setAccessible(true);
					builder.append(redisLockConfigure.delimiter()).append(ReflectionUtils.getField(field, object));
				}
			}
		}
		return redisLockConfigure.prefix() + builder.toString();
	}
}