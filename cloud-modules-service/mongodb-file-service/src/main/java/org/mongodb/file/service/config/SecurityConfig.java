package org.mongodb.file.service.config;

import org.mongodb.file.service.intercept.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月12日
 * @description: 权限配置
 */
@Configuration
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年12月12日
	 * @return SecurityInterceptor
	 * @description: 实例化权限拦截器
	 */
	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getSecurityInterceptor()).addPathPatterns("/**");
	}
}