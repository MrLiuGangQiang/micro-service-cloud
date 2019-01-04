package org.module.web.security.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.cloud.service.core.session.RedisSession;
import org.cloud.service.core.session.RedisToken;
import org.cloud.service.core.session.TerminalEnum;
import org.cloud.service.redis.cache.RedisCacheManager;
import org.module.web.security.entity.UserSecurity;
import org.module.web.security.global.BaseGlobal;
import org.module.web.security.service.UserSecurityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月17日
 * @description: 用户权限
 */
@RestController
@RequestMapping("/user")
public class UserSecurityController {
	@Resource
	private UserSecurityService userSecurityService;
	@Resource
	private RedisCacheManager redisCacheManager;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param username
	 * @param password
	 * @return {@link JsonApi}
	 * @description: 用户登录
	 */
	@GetMapping("/login")
	public JsonApi<?> login(String phone, String password) {
		Map<String, Object> userMap = userSecurityService.getUserByPhoneAndPassword(phone, password);
		if (userMap != null && !userMap.isEmpty()) {
			String token = RedisToken.createToken(TerminalEnum.WEB, userMap.get("id"));
			redisCacheManager.putSession(BaseGlobal.CACHE_WEB_USER, token, userMap);
			userMap.put("token", token);
			return new JsonApi<Map<String, Object>>(ApiCodeEnum.OK, userMap);
		}
		return new JsonApi<>(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 获取登录信息
	 */
	@GetMapping("/login/info")
	public JsonApi<?> getLoginInfo(String token) {
		RedisSession session = redisCacheManager.getSession(BaseGlobal.CACHE_WEB_USER, token);
		if (session != null) {
			return new JsonApi<RedisSession>(ApiCodeEnum.OK, session);
		}
		return new JsonApi<>(ApiCodeEnum.FAIL, null);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param uid
	 * @param oid
	 * @param target
	 * @return {@link JsonApi}
	 * @description: 角色认证
	 */
	@GetMapping("/auth/role")
	public JsonApi<?> authByRole(Integer uid, Integer oid, String target) {
		/* 封装对象 */
		UserSecurity security = new UserSecurity();
		security.setUid(uid);
		security.setOid(oid);
		security.setTarget(target);
		List<Map<String, Object>> roleList = userSecurityService.getRoleList(security);
		return new JsonApi<Boolean>(ApiCodeEnum.OK, validate(roleList, target));
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param uid
	 * @param oid
	 * @param target
	 * @return {@link JsonApi}
	 * @description: 权限认证
	 */
	@GetMapping("/auth/permission")
	public JsonApi<?> authByPermission(Integer uid, Integer oid, String target) {
		/* 封装对象 */
		UserSecurity security = new UserSecurity();
		security.setUid(uid);
		security.setOid(oid);
		security.setTarget(target);
		List<Map<String, Object>> permissionList = userSecurityService.getPermissionList(security);
		return new JsonApi<Boolean>(ApiCodeEnum.OK, validate(permissionList, target));
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param uid
	 * @param oid
	 * @param target
	 * @return {@link JsonApi}
	 * @description: 操作认证
	 */
	@GetMapping("/auth/operation")
	public JsonApi<?> authByOperation(Integer uid, Integer oid, String target) {
		/* 封装对象 */
		UserSecurity security = new UserSecurity();
		security.setUid(uid);
		security.setOid(oid);
		security.setTarget(target);
		List<Map<String, Object>> operationList = userSecurityService.getOperationList(security);
		return new JsonApi<Boolean>(ApiCodeEnum.OK, validate(operationList, target));
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月19日
	 * @param list
	 * @param auth
	 * @return boolean
	 * @description: 校验方法
	 */
	private boolean validate(List<Map<String, Object>> list, String auth) {
		if (list != null && list.size() > 0 && auth != null) {
			for (Map<String, Object> map : list) {
				if (map != null && map.size() > 0) {
					if (auth.equals(map.get(BaseGlobal.PERMISSION_CODE))) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
