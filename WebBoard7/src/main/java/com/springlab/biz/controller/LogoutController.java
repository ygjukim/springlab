package com.springlab.biz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">>> 로그아웃 처리");
		
		// step #1. get request parameters

		// step #2. data processing - DB 연동 처리
		// close session
		request.getSession().invalidate();
		
		// step #3. output processing result
		return "redirect:jsp/login.jsp";	
	}

}
