package com.springlab.biz.controller;

import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.dao.UserDAObyJDBC;
import com.springlab.biz.user.domain.UserDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">>> 로그인 처리");
		
		String result = null;
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserDO user = new UserDO();
		user.setId(id);
		
		UserDAO dao = new UserDAObyJDBC();
		user = dao.getUser(user);
		
		if (user != null && password.equals(user.getPassword())) {
			request.getSession().setAttribute("user", user);
			result = "redirect:getBoardList.do";
		}
		else {
			result = "redirect:jsp/login.jsp";
		}
		
		return result;
	}

}
