package com.springlab.biz.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages= {"com.springlab.biz.board", "com.springlab.biz.user"})
@PropertySource("dbinfo.properties")
public class ServiceConfig {

}
