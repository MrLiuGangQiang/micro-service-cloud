package org.redis.delay.service.controller;

import javax.annotation.Resource;

import org.cloud.service.core.result.ApiCodeEnum;
import org.cloud.service.core.result.JsonApi;
import org.cloud.service.core.valid.Valided.Insert;
import org.redis.delay.service.entity.sms.Sms;
import org.redis.delay.service.sender.sms.SmsSender;
import org.redis.delay.service.service.SmsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2018 Fist Team All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月3日
 * @description: 生产者
 */
@RestController
@RequestMapping(value = { "/sms" })
public class SmsController {

	@Resource
	private SmsSender smsSender;
	@Resource
	private SmsService smsService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年9月21日
	 * @param sms
	 * @return {@link JsonApi}
	 * @description: 发送短信
	 */
	@PostMapping(value = { "/send" })
	public JsonApi<?> sendPush(@RequestBody @Validated({ Insert.class }) Sms sms, BindingResult result) {
			smsSender.send(sms);
			return new JsonApi<>(ApiCodeEnum.OK);
	}
}
