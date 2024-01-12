package com.springlab.biz.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages={"com.springlab.biz.board", "com.springlab.biz.user"})
@PropertySource("config/dbinfo.properties")
@EnableTransactionManagement
public class ServiceConfig {

	@Autowired
	Environment env;

	@Bean 
	public DataSource dataSource() {
		BasicDataSource dataSrc = new BasicDataSource();
		dataSrc.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSrc.setUrl(env.getProperty("jdbc.url"));
		dataSrc.setUsername(env.getProperty("jdbc.user"));
		dataSrc.setPassword(env.getProperty("jdbc.password"));
		return dataSrc;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		DataSourceTransactionManager txManager =
			new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}
}
