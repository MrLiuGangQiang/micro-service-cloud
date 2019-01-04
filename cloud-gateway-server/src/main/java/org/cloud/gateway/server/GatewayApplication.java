package org.cloud.gateway.server;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月19日
 * @description: GatewayApplication
 */
@SpringCloudApplication
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GatewayApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}