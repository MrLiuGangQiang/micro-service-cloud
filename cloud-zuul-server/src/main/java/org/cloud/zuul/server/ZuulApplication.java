package org.cloud.zuul.server;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月19日
 * @description: ZuulApplication
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ZuulApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}