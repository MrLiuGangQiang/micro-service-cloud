package org.cloud.gateway.server;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-gateway-server
 * @package org.cloud.gateway.server
 * @remark gateway main
 */
@SpringCloudApplication
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GatewayApplication.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.run(args);
	}
}