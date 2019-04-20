package org.module.web.user.feign.fallback;

import java.util.Map;

import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.cloud.service.core.session.RedisSession;
import org.module.web.user.feign.iface.IUserSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月19日
 * @description: UserSecurityServiceFallback
 */
@Component
public class UserSecurityServiceFallback implements IUserSecurityService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserSecurityServiceFallback.class);

	@Override
	public JsonApi<Map<String, Object>> login(String username, String password) {
		LOGGER.error("feign error method login parameter [username={},password={}]", username, password);
		return new JsonApi<>(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi<RedisSession<Map<String, Object>>> getLoginInfo(String token) {
		LOGGER.error("feign error method getLoginInfo parameter [token={}]", token);
		return new JsonApi<>(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi<Boolean> authByRole(Object uid, Object oid, String target) {
		LOGGER.error("feign error method authByRole parameter [uid={},oid={},target={}]", uid, oid, target);
		return new JsonApi<>(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi<Boolean> authByPermission(Object uid, Object oid, String target) {
		LOGGER.error("feign error method authByPermission parameter [uid={},oid={},target={}]", uid, oid, target);
		return new JsonApi<>(ApiCodeEnum.TIMEOUT);
	}

	@Override
	public JsonApi<Boolean> authByOperation(Object uid, Object oid, String target) {
		LOGGER.error("feign error method authByOperation parameter [uid={},oid={},target={}]", uid, oid, target);
		return new JsonApi<>(ApiCodeEnum.TIMEOUT);
	}

}
