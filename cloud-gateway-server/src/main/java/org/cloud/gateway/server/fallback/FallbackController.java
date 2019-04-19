package org.cloud.gateway.server.fallback;

import org.cloud.service.core.messgae.Prompt;
import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-gateway-server
 * @package org.cloud.gateway.server.fallback
 * @remark gateway fallback controller
 */
@RestController
public class FallbackController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FallbackController.class);

	/**
	 * handle fallback
	 *
	 * @since 2019/04/19
	 * @author LiuGangQiang
	 * @param lb server instance id
	 * @return {@link JsonApi}
	 */
	@RequestMapping("/fallback/{lb}")
	public JsonApi<?> fallback(@PathVariable("lb") String lb) {
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error("server instance triggered short circuit operation instance id [{}]", lb);
		}
		return new JsonApi<>(ApiCodeEnum.TIMEOUT).setMsg(Prompt.bundle("fallback.timeout", lb));
	}
}