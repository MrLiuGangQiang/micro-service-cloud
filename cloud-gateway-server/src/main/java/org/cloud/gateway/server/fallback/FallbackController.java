package org.cloud.gateway.server.fallback;

import org.cloud.gateway.server.message.Prompt;
import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月20日
 * @description: FallbackController
 */
@RestController
public class FallbackController {
	private static final Logger log = LoggerFactory.getLogger(FallbackController.class);

	@RequestMapping("/fallback/{lb}")
	public JsonApi<?> fallback(@PathVariable("lb") String lb) {
		log.error(Prompt.bundle("fallback.timeout", lb));
		return new JsonApi<>(ApiCodeEnum.TIMEOUT).setMsg(Prompt.bundle("fallback.timeout", lb));
	}
}