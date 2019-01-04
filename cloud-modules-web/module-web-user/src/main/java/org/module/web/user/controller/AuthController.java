package org.module.web.user.controller;

import org.cloud.service.core.auth.Level;
import org.cloud.service.core.auth.RequiresAuthentication;
import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.module.web.user.message.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/ignore")
	@RequiresAuthentication(ignore = true)
	public JsonApi<?> ignore() {
		return new JsonApi<>(ApiCodeEnum.OK).setMsg(Prompt.bundle("test.auth.ignore.true"));
	}

	@GetMapping("/authc")
	@RequiresAuthentication(authc = true)
	public JsonApi<?> authc() {
		return new JsonApi<>(ApiCodeEnum.OK).setMsg(Prompt.bundle("test.auth.authc.true"));
	}

	@GetMapping("/role")
	@RequiresAuthentication(level = Level.ROLE, value = "role")
	public JsonApi<?> role() {
		return new JsonApi<>(ApiCodeEnum.OK).setMsg(Prompt.bundle("test.auth.level.role"));
	}

	@GetMapping("/permission")
	@RequiresAuthentication(level = Level.PERMISSION, value = "permission")
	public JsonApi<?> permission() {
		return new JsonApi<>(ApiCodeEnum.OK).setMsg(Prompt.bundle("test.auth.level.permission"));
	}

	@GetMapping("/operation")
	@RequiresAuthentication(level = Level.OPERATION, value = "operation")
	public JsonApi<?> operation() {
		return new JsonApi<>(ApiCodeEnum.OK).setMsg(Prompt.bundle("test.auth.level.operation"));
	}

}
