package org.redis.delay.service.lock;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CacheKeyGenerator {

	String getLockKey(ProceedingJoinPoint pjp);
}