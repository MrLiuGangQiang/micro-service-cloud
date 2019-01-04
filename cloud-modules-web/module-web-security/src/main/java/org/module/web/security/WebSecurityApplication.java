package org.module.web.security;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月14日
 * @description: WebUserApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WebSecurityApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebSecurityApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
