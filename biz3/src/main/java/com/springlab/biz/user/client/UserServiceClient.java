package com.springlab.biz.user.client;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springlab.biz.user.domain.UserDO;
import com.springlab.biz.user.service.UserService;

public class UserServiceClient {
	
	public static void main(String[] args) {

		ApplicationContext context =
			new ClassPathXmlApplicationContext("classpath:appConfig.xml");
		
		UserService userService = (UserService)context.getBean("userService");
		
		UserDO user = new UserDO();
		user.setId("gdhong");
		
		if (userService.getUser(user) == null) {
			user.setId("gdhong");
			user.setPassword("1234");
			user.setName("홍길동");
			user.setRole("admin");
			userService.insertUser(user);
		}
				
		List<UserDO> userList = userService.getUserList(null);
		System.out.println("--- 등록된 사용자 ---");
		for (UserDO em : userList) {
			System.out.println(em.toString());
		}
		
	}

}
