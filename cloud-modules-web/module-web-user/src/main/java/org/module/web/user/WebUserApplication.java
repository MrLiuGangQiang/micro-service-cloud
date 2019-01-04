package org.module.web.user;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月14日
 * @description: WebUserApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class WebUserApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebUserApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
