package org.module.web.user.config;

import org.module.web.user.intercept.UserSecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月17日
 * @description: 拦截器配置
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
	@Bean
	public UserSecurityInterceptor getUserSecurityInterceptor() {
		return new UserSecurityInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getUserSecurityInterceptor()).addPathPatterns("/**");
	}
}