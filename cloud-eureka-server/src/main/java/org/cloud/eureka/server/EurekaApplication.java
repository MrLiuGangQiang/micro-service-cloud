package org.cloud.eureka.server;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Copyright © 2019 ChengDu Smart Technology Co.Ltd All Rights Reserved.
 *
 * @since 2019/04/19
 * @author LiuGangQiang
 * @project cloud-eureka-server
 * @package org.cloud.eureka.server
 * @remark register main
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EurekaApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
