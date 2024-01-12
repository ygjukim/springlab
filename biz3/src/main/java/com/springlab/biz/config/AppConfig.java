package com.springlab.biz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={ServiceConfig.class, AopConfig.class})
public class AppConfig {

}
