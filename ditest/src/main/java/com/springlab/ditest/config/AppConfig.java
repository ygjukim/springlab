package com.springlab.ditest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.springlab.ditest.LgTV2;
import com.springlab.ditest.Speaker;
import com.springlab.ditest.TV;

@Configuration
@ComponentScan(basePackages = {"com.springlab.ditest"})
@PropertySource(value = { "classpath:tv.properties" })
public class AppConfig {
	
	@Autowired
	Environment env;

	@Autowired
	@Qualifier("sony")
	Speaker speaker;
	
	@Bean
	public TV tv() {
//		TV tv = new SamsungTV2();
//		((SamsungTV2)tv).setPrice(1000);
		TV tv = new LgTV2(speaker, 
					env.getProperty("lg.brand"),
					Integer.parseInt(env.getProperty("lg.price")));	
		return tv;
	}
}
