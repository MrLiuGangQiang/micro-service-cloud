package org.redis.delay.service;

import org.redis.delay.service.lock.CacheKeyGenerator;
import org.redis.delay.service.lock.LockKeyGenerator;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月14日
 * @description: TaskDelayApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TaskDelayApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TaskDelayApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
	@Bean
	public CacheKeyGenerator cacheKeyGenerator() {
		return new LockKeyGenerator();
	}
}
