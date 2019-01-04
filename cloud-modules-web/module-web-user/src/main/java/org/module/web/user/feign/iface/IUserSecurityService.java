package org.module.web.user.feign.iface;

import java.util.Map;

import org.cloud.service.core.result.JsonApi;
import org.cloud.service.core.session.RedisSession;
import org.module.web.user.feign.fallback.UserSecurityServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月19日
 * @description: IUserSecurityService
 */
@FeignClient(value = "${feign.client.web-security}", fallback = UserSecurityServiceFallback.class)
public interface IUserSecurityService {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 获取登录信息
	 */
	@GetMapping("/user/login")
	public JsonApi<Map<String, Object>> login(@RequestParam("phone") String phone, @RequestParam("password") String password);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 获取登录信息
	 */
	@GetMapping("/user/login/info")
	public JsonApi<RedisSession> getLoginInfo(@RequestParam("token") String token);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param uid
	 * @param oid
	 * @param target
	 * @return {@link JsonApi}
	 * @description: 角色认证
	 */
	@GetMapping("/user/auth/role")
	public JsonApi<Boolean> authByRole(@RequestParam("uid") Object uid, @RequestParam("oid") Object oid, @RequestParam("target") String target);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param uid
	 * @param oid
	 * @param target
	 * @return {@link JsonApi}
	 * @description: 权限认证
	 */
	@GetMapping("/user/auth/permission")
	public JsonApi<Boolean> authByPermission(@RequestParam("uid") Object uid, @RequestParam("oid") Object oid, @RequestParam("target") String target);

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param uid
	 * @param oid
	 * @param target
	 * @return {@link JsonApi}
	 * @description: 操作认证
	 */
	@GetMapping("/user/auth/operation")
	public JsonApi<Boolean> authByOperation(@RequestParam("uid") Object uid, @RequestParam("oid") Object oid, @RequestParam("target") String target);
}
