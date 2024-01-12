package com.springlab.biz.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.springlab.biz.aop")
@EnableAspectJAutoProxy()
public class AopConfig {

}
