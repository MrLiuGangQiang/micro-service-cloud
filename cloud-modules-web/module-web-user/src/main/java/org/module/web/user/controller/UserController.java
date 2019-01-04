package org.module.web.user.controller;

import javax.annotation.Resource;

import org.cloud.service.core.auth.RequiresAuthentication;
import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.module.web.user.entity.User;
import org.module.web.user.entity.User.Login;
import org.module.web.user.feign.iface.IUserSecurityService;
import org.module.web.user.message.Prompt;
import org.module.web.user.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月19日
 * @description: UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private IUserSecurityService userSecurityService;

	@GetMapping("/login")
	@RequiresAuthentication(ignore = true)
	public JsonApi<?> login(@Validated({ Login.class }) User user, BindingResult result) {
		JsonApi<?> userApi = userSecurityService.login(user.getPhone(), user.getPassword());
		if (userApi.compare(ApiCodeEnum.OK)) {
			return userApi;
		}
		return new JsonApi<>(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.login.fail"));
	}
}
