package org.module.web.user.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月17日
 * @description: 连接池配置
 */
@Configuration
public class HikariDataSourceConfiguration {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @return {@link DataSource}
	 * @description: 配置数据源
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource hikariDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		return dataSource;
	}
}