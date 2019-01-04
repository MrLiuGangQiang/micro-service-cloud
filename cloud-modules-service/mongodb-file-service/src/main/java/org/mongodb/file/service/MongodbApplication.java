package org.mongodb.file.service;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月12日
 * @description: MongodbApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MongodbApplication {
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		SpringApplication app = new SpringApplication(MongodbApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}